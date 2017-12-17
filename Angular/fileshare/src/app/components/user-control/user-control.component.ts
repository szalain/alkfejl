import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../../services/user.service';
import {Role, User} from '../../classes/user';

@Component({
  selector: 'app-user-control',
  templateUrl: './user-control.component.html',
  styleUrls: ['./user-control.component.css'],
  providers: [UserService]
})

export class UserControlComponent implements OnInit {
    private user = new User();
    private error = false;
    private success = false;

    constructor(
        private userService: UserService,
        private router: Router
    ) { }

    ngOnInit() {
    }

    private findUser(username: string): void {
        this.error = false;
        this.user = new User();
        this.userService.findUser(username).subscribe((user) => {this.user = user; console.log(this.user.role); },
            (err) => {
                if (err.status === 404) {
                    this.error = true;
                }
            });
    }

    private modifyUser(role: Role, id: number): void {
        this.success = true;
        this.userService.modifyRole(role, id).subscribe(() => {
            setTimeout((router) => {
                this.router.navigate(['/user/control']);
            }, 1000); } );
    }

    private banUser(id: number): void {
        this.success = true;
        this.userService.banUser(id).subscribe(() => {
            setTimeout((router) => {
                this.router.navigate(['/user/control']);
            }, 1000); } );
    }

    private unbanUser(id: number): void {
        this.success = true;
        this.userService.unbanUser(id).subscribe(() => {
            setTimeout((router) => {
                this.router.navigate(['/user/control']);
            }, 1000); } );
    }

}
