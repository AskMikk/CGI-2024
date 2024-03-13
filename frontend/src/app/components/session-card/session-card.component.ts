import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Session } from '../../models/session';
import { BookingService } from '../../services/booking.service';
import { SeatRecommendationService } from '../../services/seat-recommendation-service';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-session-card',
  templateUrl: './session-card.component.html',
  styleUrl: './session-card.component.scss'
})
export class SessionCardComponent {
  @Output() bookingDeleted: EventEmitter<void> = new EventEmitter();
  @Input() session: Session = {
    sessionId: 0,
    filmTitle: '',
    genres: [],
    ageRestriction: '',
    startTime: '',
    theaterName: '',
    availableSeats: 0,
    language: '',
    subtitles: [],
    posterUrl: '',
    numberOfRows: 0,
    seatsPerRow: 0
  };
  ticketCount: number = 1;

  constructor(
    private router: Router,
    private bookingService: BookingService,
    private seatRecommendationService: SeatRecommendationService,
    private userService: UserService
  ) {}
  bookSession(): void {
    this.bookingService.recommendSeats(this.session.sessionId, this.ticketCount).subscribe({
      next: (recommendedSeats) => {
        this.seatRecommendationService.setRecommendedSeats(recommendedSeats, this.session.sessionId);
        this.router.navigate(['/session', this.session.sessionId, 'seating']);
      },
      error: (error) => console.error('Failed to get seat recommendations', error)
    });
  }

  isHistoryPage(): boolean {
    return this.router.url.includes('/history');
  }

  increaseTickets(): void {
    const maxAvailableSeats = this.session.availableSeats ?? 0;
      if (this.ticketCount < maxAvailableSeats) {
      this.ticketCount++;
    }
  }

  decreaseTickets(): void {
    if (this.ticketCount > 1) {
      this.ticketCount--;
    }
  }

  deleteUserBooking(): void {
    const userId = parseInt(localStorage.getItem('userId') || '0');
    if (userId && this.session.sessionId) {
      this.userService.deleteAllBookingsBySessionAndUser(userId, this.session.sessionId).subscribe({
        next: () => {
          this.bookingDeleted.emit();
        },
        error: (error) => console.error('Failed to delete booking', error)
      });
    }
  }
}
