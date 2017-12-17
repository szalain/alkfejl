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

    private error = false;
    private success = false;
    private user: User;
    constructor(
        private authService: AuthService,
        private reportService: ReportService,
        private router: Router
    ) { }

    ngOnInit() {
    }

    private sendReport(username: string, description: string): void {
        this.error = false;
        this.user = new User(null, username, null, null, null);
        console.log(this.user);
        this.reportService.createReport(this.user, description).subscribe(() => {
            // this.router.navigate(['/']);
            this.success = true;
        }, (err) => {
            if (err.status === 400) {
                this.error = true;
            } else if (err.status === 401) {
                this.router.navigate(['/']);
            }
        });
    }
}
