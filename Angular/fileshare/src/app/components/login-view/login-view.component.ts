import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {Router} from '@angular/router';
import {User} from '../../classes/user';

@Component({
  selector: 'app-login-view',
  templateUrl: './login-view.component.html',
  styleUrls: ['./login-view.component.css'],
  providers: [AuthService]
})
export class LoginViewComponent implements OnInit {

  private error: boolean;
  private errorMsg: string;

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
      this.error = false;
      this.router.navigate(['/']);
    }, (err) => {
      this.error = true;
      if (err.status === 403) {
        this.errorMsg = 'Hiba: \'' + username + '\' felhasználó ki van tiltva!';
      } else if (err.status === 400) {
        this.errorMsg = 'Hiba: Nem megfelelő felhasználónév/jelszó!';
      }
    });
  }


}
