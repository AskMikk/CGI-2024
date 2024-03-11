import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "../../enviroments/environment";
import { Seat } from "../models/seat";
import { Booking } from "../models/booking";
import { Observable, catchError, tap, throwError } from "rxjs";

@Injectable({
    providedIn: 'root'
  })
  export class BookingService {
    private readonly apiUrl = environment.backendUrl + '/api/booking';
  
    constructor(private http: HttpClient) {}

    recommendSeats(sessionId: number, numberOfTickets: number): Observable<Seat[]> {
        const params = new HttpParams()
          .set('sessionId', sessionId.toString())
          .set('numberOfTickets', numberOfTickets.toString());
        return this.http.get<Seat[]>(`${this.apiUrl}/recommend-seats`, { params }).pipe(
          catchError(error => {
            console.error('Error fetching recommended seats:', error);
            return throwError(error);
          })
        );
      }
  
    bookSeats(bookingRequest: Booking): Observable<any> {
      return this.http.post(`${this.apiUrl}/book`, bookingRequest);
    }
  }