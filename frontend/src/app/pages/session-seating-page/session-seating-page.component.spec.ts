import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SessionSeatingPageComponent } from './session-seating-page.component';

describe('SessionSeatingPageComponent', () => {
  let component: SessionSeatingPageComponent;
  let fixture: ComponentFixture<SessionSeatingPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SessionSeatingPageComponent]
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
