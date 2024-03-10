import { Injectable } from '@angular/core';
import { Seat } from '../models/seat';

@Injectable({
  providedIn: 'root'
})
export class SeatRecommendationService {
  private recommendedSeats: Seat[] = [];

  setRecommendedSeats(seats: Seat[], sessionId: number): void {
    this.recommendedSeats = seats;
    localStorage.setItem(`recommendedSeats-${sessionId}`, JSON.stringify(seats));
  }

  getRecommendedSeats(sessionId: number): Seat[] {
    const savedSeats = localStorage.getItem(`recommendedSeats-${sessionId}`);
    if (savedSeats) {
      this.recommendedSeats = JSON.parse(savedSeats);
    }
    return this.recommendedSeats;
  }
}
