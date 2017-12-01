import {User} from "./user";

export class File {

  private _id: number;
  private _fullPath: string;
  private _path: string;
  private _fileName: string;
  private _editLevel: number;
  private _viewLevel: number;
  private _owner: User;
  private _isDir: boolean;

  constructor(id: number, fullPath: string, path: string, fileName: string, editLevel: number, viewLevel: number, owner: User, isDir: boolean) {
    this._id = id;
    this._fullPath = fullPath;
    this._path = path;
    this._fileName = fileName;
    this._editLevel = editLevel;
    this._viewLevel = viewLevel;
    this._owner = owner;
    this._isDir = isDir;
  }


  public get id(): number {
    return this._id;
  }

  public get fullPath(): string {
    return this._fullPath;
  }

  public get path(): string {
    return this._path;
  }

  public get fileName(): string {
    return this._fileName;
  }

  public get editLevel(): number {
    return this._editLevel;
  }

  public get viewLevel(): number {
    return this._viewLevel;
  }

  public get owner(): User {
    return this._owner;
  }

  public get isDir(): boolean {
    return this._isDir;
  }
}
