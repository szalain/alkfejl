import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { File } from '../classes/file';

@Injectable()
export class FileService {
    private static api = 'http://localhost:4200/api/listFiles/';

  constructor(private http: HttpClient) { }

    public getFiles(): Observable<File[]> {

        const fileStream = this.http.get(FileService.api) as Observable<File[]>;
        return fileStream;
    }
}
