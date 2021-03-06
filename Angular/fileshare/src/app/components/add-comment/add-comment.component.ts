import { Component, OnInit } from '@angular/core';
import {CommentService} from '../../services/comment.service';
import {File} from '../../classes/file';
import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';
import {AuthService} from '../../services/auth.service';

@Component({
  selector: 'app-add-comment',
  templateUrl: './add-comment.component.html',
  styleUrls: ['./add-comment.component.css'],
    providers: [CommentService, AuthService]
})
export class AddCommentComponent implements OnInit {
private path: string;
    private file: File;
  constructor(private commentService: CommentService, private http: HttpClient, private authService: AuthService) { }

  ngOnInit() {
      this.path = window.location.pathname;
      this.path = this.path.replace('/showFile', '');
      const fileStream = this.http.get('http://localhost:4200/api/showFile' + this.path + '/file') as Observable<File>;
      fileStream.subscribe((file) => {
          this.file = file as File;
         // console.log(this.file);
      });
  }

  private send(text: string): void {
      this.path = window.location.pathname;
      this.path = this.path.replace('/showFile', '');
      this.commentService.addComment(this.path, text, this.file);
  }

}
