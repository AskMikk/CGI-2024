import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SessionCardComponent } from './components/session-card/session-card.component';
import { SessionPageComponent } from './pages/session-page/session-page.component';

@NgModule({
  declarations: [
    AppComponent,
    SessionCardComponent,
    SessionPageComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
