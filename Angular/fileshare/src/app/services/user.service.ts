import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {Role, User} from '../classes/user';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class UserService {

    private static api = 'http://localhost:4200/api/user';

  constructor(
    private http: HttpClient
  ) { }

  public registerUser(username: string, email: string, password: string): Observable<User> {
    return this.http.post(UserService.api + '/register', {username, email, password}) as Observable<User>;
  }

  public findUser(username: String): Observable<User> {
    return this.http.post(UserService.api + '/control', {username}) as Observable<User>;
  }

  public modifyRole(role: Role, id: number): Observable<User> {
    return this.http.patch(UserService.api + '/control/modify/' + id, {role}) as Observable<User>;
  }

  public banUser(id: number): Observable<User> {
    return this.http.patch(UserService.api + '/control/ban/' + id, {}) as Observable<User>;
  }

  public unbanUser(id: number): Observable<User> {
    return this.http.patch(UserService.api + '/control/unban/' + id, {}) as Observable<User>;
  }

  public getUser(id: number): Observable<User> {
    return this.http.get(UserService.api + '/' + id) as Observable<User>;
  }

}
