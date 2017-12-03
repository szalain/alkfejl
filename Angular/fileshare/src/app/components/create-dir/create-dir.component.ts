import { Component, OnInit } from '@angular/core';
import {FileService} from '../../services/file.service';
import {File} from '../../classes/file';
import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';
import {AuthService} from '../../services/auth.service';

@Component({
    selector: 'app-create-dir',
    templateUrl: './create-dir.component.html',
    styleUrls: ['./create-dir.component.css'],
    providers: [FileService, AuthService]
})
export class CreateDirComponent implements OnInit {
    private path: string;
    constructor(private fileService: FileService, private http: HttpClient, private authService: AuthService) { }

    ngOnInit() {
    }

    private send(name: string): void {
        this.path = window.location.pathname;
        this.path = this.path.replace('/listFiles', '');
        this.fileService.createDir(this.path, name);
    }

}
