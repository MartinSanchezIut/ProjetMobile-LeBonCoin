import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListeannonceComponent } from './listeannonce.component';

describe('ListeannonceComponent', () => {
  let component: ListeannonceComponent;
  let fixture: ComponentFixture<ListeannonceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListeannonceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListeannonceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
