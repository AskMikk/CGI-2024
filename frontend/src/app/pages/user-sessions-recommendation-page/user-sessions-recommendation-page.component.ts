import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { Session } from '../../models/session';

@Component({
  selector: 'app-user-sessions-recommendation-page',
  templateUrl: './user-sessions-recommendation-page.component.html',
  styleUrl: './user-sessions-recommendation-page.component.scss'
})
export class UserSessionsRecommendationPageComponent {
  sessions: Session[] = [];
  loading: boolean = true;

  constructor(
    private userService: UserService,
    private router: Router, 
    ) {}

  ngOnInit(): void {
    const userId = parseInt(localStorage.getItem('userId') || '0');
    if (userId > 0) {
      this.userService.getRecommendedSessionsBasedOnHistory(userId).subscribe({
        next: (sessions) => {
          this.sessions = sessions;
          this.loading = false;
        },
        error: (error) => {
          console.error('Failed to fetch sessions', error);
          this.loading = false;
        }
      });
    } else {
      console.error('User ID not found');
      this.loading = false;
    }
  }
  redirectToMain(): void {
    this.router.navigate(['/']); 
  }
}

