import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Comment } from '../../classes/comment';
import { AuthService } from '../../services/auth.service';
import {CommentService} from '../../services/comment.service';
import {HttpClient} from '@angular/common/http';
import {File} from '../../classes/file';

@Component({
    selector: 'app-comment',
    templateUrl: './comment.component.html',
    styleUrls: ['./comment.component.css'],
    providers: [CommentService, AuthService]
})
export class CommentComponent implements OnInit {
    @Input()
    public comment: Comment;

    constructor(private commentService: CommentService, private http: HttpClient, private authService: AuthService) { }

    ngOnInit() {
    }

    public delComment(): void {
        this.commentService.delComment(this.comment.id);
    }

}
