import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportItemViewComponent } from './report-item-view.component';

describe('ReportItemViewComponent', () => {
  let component: ReportItemViewComponent;
  let fixture: ComponentFixture<ReportItemViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReportItemViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportItemViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
