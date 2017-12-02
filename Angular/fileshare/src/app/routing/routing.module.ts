import { NgModule } from '@angular/core';
import { RouterModule as NgRouterModule, Routes } from '@angular/router';
import {FileListViewComponent} from '../components/file-list-view/file-list-view.component';

const routes: Routes = [
    { path: 'listFiles', component: FileListViewComponent}
];

@NgModule({
    imports: [
        NgRouterModule.forRoot(routes)
    ],
    exports: [
        NgRouterModule
    ],
    declarations: []
})
export class RouterModule { }
