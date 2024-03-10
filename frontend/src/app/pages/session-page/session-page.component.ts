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
  filteredSessions: Session[] = [];
  searchTerm: string = '';
  selectedGenres: string[] = [];
  selectedAgeRestrictions: string[] = [];
  selectedLanguages: string[] = [];
  selectedSubtitles: string[] = [];
  selectedStartTime: string = '';
  genres = ['Animatsioon', 'Märul', 'Seiklus', 'Komöödia', 'Ulmefilm', 'Draama', 'Kogupere', 'Biograafia', 'Muusika', 'Fantaasia', 'Triller', 'Anime', 'Sport', 'Õudus'];
  ageRestrictions = ['PERE', 'MS-6', 'MS-12', 'K-14', 'K-12'];
  languages = ['Eesti', 'Inglise', 'Vene'];
  subtitles = ['Eesti', 'Inglise', 'Vene'];
  startTimeOptions = [
    '8:00', '9:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', 
    '16:00', '17:00', '18:00', '19:00', '20:00'
  ];
  showFilters = false;

  constructor(private sessionService: SessionService) {}

  ngOnInit(): void {
    this.fetchSessions();
  }

  fetchSessions(): void {
    this.sessionService.getAllUpcomingSessions().subscribe(sessions => {
      this.sessions = sessions;
      this.filterSessions();
      this.loading = false;
    });
  }

  filterSessions(): void {
    this.filteredSessions = this.sessions.filter(session => {
      const matchesSearchTerm = !this.searchTerm || session.filmTitle.toLowerCase().includes(this.searchTerm.toLowerCase());
      const matchesGenres = this.selectedGenres.length === 0 || session.genres.some(genre => this.selectedGenres.includes(genre));
      const matchesAgeRestrictions = this.selectedAgeRestrictions.length === 0 || this.selectedAgeRestrictions.includes(session.ageRestriction);
      const matchesLanguages = this.selectedLanguages.length === 0 || this.selectedLanguages.includes(session.language);
      const matchesSubtitles = this.selectedSubtitles.length === 0 || session.subtitles.some(subtitle => this.selectedSubtitles.includes(subtitle));
      const matchesStartTime = !this.selectedStartTime || new Date(session.startTime).getHours() >= parseInt(this.selectedStartTime.split(':')[0]);
      
      return matchesSearchTerm && matchesGenres && matchesAgeRestrictions && matchesLanguages && matchesSubtitles && matchesStartTime;
    });
  }

  toggleFilters(): void {
    this.showFilters = !this.showFilters;
  }

  resetFilters(): void {
    this.selectedGenres = [];
    this.selectedAgeRestrictions = [];
    this.selectedLanguages = [];
    this.selectedSubtitles = [];
    this.selectedStartTime = '';
    this.filterSessions();
  }
}