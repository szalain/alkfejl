import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { ReportComponent } from './components/report/report.component';
import { IndexViewComponent } from './components/index-view/index-view.component';
import {RouterModule} from "@angular/router";
import {HttpClientModule} from "@angular/common/http";
import {RoutingModule} from "./routing/routing.module";
import { LoginViewComponent } from './components/login-view/login-view.component';
import { RegisterViewComponent } from './components/register-view/register-view.component';
import { ReportListViewComponent } from './components/report-list-view/report-list-view.component';
import { ReportItemViewComponent } from './components/report-item-view/report-item-view.component';
import { FileListViewComponent } from './components/file-list-view/file-list-view.component';
import { FileComponent } from './components/file/file.component';
import { FileItemViewComponent } from './components/file-item-view/file-item-view.component';
import { UrlresolverComponent } from './components/urlresolver/urlresolver.component';


@NgModule({
  declarations: [
    AppComponent,
    ReportComponent,
    IndexViewComponent,
    LoginViewComponent,
    RegisterViewComponent,
    ReportListViewComponent,
    ReportItemViewComponent,
    FileListViewComponent,
    FileComponent,
    FileItemViewComponent,
    UrlresolverComponent
  ],
  imports: [
    BrowserModule,
    RoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
