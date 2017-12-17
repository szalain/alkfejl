import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Comment} from '../classes/comment';
import {File} from '../classes/file';
import {FileService} from './file.service';
import {Subscription} from 'rxjs/Subscription';
import {Router} from '@angular/router';

@Injectable()
export class CommentService {
private path: string;
    private file: File;
    constructor(private http: HttpClient, private r: Router) {

    }

    public getComments(path: String): Observable<Comment[]> {
        // let comments: Comment[];
        const commentStream = this.http.get('http://localhost:4200/api/showFile' + path + '/comments') as Observable<Comment[]>;
        return commentStream;
    }

    public addComment(path: string, text: string, file: File): Subscription {
        const formData: FormData = new FormData();
        formData.append('comment', text);
        const subscription = this.http.post('http://localhost:4200/api/showFile' + file.fullPath + '/comment', formData, {responseType: 'text'}).subscribe(result => console.log(result));
        setTimeout((router) => {
            this.r.navigateByUrl('/showFile' + path);
        }, 500);
        return subscription;
    }
    public delComment(id: number): Subscription {
        this.path = window.location.pathname;
        this.path = this.path.replace('/showFile', '');
        const subscription = this.http.delete('http://localhost:4200/api/showFile' + this.path + '/comments/' + id, { responseType: 'text' }).subscribe(result => console.log(result));
        setTimeout((router) => {
            this.r.navigateByUrl('/showFile' + this.path);
        }, 500);
        return subscription;
    }
}
