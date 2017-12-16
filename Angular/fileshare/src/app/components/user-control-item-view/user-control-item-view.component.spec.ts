import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserControlItemViewComponent } from './user-control-item-view.component';

describe('UserControlItemViewComponent', () => {
  let component: UserControlItemViewComponent;
  let fixture: ComponentFixture<UserControlItemViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserControlItemViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserControlItemViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
