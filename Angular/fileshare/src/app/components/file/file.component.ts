import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { File } from '../../classes/file';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-file',
  templateUrl: './file.component.html',
  styleUrls: ['./file.component.css']
})
export class FileComponent implements OnInit {
    @Input()
    public file: File;


  constructor() { }

  ngOnInit() {
  }

}
