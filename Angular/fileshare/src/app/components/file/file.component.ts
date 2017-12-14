import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { File } from '../../classes/file';
import { AuthService } from '../../services/auth.service';
import {FileService} from '../../services/file.service';

@Component({
  selector: 'app-file',
  templateUrl: './file.component.html',
  styleUrls: ['./file.component.css'],
    providers: [FileService, AuthService]
})
export class FileComponent implements OnInit {
    @Input()
    public file: File;

    @Output()
    public delFile: EventEmitter<string> = new EventEmitter();

    public clickButton($event: Event): void {
        $event.preventDefault();
        $event.stopPropagation();
        this.delFile.emit(this.file.fullPath);
    }

  constructor(private fileService: FileService, private authService: AuthService) { }

  ngOnInit() {
  }

}
