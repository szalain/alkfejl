import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from './routing/routing.module';

import { AppComponent } from './app.component';
import { ReportComponent } from './components/report/report.component';
import { FileListViewComponent } from './components/file-list-view/file-list-view.component';


@NgModule({
  declarations: [
    AppComponent,
    ReportComponent,
    FileListViewComponent
  ],
  imports: [
    BrowserModule,
      RouterModule,
      HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
