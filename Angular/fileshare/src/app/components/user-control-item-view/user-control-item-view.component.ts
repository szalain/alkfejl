import {Component, Injectable, OnInit} from '@angular/core';
import {User} from "../../classes/user";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-control-item-view',
  templateUrl: './user-control-item-view.component.html',
  styleUrls: ['./user-control-item-view.component.css'],
  providers: [UserService]
})

export class UserControlItemViewComponent implements OnInit {

  private user: User;
  private error = false;

  constructor(
      private userService: UserService,
      private router: Router
  ) { }

  ngOnInit() {
  }

    private findUser(username: string): void {
        this.error = false;
        this.user = new User();
        this.userService.findUser(username).subscribe((user) => {this.user = user; },
            (err) => {
                if (err.status === 404) {
                    this.error = true;
                }
            });
    }

}
