import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserSessionsRecommendationPageComponent } from './user-sessions-recommendation-page.component';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

describe('UserSessionsRecommendationPageComponent', () => {
  let component: UserSessionsRecommendationPageComponent;
  let fixture: ComponentFixture<UserSessionsRecommendationPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UserSessionsRecommendationPageComponent],
      imports: [
        RouterTestingModule,
        HttpClientTestingModule,
        BrowserAnimationsModule
      ],
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
