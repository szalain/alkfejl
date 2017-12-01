import {File} from "./file";
import {User} from "./user";

export class Comment {

  private _id: number;
  private _file: File;
  private _text: string;
  private _user: User;
  private _date: Date;

  public constructor(id: number, file: File, text: string, user: User, date: Date) {
    this._id = id;
    this._file = file;
    this._text = text;
    this._user = user;
    this._date = date;
  }

  public get id(): number {
    return this._id;
  }

  public get file(): File {
    return this._file;
  }

  public get text(): string {
    return this._text;
  }

  public get user(): User {
    return this._user;
  }

  public get date(): Date {
    return this._date;
  }
}
