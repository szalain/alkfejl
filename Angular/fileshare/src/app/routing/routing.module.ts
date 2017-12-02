import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {IndexViewComponent} from "../components/index-view/index-view.component";
import {RegisterViewComponent} from "../components/register-view/register-view.component";
import {LoginViewComponent} from "../components/login-view/login-view.component";
import {Report} from "../classes/report";
import {ReportComponent} from "../components/report/report.component";
import {ReportListViewComponent} from "../components/report-list-view/report-list-view.component";
import {FileListViewComponent} from '../components/file-list-view/file-list-view.component';
import {FileItemViewComponent} from '../components/file-item-view/file-item-view.component';
import {UrlresolverComponent} from '../components/urlresolver/urlresolver.component';

const routes: Routes = [
  //végpontok
  { path: '', component: IndexViewComponent },
  { path: 'register', component: RegisterViewComponent },
  { path: 'login', component: LoginViewComponent },
    { path: 'report', component: ReportComponent },
  { path: 'reportlist', component: ReportListViewComponent },
    { path: 'showFile', children: [ { path: '**', component: UrlresolverComponent} ] },
    { path: 'listFiles', children: [ { path: '**', component: UrlresolverComponent} ] },
    {path: 'show', component: FileItemViewComponent},
];

@NgModule({
  imports: [ RouterModule.forRoot(routes)  ],
  exports: [ RouterModule ],
  declarations: []
})
export class RoutingModule { }
