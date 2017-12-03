import {User} from './user';

export class Report {

  private _id: number;
  private _reported: User;
  private _description: string;
  private _date: Date;

  public constructor(id: number, reported: User, description: string, date: Date) {
    this._id = id;
    this._reported = reported;
    this._description = description;
    this._date = date;
  }

  public get id(): number {
    return this._id;
  }

  public get reported(): User {
    return this._reported;
  }

  public get description(): string {
    return this._description;
  }

  public get date(): Date {
    return this._date;
  }
}
