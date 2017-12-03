import {Component, Input, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {Report} from '../../classes/report';
import {ReportService} from '../../services/report.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-report-item-view',
  templateUrl: './report-item-view.component.html',
  styleUrls: ['./report-item-view.component.css'],
    providers: [AuthService, ReportService]
})
export class ReportItemViewComponent implements OnInit {

  @Input()
  public report: Report;

  constructor(
      private authService: AuthService,
      private reportService: ReportService,
      private router: Router
  ) { }

  ngOnInit() {
  }

  private delReport(id: number): void {
    this.reportService.deleteReport(id).subscribe(() => this.router.navigate(['/report/list']));
  }

  private banUserFromReport(id: number): void {
    this.reportService.banUser(id).subscribe(() => this.router.navigate(['/report/list']));
  }
}
