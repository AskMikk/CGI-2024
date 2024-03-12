import { ComponentFixture, TestBed } from '@angular/core/testing';
import { SessionPageComponent } from './session-page.component';
import { SessionService } from '../../services/sesssion.service';
import { Router } from '@angular/router';
import { Session } from '../../models/session';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { of } from 'rxjs';
import { SessionCardComponent } from '../../components/session-card/session-card.component';

// Kirjutatud tehisintellekti kasutades
describe('SessionPageComponent', () => {
  let component: SessionPageComponent;
  let fixture: ComponentFixture<SessionPageComponent>;
  let mockSessionService: jasmine.SpyObj<SessionService>;
  let mockRouter: jasmine.SpyObj<Router>;
  const mockSessions: Session[] = [
    {
      sessionId: 382,
      filmTitle: "Mahajäänud",
      genres: ["Draama", "Komöödia"],
      ageRestriction: "K-12",
      startTime: "2025-03-12T06:00:00.000+00:00",
      theaterName: "Väike Saal",
      availableSeats: 71,
      language: "Inglise",
      subtitles: ["Vene", "Eesti"],
      posterUrl: "assets/images/Holdovers.jpg",
      numberOfRows: 5,
      seatsPerRow: 20
    },
    {
      sessionId: 388,
      filmTitle: "Kung Fu Panda 4",
      genres: ["Märul", "Seiklus", "Animatsioon"],
      ageRestriction: "PERE",
      startTime: "2025-03-12T06:00:00.000+00:00",
      theaterName: "VIP Saal",
      availableSeats: 36,
      language: "Vene",
      subtitles: ["Inglise", "Eesti"],
      posterUrl: "assets/images/KungFuPanda.jpg",
      numberOfRows: 5,
      seatsPerRow: 10
    },
    {
      sessionId: 377,
      filmTitle: "Priscilla",
      genres: ["Draama", "Biograafia"],
      ageRestriction: "K-12",
      startTime: "2025-03-12T08:00:00.000+00:00",
      theaterName: "Suur Saal",
      availableSeats: 219,
      language: "Vene",
      subtitles: ["Inglise", "Eesti"],
      posterUrl: "assets/images/Priscilla.jpg",
      numberOfRows: 10,
      seatsPerRow: 25
    }
  ];

  beforeEach(async () => {
    mockRouter = jasmine.createSpyObj('Router', ['navigate']);
    Object.defineProperty(mockRouter, 'url', { value: '/test-url' });
    mockSessionService = jasmine.createSpyObj('SessionService', ['getAllUpcomingSessions']);
    mockSessionService.getAllUpcomingSessions.and.returnValue(of(mockSessions));

    await TestBed.configureTestingModule({
      declarations: [SessionPageComponent, SessionCardComponent],
      imports: [
        RouterTestingModule.withRoutes([{path: 'sessions', component: SessionPageComponent}]),
        HttpClientTestingModule,
        FormsModule,
        BrowserAnimationsModule,
        MatFormFieldModule,
        MatInputModule,
        MatSelectModule,
        BrowserAnimationsModule
      ],
      providers: [
        { provide: SessionService, useValue: mockSessionService },
        { provide: Router, useValue: mockRouter }
      ]
    }).compileComponents();
    mockRouter.navigate(['/sessions']);
    fixture = TestBed.createComponent(SessionPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should fetch and filter sessions on init', () => {
    expect(component.sessions.length).toBe(3);
    expect(component.filteredSessions.length).toBe(3);
    expect(component.loading).toBeFalse();
  });

  it('should filter sessions based on search term', () => {
    component.searchTerm = 'Priscilla';
    component.filterSessions();
    expect(component.filteredSessions.length).toBe(1);
    expect(component.filteredSessions[0].filmTitle).toBe('Priscilla');
  });

  it('should toggle filters visibility', () => {
    expect(component.showFilters).toBeFalse();
    component.toggleFilters();
    expect(component.showFilters).toBeTrue();
    component.toggleFilters();
    expect(component.showFilters).toBeFalse();
  });

  it('should reset filters', () => {
    component.selectedGenres = ['Pere'];
    component.resetFilters();
    expect(component.selectedGenres.length).toBe(0);
    expect(component.filteredSessions.length).toBe(3);
  });

  it('should navigate to recommendation page', () => {
    component.redirectToRecommendation();
    expect(mockRouter.navigate).toHaveBeenCalledWith(['/recommendation']);
  });
});
