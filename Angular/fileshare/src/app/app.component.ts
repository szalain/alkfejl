import {Component, OnInit} from '@angular/core';
import {NavigationEnd, Router} from '@angular/router';
import {AuthService} from './services/auth.service';

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
    this.authService.syncLoginStatus();
      // override the route reuse strategy
      this.router.routeReuseStrategy.shouldReuseRoute = function(){
          return false;
      }

      this.router.events.subscribe((evt) => {
          if (evt instanceof NavigationEnd) {
              // trick the Router into believing it's last link wasn't previously loaded
              this.router.navigated = false;
              // if you need to scroll back to top, here is the right place
              window.scrollTo(0, 0);
          }
      });
  }

  private logout() {
    this.authService.logout();
  }
}
