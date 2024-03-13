import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { Session } from '../../models/session';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-sessions-page',
  templateUrl: './user-sessions-page.component.html',
  styleUrl: './user-sessions-page.component.scss'
})
export default class UserSessionsPageComponent implements OnInit {
  sessions: Session[] = [];
  loading: boolean = true;

  constructor(
    private userService: UserService,
    private router: Router, 
    ) {}

  ngOnInit(): void {
    const userId = parseInt(localStorage.getItem('userId') || '0');
    if (userId > 0) {
      this.userService.getSessionsByUserId(userId).subscribe({
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
    this.fetchUserSessions();
  }

  redirectToMain(): void {
    this.router.navigate(['/']); 
  }

  fetchUserSessions(): void {
    this.loading = true;
    const userId = parseInt(localStorage.getItem('userId') || '0');
    if (userId > 0) {
      this.userService.getSessionsByUserId(userId).subscribe({
        next: (sessions) => {
          this.sessions = sessions;
          this.loading = false;
        },
        error: (error) => {
          console.error('Failed to fetch sessions', error);
          this.loading = false;
        }
      });
    }
  }
}
