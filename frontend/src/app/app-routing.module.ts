import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SessionPageComponent } from './pages/session-page/session-page.component';

const routes: Routes = [
  {path: '', redirectTo: 'sessions', pathMatch: 'full'},
  {path: 'sessions', component: SessionPageComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
