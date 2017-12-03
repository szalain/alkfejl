import { Component, OnInit } from '@angular/core';
import {ReportService} from "../../services/report.service";
import {Report} from "../../classes/report";

@Component({
  selector: 'app-report-list-view',
  templateUrl: './report-list-view.component.html',
  styleUrls: ['./report-list-view.component.css'],
    providers: [ReportService]
})
export class ReportListViewComponent implements OnInit {

  private reports: Report[];

  constructor(
      private reportService: ReportService
  ) { }

  ngOnInit() {
      this.reportService.getReports().subscribe((reports) => {
          this.reports = reports as Report[];
      });
  }

}
