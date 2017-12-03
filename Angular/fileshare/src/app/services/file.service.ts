import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { File } from '../classes/file';

@Injectable()
export class FileService {
    private static api = 'http://localhost:4200/api/showFile/';

  constructor(private http: HttpClient) { }

    public getFile(fullPath: String): Observable<File> {
      const fileStream = this.http.get('http://localhost:4200/api/showFile' + fullPath + '/file') as Observable<File>;
      console.log(fileStream)
      return fileStream;
    }

    public getFiles(path: String): Observable<File[]> {

        const fileStream = this.http.get('http://localhost:4200/api/listFiles' + path) as Observable<File[]>;
        return fileStream;
    }
}
