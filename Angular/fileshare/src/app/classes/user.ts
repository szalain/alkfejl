export class User {

  private _id: number;
  private _email: string;
  private _uploadCount: number;
  private _isBanned: boolean;

  public constructor(id: number, email: string, uploadCount: number, isBanned: boolean) {
    this._id = id;
    this._email = email;
    this._uploadCount = uploadCount;
    this._isBanned = isBanned;
  }

  public get id(): number {
    return this._id;
  }

  public get email(): string {
    return this._email;
  }

  public get uploadCount(): number {
    return this._uploadCount;
  }

  public get isBanned(): boolean {
    return this._isBanned;
  }

  /*public toString(): string {
    return this.text;
  }*/
}
