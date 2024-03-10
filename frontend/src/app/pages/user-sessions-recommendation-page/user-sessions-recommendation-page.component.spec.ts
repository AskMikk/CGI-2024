import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserSessionsRecommendationPageComponent } from './user-sessions-recommendation-page.component';

describe('UserSessionsRecommendationPageComponent', () => {
  let component: UserSessionsRecommendationPageComponent;
  let fixture: ComponentFixture<UserSessionsRecommendationPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UserSessionsRecommendationPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UserSessionsRecommendationPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
