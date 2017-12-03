import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Comment } from '../../classes/Comment';
import { AuthService } from '../../services/auth.service';

@Component({
    selector: 'app-comment',
    templateUrl: './comment.component.html',
    styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {
    @Input()
    public comment: Comment;


    constructor() { }

    ngOnInit() {
    }

}
