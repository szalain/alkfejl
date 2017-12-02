import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FileItemViewComponent } from './file-item-view.component';

describe('FileItemViewComponent', () => {
  let component: FileItemViewComponent;
  let fixture: ComponentFixture<FileItemViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FileItemViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FileItemViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
