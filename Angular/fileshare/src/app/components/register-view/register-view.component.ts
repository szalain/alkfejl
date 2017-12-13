import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register-view',
  templateUrl: './register-view.component.html',
  styleUrls: ['./register-view.component.css'],
  providers: [UserService]
})
export class RegisterViewComponent implements OnInit {

  private error: boolean;
  private success: boolean;
  private errorMsg = 'Hiba: A felhasználónév/email már regisztrálva lett!'
  private successMsg = 'Sikeres regisztráció! Átirányítás a bejelentkezésre...'
  private user: Object = {};

  constructor(
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  // TODO: szerveroldalon és kliensoldalon helyesség vizsgálata: username:min. 3 karakter, és egyedi kell legyen, email: *@*.** formátum, jelszó: min. 3 karakter
  private tryRegister(username: string, email: string, password: string): void {
      /*if (!username.match('[a-zA-Z][a-zA-Z0-9]{2,15}')
        || !email.match('[a-zA-Z0-9]{3,16}@[a-zA-Z0-9]{3,16}(\\.[a-zA-Z]{2,8}){1,3}')
        || !password.match('[a-zA-Z0-9]{5,16}')) {
          return;
      }*/
    this.userService.registerUser(username, email, password).subscribe((user) => {
        console.log(user);
        this.error = false;
        this.success = true;
        setTimeout((router) => {
            this.router.navigate(['login']);
        }, 3000);
    }, (err) => {
        if (err.status === 400) {
            this.error = true;
        }
    });
  }

}
