import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateuserComponent } from './update-user.component';

describe('UpdateUserComponent', () => {
  let component: UpdateuserComponent;
  let fixture: ComponentFixture<UpdateuserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateuserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateuserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
