import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "./services/auth.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [AuthService]
})
export class AppComponent implements OnInit {
  title = 'Kezdőlap';
  public constructor(
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit() {
    //this.authService.syncLoginStatus();
  }

  private logout() {
    this.authService.logout();
  }
}
