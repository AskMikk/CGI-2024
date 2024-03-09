import { Component, Input } from '@angular/core';
import { Session } from '../../models/session';

@Component({
  selector: 'app-session-card',
  templateUrl: './session-card.component.html',
  styleUrl: './session-card.component.scss'
})
export class SessionCardComponent {
  @Input() session!: Session;
}
