import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SessionSeatingPageComponent } from './session-seating-page.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

describe('SessionSeatingPageComponent', () => {
  let component: SessionSeatingPageComponent;
  let fixture: ComponentFixture<SessionSeatingPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SessionSeatingPageComponent],
      imports: [
        RouterTestingModule,
        HttpClientTestingModule,
        BrowserAnimationsModule
      ],
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SessionSeatingPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
