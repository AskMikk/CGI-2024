package com.cinema.recommendations.service;

import com.cinema.recommendations.entity.Booking;
import com.cinema.recommendations.entity.Session;
import com.cinema.recommendations.entity.User;
import com.cinema.recommendations.model.SeatDTO;
import com.cinema.recommendations.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public List<Booking> bookSeats(Session session, int row, List<Integer> seats, User user) {
        return seats.stream().map(seat -> new Booking(null, session, user, row, seat, new Date())).map(bookingRepository::save).collect(Collectors.toList());
    }

    private Set<String> getOccupiedSeatsSet(Long sessionId) {
        return getOccupiedSeatsForSession(sessionId).stream()
                .map(seat -> seat.getRow() + "_" + seat.getSeat())
                .collect(Collectors.toSet());
    }

    private List<SeatDTO> findAvailableSeats(int numberOfRows, int seatsPerRow, Set<String> occupied) {
        List<SeatDTO> availableSeats = new ArrayList<>();
        for (int row = 1; row <= numberOfRows; row++) {
            for (int seat = 1; seat <= seatsPerRow; seat++) {
                if (!occupied.contains(row + "_" + seat)) {
                    availableSeats.add(new SeatDTO(row, seat));
                }
            }
        }
        return availableSeats;
    }

    private List<List<SeatDTO>> findSuitableBlocks(List<SeatDTO> availableSeats, int numberOfRows, int numberOfTickets) {
        List<List<SeatDTO>> suitableBlocks = new ArrayList<>();
        for (int row = 1; row <= numberOfRows; row++) {
            final int finalRow = row;
            List<SeatDTO> rowSeats = availableSeats.stream()
                    .filter(s -> s.getRow() == finalRow)
                    .sorted(Comparator.comparingInt(SeatDTO::getSeat))
                    .collect(Collectors.toList());

            for (int i = 0; i <= rowSeats.size() - numberOfTickets; i++) {
                List<SeatDTO> block = new ArrayList<>(rowSeats.subList(i, i + numberOfTickets));
                if (block.getLast().getSeat() - block.getFirst().getSeat() == numberOfTickets - 1) {
                    suitableBlocks.add(block);
                }
            }
        }
        return suitableBlocks;
    }

    private void sortSuitableBlocks(List<List<SeatDTO>> suitableBlocks, int numberOfRows, int seatsPerRow) {
        suitableBlocks.sort(Comparator.comparingDouble(block -> {
            SeatDTO firstSeat = block.getFirst();
            SeatDTO lastSeat = block.getLast();
            int rowCenter = (numberOfRows + 1) / 2;
            int seatCenter = (seatsPerRow + 1) / 2;
            double rowDistance = Math.abs(firstSeat.getRow() - rowCenter);
            double penalty = Math.min(firstSeat.getRow() - 1, numberOfRows - firstSeat.getRow()) * 0.5;
            double seatDistance = Math.abs((firstSeat.getSeat() + lastSeat.getSeat()) / 2.0 - seatCenter);
            if (firstSeat.getRow() == 1 || firstSeat.getRow() == numberOfRows) {
                penalty += 2;
            }
            double adjustedRowDistance = rowDistance * 2 - penalty;
            return adjustedRowDistance + seatDistance;
        }));
    }

    public List<SeatDTO> recommendSeats(Session session, int numberOfTickets) {
        int numberOfRows = session.getTheater().getNumberOfRows();
        int seatsPerRow = session.getTheater().getSeatsPerRow();
        Set<String> occupied = getOccupiedSeatsSet(session.getId());
        List<SeatDTO> availableSeats = findAvailableSeats(numberOfRows, seatsPerRow, occupied);
        List<List<SeatDTO>> suitableBlocks = findSuitableBlocks(availableSeats, numberOfRows, numberOfTickets);
        sortSuitableBlocks(suitableBlocks, numberOfRows, seatsPerRow);
        if (!suitableBlocks.isEmpty()) {
            return suitableBlocks.getFirst();
        } else {
            return groupSeatsCloseToCenter(availableSeats, numberOfRows, seatsPerRow, numberOfTickets);
        }
    }

    private List<SeatDTO> groupSeatsCloseToCenter(List<SeatDTO> availableSeats, int numberOfRows, int seatsPerRow, int numberOfTickets) {
        int middleRow = numberOfRows / 2;
        int middleSeat = seatsPerRow / 2;

        PriorityQueue<SeatDTO> prioritySeats = new PriorityQueue<>(Comparator.comparingInt(
                seat -> Math.abs(seat.getRow() - middleRow) + Math.abs(seat.getSeat() - middleSeat)
        ));

        prioritySeats.addAll(availableSeats);

        List<SeatDTO> selectedSeats = new ArrayList<>();
        while (!prioritySeats.isEmpty() && numberOfTickets > 0) {
            selectedSeats.add(prioritySeats.poll());
            numberOfTickets--;
        }

        if (selectedSeats.size() < numberOfTickets) {
            return Collections.emptyList();
        }
        return selectedSeats;
    }

    public List<SeatDTO> getOccupiedSeatsForSession(Long sessionId) {
        List<Booking> bookings = bookingRepository.findBySessionId(sessionId);
        return bookings.stream().map(booking -> new SeatDTO(booking.getRowNumber(), booking.getSeatNumber())).collect(Collectors.toList());
    }
}
