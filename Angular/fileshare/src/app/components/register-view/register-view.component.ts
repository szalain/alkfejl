import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {User} from "../../classes/user";

@Component({
  selector: 'app-register-view',
  templateUrl: './register-view.component.html',
  styleUrls: ['./register-view.component.css'],
  providers: [UserService]
})
export class RegisterViewComponent implements OnInit {

  constructor(
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  //TODO: szerveroldalon és kliensoldalon helyesség vizsgálata: username:min. 3 karakter, és egyedi kell legyen, email: *@*.** formátum, jelszó: min. 3 karakter
  private tryRegister(username: string, email: string, password: string): void {
    this.userService.registerUser(username, email, password).subscribe((user) => console.log(user));
  }

}
