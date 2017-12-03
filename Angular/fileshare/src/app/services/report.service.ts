import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Report} from '../classes/report';
import {User} from '../classes/user';

@Injectable()
export class ReportService {

    private static api = 'http://localhost:4200/api/report';

  constructor(
    private http: HttpClient
  ) { }

  public getReports(): Observable<Report[]> {
    return this.http.get(ReportService.api + '/list') as Observable<Report[]>;
  }

  // TODO: hibakezelés nemlétező user esetén
  public createReport(reported: User, description: string): Observable<Report> {
    return this.http.post(ReportService.api, {reported, description}) as Observable<Report>;
  }

  public deleteReport(id: number) {
    return this.http.delete(ReportService.api + '/delete/' + id);
  }

  public banUser(id: number) {
    return this.http.delete(ReportService.api + '/ban/' + id);
  }

  /*public getTodo(id: number): Observable<Todo> {
    //return TodoService._data.find((todo) => todo.id === id);
    let todoStream = this.http.get(TodoService.api + '/' + id) as Observable<Todo>;
    return todoStream;
  }

  public delTodoById(id: number): Observable<any> {
    //let idx: number = TodoService._data.findIndex((todo) => todo.id === id);
    //TodoService._data.splice(idx, 1);
    return this.http.delete(TodoService.api + '/' + id);
  }

  public delTodo(todo: Todo) {
    //this.delTodoById(todo.id);
  }*/


}
