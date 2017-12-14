export class Role {
    static GUEST = 'GUEST';
    static USER = 'USER';
    static MOD = 'MOD';
    static ADMIN = 'ADMIN';
}

export class User {

    id: number;
    username: string;
    email: string;
    uploadCount: number;
    isBanned: boolean;
    role: string;

    public constructor(id?: number, username?: string, email?: string, uploadCount?: number, isBanned?: boolean, role?: string) {
        this.id = id || null;
        this.username = username || null;
        this.email = email || null;
        this.uploadCount = uploadCount || null;
        this.isBanned = isBanned || null;
        this.role = role || Role.GUEST;
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
