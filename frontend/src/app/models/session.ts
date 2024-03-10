export interface Session {
  sessionId: number;
  filmTitle: string;
  genres: string[];
  ageRestriction: string;
  startTime: string;
  theaterName: string;
  availableSeats: number | null;
  language: string;
  subtitles: string[];
  posterUrl: string;
  numberOfRows: number;
  seatsPerRow: number;
}
