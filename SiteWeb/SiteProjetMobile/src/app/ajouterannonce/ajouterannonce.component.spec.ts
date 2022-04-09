import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjouterannonceComponent } from './ajouterannonce.component';

describe('AjouterannonceComponent', () => {
  let component: AjouterannonceComponent;
  let fixture: ComponentFixture<AjouterannonceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AjouterannonceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AjouterannonceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
