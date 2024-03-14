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

    public List<SeatDTO> recommendSeats(Session session, int numberOfTickets) {
        int numberOfRows = session.getTheater().getNumberOfRows();
        int seatsPerRow = session.getTheater().getSeatsPerRow();
        Set<String> occupied = getOccupiedSeatsSet(session.getId());
        List<SeatDTO> availableSeats = findAvailableSeats(numberOfRows, seatsPerRow, occupied);

        return findOptimalGroupPlacement(availableSeats, numberOfRows, seatsPerRow, numberOfTickets);
    }

    // Kasutasin tehisintellekti funktsiooni ümbertöötamiseks.
    private List<SeatDTO> findOptimalGroupPlacement(List<SeatDTO> availableSeats, int numberOfRows, int seatsPerRow, int numberOfTickets) {
        List<SeatDTO> optimalPlacement = new ArrayList<>();
        double bestScore = Double.MAX_VALUE;

        for (SeatDTO startSeat : availableSeats) {
            Queue<SeatDTO> queue = new LinkedList<>();
            queue.add(startSeat);
            List<SeatDTO> currentPlacement = new ArrayList<>();
            while (!queue.isEmpty() && currentPlacement.size() < numberOfTickets) {
                SeatDTO currentSeat = queue.poll();
                if (!currentPlacement.contains(currentSeat) && isAdjacentOrCloseToPlacement(currentSeat, currentPlacement)) {
                    currentPlacement.add(currentSeat);
                    queue.addAll(getAdjacentSeats(currentSeat, availableSeats, numberOfRows, seatsPerRow));
                }
            }
            if (currentPlacement.size() == numberOfTickets) {
                double currentScore = evaluatePlacement(currentPlacement, numberOfRows, seatsPerRow);
                if (currentScore < bestScore) {
                    bestScore = currentScore;
                    optimalPlacement = new ArrayList<>(currentPlacement);
                }
            }
        }
        return optimalPlacement;
    }

    // Kasutasin tehisintellekti funktsiooni ümbertöötamiseks.
    private List<SeatDTO> getAdjacentSeats(SeatDTO seat, List<SeatDTO> availableSeats, int numberOfRows, int seatsPerRow) {
        List<SeatDTO> adjacentSeats = new ArrayList<>();
        int[] rowOffsets = {-1, 0, 1};
        int[] seatOffsets = {-1, 0, 1};
        for (int rowOffset : rowOffsets) {
            for (int seatOffset : seatOffsets) {
                if (rowOffset != 0 && seatOffset != 0) {
                    continue;
                }
                int newRow = seat.getRow() + rowOffset;
                int newSeat = seat.getSeat() + seatOffset;
                if (newRow > 0 && newRow <= numberOfRows && newSeat > 0 && newSeat <= seatsPerRow) {
                    SeatDTO potentialSeat = new SeatDTO(newRow, newSeat);
                    if (availableSeats.contains(potentialSeat) && !seat.equals(potentialSeat)) {
                        adjacentSeats.add(potentialSeat);
                    }
                }
            }
        }
        return adjacentSeats;
    }

    private boolean isAdjacentOrCloseToPlacement(SeatDTO seat, List<SeatDTO> placement) {
        if (placement.isEmpty()) return true;
        return !getAdjacentSeats(seat, placement, Integer.MAX_VALUE, Integer.MAX_VALUE).isEmpty();
    }

    private double evaluatePlacement(List<SeatDTO> placement, int numberOfRows, int seatsPerRow) {
        int centerRow = (numberOfRows + 1) / 2;
        int centerSeat = (seatsPerRow + 1) / 2;
        double widthHeightRatio = (double)seatsPerRow / numberOfRows * 2.5;

        return placement.stream().mapToDouble(seat -> {
            double rowDistance = Math.abs(seat.getRow() - centerRow) * widthHeightRatio;
            double seatDistance = Math.abs(seat.getSeat() - centerSeat);
            double penalty = 0;
            if (seat.getRow() == 1 || seat.getRow() == numberOfRows) {
                penalty += 100;
            }
            return rowDistance + seatDistance + penalty;
        }).average().orElse(Double.MAX_VALUE);
    }


    public List<SeatDTO> getOccupiedSeatsForSession(Long sessionId) {
        List<Booking> bookings = bookingRepository.findBySessionId(sessionId);
        return bookings.stream().map(booking -> new SeatDTO(booking.getRowNumber(), booking.getSeatNumber())).collect(Collectors.toList());
    }
}
