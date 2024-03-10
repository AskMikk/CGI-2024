import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "../../enviroments/environment";
import { Observable } from "rxjs";
import { Session } from "../models/session";
import { Seat } from "../models/seat";

@Injectable({
    providedIn: 'root'
  })
  export class SessionService {
    private readonly apiUrl = environment.backendUrl + '/api/session';
  
    constructor(private http: HttpClient) {}
  
    getAllUpcomingSessions(): Observable<Session[]> {
      return this.http.get<Session[]>(`${this.apiUrl}/upcoming`);
    }

    getOccupiedSeats(sessionId: number): Observable<Seat[]> {
      return this.http.get<Seat[]>(`${this.apiUrl}/${sessionId}/bookings`);
    }

    getSessionById(sessionId: number): Observable<Session> {
      return this.http.get<Session>(`${this.apiUrl}/${sessionId}`);
    }
  }