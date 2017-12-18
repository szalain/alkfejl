import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {FileItemViewComponent} from '../file-item-view/file-item-view.component';
import {File as VirtualFile} from '../../classes/file';
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
    private file: VirtualFile;
    private files: VirtualFile[];
  private path: string;
   private decodedPath: string;
  private comments: Comment[];
  private fileID: number;
private fileItemView: FileItemViewComponent;
private addCommentComponent: AddCommentComponent;
private comment: Comment;
private uploadFileComponent: UploadFileComponent;
private createDirComponent: CreateDirComponent;
public backPath: string;
  constructor(private fileService: FileService, private commentService: CommentService) {}

  ngOnInit() {
      this.path = window.location.pathname;
      this.decodedPath = decodeURIComponent(this.path);
      if (this.path.startsWith('/showFile')) {
          this.path = this.path.replace('/showFile', '');
          this.decodedPath = this.decodedPath.replace('/showFile', '');

          this.fileService.getFile(this.path).subscribe((file) => {
              this.file = file as VirtualFile;
              this.fileID = file.id;
              this.backPath = '/listFiles' + file.path;
          });
          this.commentService.getComments(this.path).subscribe((comments) => {
              this.comments = comments as Comment[];
          });

      }

      if (this.path.startsWith('/listFiles')) {
          this.path = this.path.replace('/listFiles', '');
          this.decodedPath = this.decodedPath.replace('/listFiles', '');
          this.fileService.getFiles(this.path + '/').subscribe((files) => {
              this.files = files as VirtualFile[];
              this.files.forEach(f => {
                  if (f.fileName.charAt(f.fileName.length - 1) === '/') {
                      f.isDir = true;
                  } else {
                      f.isDir = false;
                  }
              });
          });
          let paths = this.decodedPath.split('/');
          this.backPath = '/listFiles' + this.decodedPath.replace(paths[paths.length-1], '');
      }

  }
    public delFile(fullPath: string): void {
        this.fileService.delFile(fullPath);
        this.files.splice(this.files.indexOf(this.files.find(x => x.fullPath === fullPath)), 1);

    }

    public uploadFile(path: string, file: File): void {
      this.fileService.uploadFile(path, file);
    }

    public createDir(path: string, name: string) {
        this.fileService.createDir(path, name);
    }

}
