import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditerannonceComponent } from './editerannonce.component';

describe('EditerannonceComponent', () => {
  let component: EditerannonceComponent;
  let fixture: ComponentFixture<EditerannonceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditerannonceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditerannonceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
