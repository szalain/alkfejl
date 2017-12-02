import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {FileItemViewComponent} from '../file-item-view/file-item-view.component';
import {File} from '../../classes/file';
import {FileService} from '../../services/file.service';

@Component({
  selector: 'app-urlresolver',
  templateUrl: './urlresolver.component.html',
  styleUrls: ['./urlresolver.component.css'],
    providers: [FileService]
})
export class UrlresolverComponent implements OnInit {
    private file: File;
    private files: File[];
  private path: String;
private fileItemView: FileItemViewComponent;
  constructor(private fileService: FileService) {
  }

  ngOnInit() {
      this.path = window.location.pathname;
      console.log(this.path);
      if (this.path.startsWith('/showFile')) {
          this.path = this.path.replace('/showFile', '');
          console.log(this.path);
          this.fileService.getFile(this.path).subscribe((file) => {
              this.file = file as File;
          });
      }
      if (this.path.startsWith('/listFiles')) {
          this.path = this.path.replace('/listFiles', '');
          console.log(this.path);
          this.fileService.getFiles(this.path + '/').subscribe((files) => {
              this.files = files as File[];

              files.forEach(f => {
                  if (f.fileName.charAt(f.fileName.length - 1) === '/') {
                      f.isDir=true;
                  } else {
                      f.isDir=false;
                  }
              });
          });
      }

  }

}
