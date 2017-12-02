import { Injectable } from '@angular/core';
import {AuthService} from "./auth.service";
import {Observable} from "rxjs/Observable";
import {User} from "../classes/user";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class UserService {

  private static api: string = 'http://localhost:8080/api/user';

  constructor(
    private http: HttpClient
  ) { }

  public registerUser(username: string, email:string, password: string): Observable<User> {
    return this.http.post(UserService.api + '/register', {username, email, password}) as Observable<User>;
  }



  //TODO: Backendben még hiányzik ez a végpont
  public getUser(id: number): Observable<User> {
    return this.http.get(UserService.api + '/' + id) as Observable<User>;
  }

}
