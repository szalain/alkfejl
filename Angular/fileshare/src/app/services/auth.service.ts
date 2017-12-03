import { Injectable } from '@angular/core';
import {User} from '../classes/user';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class AuthService {
  private static api = 'http://localhost:4200/api/user';
  public static user: User;

  constructor(
    private http: HttpClient
  ) { }

  public login(username: string, password: string): Observable<User> {
    return this.http.post(AuthService.api + '/login', {
      username,
      password
    }) as Observable<User>;
  }

  public logout(): void {
    this.http.get(AuthService.api + '/logout').subscribe(() => {
      this.setUser(undefined);
    });
  }

  public setUser(user: User) {
    AuthService.user = user;
  }

  public getUser(): User {
    return AuthService.user;
  }

  public syncLoginStatus(): void {
    console.log(AuthService.user);
    this.http.get(AuthService.api).subscribe((user: User) => {
      if (user) {
        this.setUser(user);
      }
    });
  }

  /*public hasRole(role) {
    if (!this.getUser()) {
      return false;
    }
    return this.getUser().role == role;
  }
*/
}
