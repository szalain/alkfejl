import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/user.service';
import {Router} from '@angular/router';
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-register-view',
  templateUrl: './register-view.component.html',
  styleUrls: ['./register-view.component.css'],
  providers: [UserService, AuthService]
})
export class RegisterViewComponent implements OnInit {

  private error = false;
  private success = false;
  private errorMsg = 'A felhasználónév/email már regisztrálva lett!'
  private successMsg = 'Sikeres regisztráció! Átirányítás a bejelentkezésre...'
  private user: Object = {};

  constructor(
    private userService: UserService,
    private router: Router,
    private authService: AuthService
  ) { }

  ngOnInit() {
  }

  private tryRegister(username: string, email: string, password: string): void {
    this.userService.registerUser(username, email, password).subscribe((user) => {
        console.log(user);
        this.error = false;
        this.success = true;
        setTimeout((router) => {
            this.router.navigate(['login']);
        }, 2000);
    }, (err) => {
        if (err.status === 400) {
            this.error = true;
        }
    });
  }

}
