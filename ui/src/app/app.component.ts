import { Component } from '@angular/core';
import { RestService } from './rest.service';

@Component({
  selector: 'mmj-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  isCollapsed = false;
  title = 'OTRS';

  constructor(private rest: RestService) {}
}
