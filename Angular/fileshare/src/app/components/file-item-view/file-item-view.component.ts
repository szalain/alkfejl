import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { File } from '../../classes/file';
import { AuthService } from '../../services/auth.service';

@Component({
    selector: 'app-file-item-view',
    templateUrl: './file-item-view.component.html',
    styleUrls: ['./file-item-view.component.css']
})
export class FileItemViewComponent implements OnInit {
    @Input()
    public file: File;


    constructor() { }

    ngOnInit() {
    }

}
