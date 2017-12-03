import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Comment} from '../classes/comment';

@Injectable()
export class CommentService {

    constructor(private http: HttpClient) { }

    public getComments(path: String): Observable<Comment[]> {
        const commentStream = this.http.get('http://localhost:8080/api/showFile' + path + '/comments') as Observable<Comment[]>;
        return commentStream;
    }
}
