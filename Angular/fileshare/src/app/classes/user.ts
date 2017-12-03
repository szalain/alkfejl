export class User {

    private id: number;
    private username: string;
    private email: string;
    private uploadCount: number;
    private isBanned: boolean;

    public constructor(id: number, username: string, email: string, uploadCount: number, isBanned: boolean) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.uploadCount = uploadCount;
        this.isBanned = isBanned;
    }
    /*
      public get id(): number {
        return this._id;
      }

      public get username(): string {
        return this._username;
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

      public set username(value: string) {
            this._username = value;
        }

      /*public toString(): string {
        return this.text;
      }*/
}
