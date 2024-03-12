import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Session } from '../../models/session';
import { Seat } from '../../models/seat';
import { SessionService } from '../../services/sesssion.service';
import { SeatRecommendationService } from '../../services/seat-recommendation-service';
import { BookingService } from '../../services/booking.service';
import { Booking } from '../../models/booking';

@Component({
  selector: 'app-session-seating-page',
  templateUrl: './session-seating-page.component.html',
  styleUrl: './session-seating-page.component.scss'
})
export class SessionSeatingPageComponent implements OnInit {
  session!: Session;
  occupiedSeats: Seat[] = [];
  recommendedSeats: Seat[] = [];
  seatMatrix: boolean[][] = [];
  selectedSeats: Seat[] = [];
  ticketCount: number = 1;

  constructor(
    private sessionService: SessionService,
    private route: ActivatedRoute,
    private router: Router,
    private seatRecommendationService: SeatRecommendationService,
    private bookingService: BookingService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const sessionId = +params['sessionId'];
      this.sessionService.getSessionById(sessionId).subscribe(session => {
        this.session = session;
        this.fetchOccupiedSeats(sessionId);
        this.restoreRecommendedSeats();
      });
    });
  }

  restoreRecommendedSeats(): void {
    const savedSeats = localStorage.getItem(`recommendedSeats-${this.session.sessionId}`);
    if (savedSeats) {
      this.recommendedSeats = JSON.parse(savedSeats);
    } else {
      this.recommendedSeats = this.seatRecommendationService.getRecommendedSeats(this.session.sessionId);
      this.saveRecommendedSeats();
    }
    this.ticketCount = this.recommendedSeats.length;
    this.initializeSeatMatrix();
  }

  saveRecommendedSeats(): void {
    localStorage.setItem(`recommendedSeats-${this.session.sessionId}`, JSON.stringify(this.recommendedSeats));
  }

  bookRecommendedSeats(): void {
    if (this.recommendedSeats.length > 0) {
      const userId = localStorage.getItem('userId');
      const bookingRequest: Booking = {
        sessionId: this.session.sessionId,
        userId: parseInt(userId!),
        row: this.recommendedSeats[0].row,
        seats: this.recommendedSeats.map(s => s.seat)
      };

      this.bookingService.bookSeats(bookingRequest).subscribe({
        next: (response) => {
          localStorage.removeItem(`recommendedSeats-${this.session.sessionId}`);
        },
        error: (error) => console.error('Booking failed', error,bookingRequest)
      });
    } else {
      console.error('No seats selected');
    }
    this.router.navigate(['/']);
  }

  fetchOccupiedSeats(sessionId: number): void {
    this.sessionService.getOccupiedSeats(sessionId).subscribe({
      next: (seats) => {
        this.occupiedSeats = seats;
        this.initializeSeatMatrix();
      },
      error: (err) => console.error(err)
    });
  }

  initializeSeatMatrix(): void {
    this.seatMatrix = Array.from({ length: this.session.numberOfRows }, (_, rowIndex) =>
      Array.from({ length: this.session.seatsPerRow }, (_, seatIndex) =>
        this.occupiedSeats.some(seat => seat.row === rowIndex + 1 && seat.seat === seatIndex + 1) ||
        this.recommendedSeats.some(seat => seat.row === rowIndex + 1 && seat.seat === seatIndex + 1)
      )
    );
  }

  isSeatOccupied(row: number, seat: number): boolean {
    return this.occupiedSeats.some(seatObj => seatObj.row === row + 1 && seatObj.seat === seat + 1);
  }

  isSeatRecommended(row: number, seat: number): boolean {
    return this.recommendedSeats.some(s => s.row === row + 1 && s.seat === seat + 1);
  }

  increaseTickets(): void {
    const maxAvailableSeats = this.session.availableSeats ?? 0;
    if (this.ticketCount < maxAvailableSeats) {
      this.ticketCount++;
      this.bookingService.recommendSeats(this.session.sessionId, this.ticketCount).subscribe({
        next: (recommendedSeats) => {
          this.recommendedSeats = recommendedSeats;
          this.saveRecommendedSeats();
          this.restoreRecommendedSeats();
          this.fetchOccupiedSeats(this.session.sessionId);
        },
        error: (error) => console.error('Failed to get seat recommendations', error)
      });
    }
  }

  decreaseTickets(): void {
    if (this.ticketCount > 1) {
      this.ticketCount--;
      this.bookingService.recommendSeats(this.session.sessionId, this.ticketCount).subscribe({
        next: (recommendedSeats) => {
          this.recommendedSeats = recommendedSeats;
          this.saveRecommendedSeats();
          this.restoreRecommendedSeats();
          this.fetchOccupiedSeats(this.session.sessionId);
        },
        error: (error) => console.error('Failed to get seat recommendations', error)
      });
    }
  }
}
