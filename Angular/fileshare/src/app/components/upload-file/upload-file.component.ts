import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';
import {CommentService} from '../../services/comment.service';
import {FileService} from '../../services/file.service';
import {Router} from '@angular/router';
import {AuthService} from '../../services/auth.service';

@Component({
  selector: 'app-upload-file',
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.css'],
    providers: [FileService, AuthService]
})
export class UploadFileComponent implements OnInit {
private file: File;
    private path: string;
  constructor(private fileService: FileService, private http: HttpClient, private r : Router, private authService: AuthService) { }

  ngOnInit() {

      this.path = window.location.pathname;
      this.path = this.path.replace('/listFiles', '');
  }
    private send(file: File): void {
       // this.fileService.uploadFile(this.path, file);
    }
    onFileChange(event) {
        if (event.target.files.length > 0) {
            const file = event.target.files[0];
            this.file = file;

        }
    }
    onSubmit() {
    this.fileService.uploadFile(this.path, this.file);
        this.r.navigateByUrl('/listFiles' + this.path);
    }
}
