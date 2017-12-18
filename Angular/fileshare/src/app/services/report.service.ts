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

  public createReport(reported: User, description: string): Observable<Report> {
    return this.http.post(ReportService.api, {reported, description}) as Observable<Report>;
  }

  public deleteReport(id: number) {
    return this.http.delete(ReportService.api + '/delete/' + id);
  }

  public banUser(id: number) {
    return this.http.delete(ReportService.api + '/ban/' + id);
  }
}
