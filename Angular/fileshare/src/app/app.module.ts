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


@NgModule({
  declarations: [
    AppComponent,
    ReportComponent,
    IndexViewComponent,
    LoginViewComponent,
    RegisterViewComponent,
    ReportListViewComponent,
    ReportItemViewComponent
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
