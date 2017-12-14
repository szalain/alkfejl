import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import {FileService} from '../../services/file.service';
import {File} from '../../classes/file';
import{UrlresolverComponent} from '../urlresolver/urlresolver.component';

@Component({
  selector: 'app-file-list-view',
  templateUrl: './file-list-view.component.html',
  styleUrls: ['./file-list-view.component.css'],
    providers: [FileService, UrlresolverComponent]
})
export class FileListViewComponent implements OnInit {
    private fullPathToDelete: string;

    @Input()
    public files: File[];

    @Output()
    public delFileEmit: EventEmitter<Event> = new EventEmitter();

    public clickButton($event: Event): void {
        //$event.preventDefault();
        // $event.stopPropagation();
        this.delFileEmit.emit($event);
    }
    private path: string;
    constructor(private fileService: FileService, private urlresolverComponent: UrlresolverComponent) { }

    ngOnInit() {
        this.path = window.location.pathname;
            this.path = this.path.replace('/listFiles', '');
      /*  this.fileService.getFiles(this.path).subscribe((files) => {
            this.files = files as File[];
        });*/
    }
    public delFile(fullPath: string): void {
        this.urlresolverComponent.delFile(fullPath);
    }
}
