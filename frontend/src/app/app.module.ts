import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SessionCardComponent } from './components/session-card/session-card.component';
import { SessionPageComponent } from './pages/session-page/session-page.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatSelectModule } from '@angular/material/select';
import { MatRadioModule } from '@angular/material/radio';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatExpansionModule } from '@angular/material/expansion';
import { SessionSeatingPageComponent } from './pages/session-seating-page/session-seating-page.component';
import UserSessionsComponent from './pages/user-sessions-page/user-sessions-page.component';
import { UserSessionsRecommendationPageComponent } from './pages/user-sessions-recommendation-page/user-sessions-recommendation-page.component';





@NgModule({
  declarations: [
    AppComponent,
    SessionCardComponent,
    SessionPageComponent,
    SessionSeatingPageComponent,
    UserSessionsComponent,
    UserSessionsRecommendationPageComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatSelectModule,
    MatRadioModule,
    MatExpansionModule,
    MatCheckboxModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatSelectModule,
    MatRadioModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
