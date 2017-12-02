import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UrlresolverComponent } from './urlresolver.component';

describe('UrlresolverComponent', () => {
  let component: UrlresolverComponent;
  let fixture: ComponentFixture<UrlresolverComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UrlresolverComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UrlresolverComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
