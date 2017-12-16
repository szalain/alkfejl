import { Injectable } from '@angular/core';
import {Role, User} from '../classes/user';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class AuthService {
  private static api = 'http://localhost:4200/api/user';
  public static user = new User();

  constructor(
    private http: HttpClient
  ) {this.syncLoginStatus(); }

  public login(username: string, password: string): Observable<User> {
    return this.http.post(AuthService.api + '/login', {
      username,
      password
    }) as Observable<User>;
  }

  public logout(): void {
    this.http.get(AuthService.api + '/logout').subscribe(() => {
      this.setUser(new User());
    });
  }

  public setUser(user: User) {
    // AuthService.user = user;
    AuthService.user = new User(user.id, user.username, user.email, user.uploadCount, user.isBanned, user.role);
  }

  public getUser(): User {
    return AuthService.user;
  }

  public syncLoginStatus(): void {
    this.http.get(AuthService.api).subscribe((user: User) => {
      if (user) {
        this.setUser(user);
        if (user.isBanned) this.logout();
      } else {
          // AuthService.user = new User();
          /*console.log(this.hasRole(Role.GUEST));
          console.log(this.hasRole(Role.ADMIN));
          console.log(this.getUser());*/
      }
    });
  }

  public hasRole(role) {
    if (!this.getUser()) {
      return false;
    }
    return this.getUser().role === role;
  }

}
