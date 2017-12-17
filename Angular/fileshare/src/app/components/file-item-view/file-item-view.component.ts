import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import {File as VirtualFile} from '../../classes/file';
import { AuthService } from '../../services/auth.service';
import {FileService} from '../../services/file.service';

@Component({
    selector: 'app-file-item-view',
    templateUrl: './file-item-view.component.html',
    styleUrls: ['./file-item-view.component.css'],
    providers: [FileService]
})
export class FileItemViewComponent implements OnInit {
    @Input()
    public file: VirtualFile;

    constructor(private fileService: FileService) { }

    ngOnInit() {
    }

    public download(): void {
        var fileName = this.file.fileName;
        var pieces = fileName.split('.');
        var ext = pieces[pieces.length-1].toLowerCase();
        var mime = this.fileService.getMIMEtype(ext);
        this.fileService.downloadFile(this.file.fullPath).subscribe(result => {
            var blob = new Blob([result], {type: mime})
            var url= window.URL.createObjectURL(blob);
            var a = document.createElement("a");
            document.body.appendChild(a);
            a.href = url;
            a.download = fileName;
            a.click();
            window.URL.revokeObjectURL(url);
        });
    }
}
