import { Component, OnInit } from '@angular/core';
import { File } from '../../classes/file';
import { FileService } from '../../services/file.service';

@Component({
  selector: 'app-file-list-view',
  templateUrl: './file-list-view.component.html',
  styleUrls: ['./file-list-view.component.css'],
    providers: [FileService]
})
export class FileListViewComponent implements OnInit {
    private files: File[];
  constructor(private fileService: FileService) { }

  ngOnInit() {
      this.fileService.getFiles().subscribe((files) => {
          this.files = files as File[];
      });
  }

}
