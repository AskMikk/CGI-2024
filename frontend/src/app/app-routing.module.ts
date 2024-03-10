import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SessionPageComponent } from './pages/session-page/session-page.component';
import { SessionSeatingPageComponent } from './pages/session-seating-page/session-seating-page.component';
import UserSessionsComponent from './pages/user-sessions-page/user-sessions-page.component';
import { UserSessionsRecommendationPageComponent } from './pages/user-sessions-recommendation-page/user-sessions-recommendation-page.component';

const routes: Routes = [
  { path: '', redirectTo: 'sessions', pathMatch: 'full' },
  { path: 'sessions', component: SessionPageComponent },
  { path: 'session/:sessionId/seating', component: SessionSeatingPageComponent },
  { path: 'history', component: UserSessionsComponent },
  { path: 'recommendation', component: UserSessionsRecommendationPageComponent } 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
