import { ComponentFixture, TestBed } from '@angular/core/testing';
import UserSessionsComponent from './user-sessions-page.component';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Router } from '@angular/router';
import { SessionCardComponent } from '../../components/session-card/session-card.component';

describe('UserSessionsComponent', () => {
  let component: UserSessionsComponent;
  let fixture: ComponentFixture<UserSessionsComponent>;
  let routerMock: jasmine.SpyObj<Router>;

  beforeEach(async () => {
    routerMock = jasmine.createSpyObj('Router', ['navigate']);

    await TestBed.configureTestingModule({
      declarations: [UserSessionsComponent, SessionCardComponent],
      imports: [RouterTestingModule, HttpClientTestingModule, BrowserAnimationsModule],
      providers: [
        { provide: Router, useValue: routerMock }
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(UserSessionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
