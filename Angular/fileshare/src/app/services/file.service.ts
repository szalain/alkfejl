import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { File as VirtualFile} from '../classes/file';
import {Subscription} from 'rxjs/Subscription';
import {Router} from '@angular/router';

@Injectable()
export class FileService {
    private static api = 'http://localhost:4200/api/showFile/';
    private path: string;

  constructor(private http: HttpClient, private r: Router) { }

    public getFile(fullPath: String): Observable<VirtualFile> {
      const fileStream = this.http.get('http://localhost:4200/api/showFile' + fullPath + '/file') as Observable<VirtualFile>;
      // console.log(fileStream)
      return fileStream;
    }

    public getFiles(path: String): Observable<VirtualFile[]> {

        const fileStream = this.http.get('http://localhost:4200/api/listFiles' + path) as Observable<VirtualFile[]>;
        return fileStream;
    }

    public uploadFile(path: string, file: File): Subscription {
        const formData: FormData = new FormData();
        formData.append('file', file);
        return this.http.post('http://localhost:4200/api/listFiles' + path + '/upload', formData, { responseType: 'text' }).subscribe(result => console.log(result));
    }

    public delFile(fullPath: string): Subscription {
        this.path = window.location.pathname;
        this.path = this.path.replace('/listFiles', '');
        console.log(this.path);
        const subscription = this.http.delete('http://localhost:4200/api/listFiles' + fullPath, { responseType: 'text' }).subscribe(result => console.log(result));
        this.r.navigateByUrl('/listFiles' + this.path);
        return subscription;
    }

    public createDir(path: string, name: string): Subscription {
        const formData: FormData = new FormData();
        formData.append('name', name);
        const subscription = this.http.post('http://localhost:4200/api/listFiles' + path + '/createDir', formData, { responseType: 'text' }).subscribe(result => console.log(result));
        this.r.navigateByUrl('/listFiles' + path);
        return subscription;
    }
}
