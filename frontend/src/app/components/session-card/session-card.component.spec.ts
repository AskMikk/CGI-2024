import { ComponentFixture, TestBed } from '@angular/core/testing';
import { SessionCardComponent } from './session-card.component';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';
import { BookingService } from '../../services/booking.service';
import { SeatRecommendationService } from '../../services/seat-recommendation-service';
import { of } from 'rxjs';
import { Router } from '@angular/router';
import { Seat } from '../../models/seat';

// Kirjutatud tehisintellekti kasutades
describe('SessionCardComponent', () => {
  let component: SessionCardComponent;
  let fixture: ComponentFixture<SessionCardComponent>;
  let mockBookingService: jasmine.SpyObj<BookingService>;
  let mockSeatRecommendationService: jasmine.SpyObj<SeatRecommendationService>;
  let router: Router;

  beforeEach(async () => {
    mockBookingService = jasmine.createSpyObj('BookingService', ['recommendSeats']);
    mockSeatRecommendationService = jasmine.createSpyObj('SeatRecommendationService', ['setRecommendedSeats']);

    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientTestingModule,
        FormsModule
      ],
      declarations: [SessionCardComponent],
      providers: [
        { provide: BookingService, useValue: mockBookingService },
        { provide: SeatRecommendationService, useValue: mockSeatRecommendationService }
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SessionCardComponent);
    component = fixture.componentInstance;
    router = TestBed.inject(Router);
    spyOn(router, 'navigate');
    fixture.detectChanges();
  });

  it('should initialize with default input values', () => {
    expect(component.session).toBeDefined();
    expect(component.ticketCount).toBe(1);
  });

  it('should navigate to seating page on successful booking', () => {
    const recommendedSeats: Seat[] = [
      { row: 1, seat: 1 },
      { row: 1, seat: 2 }
    ];
    mockBookingService.recommendSeats.and.returnValue(of(recommendedSeats));
    component.bookSession();
    expect(mockBookingService.recommendSeats).toHaveBeenCalledWith(component.session.sessionId, component.ticketCount);
    expect(mockSeatRecommendationService.setRecommendedSeats).toHaveBeenCalledWith(recommendedSeats, component.session.sessionId);
    expect(router.navigate).toHaveBeenCalledWith(['/session', component.session.sessionId, 'seating']);
  });

  it('should return true if current URL includes /history', () => {
    Object.defineProperty(router, 'url', {value: '/history', writable: true});
    expect(component.isHistoryPage()).toBeTrue();
  });


  it('should increase ticket count if below available seats', () => {
    component.session.availableSeats = 5;
    component.increaseTickets();
    expect(component.ticketCount).toBe(2);
  });

  it('should not increase ticket count if at max available seats', () => {
    component.session.availableSeats = 1;
    component.increaseTickets();
    expect(component.ticketCount).toBe(1);
  });

  it('should decrease ticket count if above 1', () => {
    component.ticketCount = 2;
    component.decreaseTickets();
    expect(component.ticketCount).toBe(1);
  });

  it('should not decrease ticket count if at 1', () => {
    component.decreaseTickets();
    expect(component.ticketCount).toBe(1);
  });
});
