import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "../../enviroments/environment";
import { Observable } from "rxjs";
import { Session } from "../models/session";

@Injectable({
    providedIn: 'root'
  })
  export class SessionService {
    private readonly apiUrl = environment.backendUrl + '/api/session';
  
    constructor(private http: HttpClient) {}
  
    getAllUpcomingSessions(): Observable<Session[]> {
      return this.http.get<Session[]>(`${this.apiUrl}/upcoming`);
    }
  }