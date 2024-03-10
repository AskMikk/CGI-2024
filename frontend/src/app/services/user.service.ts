import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../enviroments/environment';
import { Session } from '../models/session';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private readonly apiUrl = `${environment.backendUrl}/api/user`;

  constructor(private http: HttpClient) {}

  getSessionsByUserId(userId: number): Observable<Session[]> {
    return this.http.get<Session[]>(`${this.apiUrl}/${userId}`);
  }

  getRecommendedSessionsBasedOnHistory(userId: number): Observable<Session[]> {
    return this.http.get<Session[]>(`${this.apiUrl}/${userId}/recommendations`);
  }
}
