import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SessionPageComponent } from './pages/session-page/session-page.component';
import { SessionSeatingPageComponent } from './pages/session-seating-page/session-seating-page.component';

const routes: Routes = [
  {path: '', redirectTo: 'sessions', pathMatch: 'full'},
  {path: 'sessions', component: SessionPageComponent},
  {path: 'session/:sessionId/seating', component: SessionSeatingPageComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
