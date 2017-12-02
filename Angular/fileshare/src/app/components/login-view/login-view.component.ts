import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {User} from "../../classes/user";

@Component({
  selector: 'app-login-view',
  templateUrl: './login-view.component.html',
  styleUrls: ['./login-view.component.css'],
  providers: [AuthService]
})
export class LoginViewComponent implements OnInit {

  private error: boolean;

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  private tryLogin(username: string, password: string): void {
    this.authService.login(username, password).subscribe((user) => {
      console.log(user);
      this.authService.setUser(user as User);
      this.router.navigate(['/']);
    }, (err) => {
      if (err.status === 403) {
        this.error = true;
      }
    });
  }


}
