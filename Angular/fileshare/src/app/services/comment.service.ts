import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Comment} from '../classes/comment';
import {File} from '../classes/file';
import {FileService} from './file.service';
import {Subscription} from 'rxjs/Subscription';

@Injectable()
export class CommentService {
private path: string;
    private file: File;
    constructor(private http: HttpClient) {

    }

    public getComments(path: String): Observable<Comment[]> {
        let comments: Comment[];
        const commentStream = this.http.get('http://localhost:8080/api/showFile' + path + '/comments') as Observable<Comment[]>;
        return commentStream;
    }

    public addComment(path: string, text: string, file: File): Subscription {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        console.log(file);
        const comment = new Comment(file, text, file.owner, new Date()); //commented user is currently the owner of the file
        console.log(comment);
        const formData: FormData = new FormData();
        formData.append('comment', text);
        return this.http.post('http://localhost:8080/api/showFile' + file.fullPath + '/comment', formData, {
            headers: new HttpHeaders().set('Content-Type', 'application/json')
        }).subscribe();
    }
}
