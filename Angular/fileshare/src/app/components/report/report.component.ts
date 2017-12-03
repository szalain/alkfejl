import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {Router} from '@angular/router';
import {ReportService} from '../../services/report.service';
import {User} from '../../classes/user';

@Component({
    selector: 'app-report',
    templateUrl: './report.component.html',
    styleUrls: ['./report.component.css'],
    providers: [AuthService, ReportService]
})
export class ReportComponent implements OnInit {

    private error: boolean;
    private user: User;
    constructor(
        private authService: AuthService,
        private reportService: ReportService,
        private router: Router
    ) { }

    ngOnInit() {
    }

    private sendReport(username: string, description: string): void {
        this.user = new User(null, username, null, null, null);
        console.log(this.user);
        this.reportService.createReport(this.user, description).subscribe()/*.subscribe(() => {
            this.router.navigate(['/']);
        }, (err) => {
            if (err.status === 401) {
                this.error = true;
            }
        })*/;
    }
}
