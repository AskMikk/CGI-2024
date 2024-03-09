import { Component, OnInit } from '@angular/core';
import { Session } from '../../models/session';
import { SessionService } from '../../services/sesssion.service';

@Component({
  selector: 'app-session-page',
  templateUrl: './session-page.component.html',
  styleUrl: './session-page.component.scss'
})
export class SessionPageComponent implements OnInit {
  loading = true;
  sessions: Session[] = [];
  searchTerm: string = '';

  constructor(private sessionService: SessionService) {}

  ngOnInit(): void {
    this.fetchSessions();
  }

  fetchSessions(): void {
    this.sessionService.getAllUpcomingSessions().subscribe({
      next: (sessions) => {
        this.sessions = sessions;
        this.loading = false;
      },
      error: () => {
        this.loading = false;
      }
    });
  }
}
