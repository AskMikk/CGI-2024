import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Session } from '../../models/session';
import { BookingService } from '../../services/booking.service';
import { SeatRecommendationService } from '../../services/seat-recommendation-service';

@Component({
  selector: 'app-session-card',
  templateUrl: './session-card.component.html',
  styleUrl: './session-card.component.scss'
})
export class SessionCardComponent {
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
    private seatRecommendationService: SeatRecommendationService
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
}
