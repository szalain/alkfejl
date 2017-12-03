import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {FileItemViewComponent} from '../file-item-view/file-item-view.component';
import {File} from '../../classes/file';
import {FileService} from '../../services/file.service';
import {CommentService} from '../../services/comment.service';
import {Comment} from '../../classes/comment';
import {AddCommentComponent} from '../add-comment/add-comment.component';
import {UploadFileComponent} from '../upload-file/upload-file.component';
import {CreateDirComponent} from '../create-dir/create-dir.component';

@Component({
  selector: 'app-urlresolver',
  templateUrl: './urlresolver.component.html',
  styleUrls: ['./urlresolver.component.css'],
    providers: [FileService, CommentService]
})
export class UrlresolverComponent implements OnInit {
    private file: File;
    private files: File[];
  private path: String;
  private comments: Comment[];
  private fileID: number;
private fileItemView: FileItemViewComponent;
private addCommentComponent: AddCommentComponent;
private comment: Comment;
private uploadFileComponent: UploadFileComponent;
private createDirComponent: CreateDirComponent;
  constructor(private fileService: FileService, private commentService: CommentService) {
  }

  ngOnInit() {
      this.path = window.location.pathname;
      // console.log(this.path);
      if (this.path.startsWith('/showFile')) {
          this.path = this.path.replace('/showFile', '');
          // console.log(this.path);
          this.fileService.getFile(this.path).subscribe((file) => {
              this.file = file as File;
              this.fileID = file.id;
          });
          this.commentService.getComments(this.path).subscribe((comments) => {
              this.comments = comments as Comment[];
          });
      }
      if (this.path.startsWith('/listFiles')) {
          this.path = this.path.replace('/listFiles', '');
          // console.log(this.path);
          this.fileService.getFiles(this.path + '/').subscribe((files) => {
              this.files = files as File[];

              files.forEach(f => {
                  if (f.fileName.charAt(f.fileName.length - 1) === '/') {
                      f.isDir = true;
                  } else {
                      f.isDir = false;
                  }
              });
          });
      }

  }

}
