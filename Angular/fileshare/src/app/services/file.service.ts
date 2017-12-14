import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { File as VirtualFile} from '../classes/file';
import {Subscription} from 'rxjs/Subscription';
import {Router} from '@angular/router';
import {UrlresolverComponent} from '../components/urlresolver/urlresolver.component';

@Injectable()
export class FileService {
    private static api = 'http://localhost:4200/api/showFile/';
    private path: string;
    private virtualFile: VirtualFile;

  constructor(private http: HttpClient, private r: Router) { }

    public getFile(fullPath: String) : Observable < VirtualFile > {
      const fileStream = this.http.get('http://localhost:4200/api/showFile' + fullPath + '/file') as Observable<VirtualFile>;

      return fileStream;
    };

    public getFiles(path: String) : Observable < VirtualFile[] > {

        const fileStream = this.http.get('http://localhost:4200/api/listFiles' + path) as Observable<VirtualFile[]>;
        return fileStream;
    };

    public uploadFile(path: string, file: File) :Subscription {

        const formData: FormData = new FormData();
        formData.append('file', file);
        const subscription = this.http.post('http://localhost:4200/api/listFiles' + path + '/upload', formData).subscribe(result => console.log(result));
        setTimeout((r) => {
            this.r.navigateByUrl('/listFiles' + path);
        }, 500);
        return subscription;
  }

    public delFile(fullPath: string) : Subscription {
        this.path = window.location.pathname;
        this.path = this.path.replace('/listFiles', '');
        const subscription = this.http.delete('http://localhost:4200/api/listFiles' + fullPath, { responseType: 'text' }).subscribe(result => console.log(result));
        return subscription;
    }

    public createDir(path: string, name: string) : Subscription {
        const formData: FormData = new FormData();
        formData.append('name', name);
        const subscription = this.http.post('http://localhost:4200/api/listFiles' + path + '/createDir', formData, { responseType: 'text' }).subscribe(result => console.log(result));
        setTimeout((r) => {
            this.r.navigateByUrl('/listFiles' + path);
        }, 500);
        return subscription;
    }
}
