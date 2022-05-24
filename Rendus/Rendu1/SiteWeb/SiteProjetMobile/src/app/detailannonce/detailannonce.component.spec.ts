import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailannonceComponent } from './detailannonce.component';

describe('DetailannonceComponent', () => {
  let component: DetailannonceComponent;
  let fixture: ComponentFixture<DetailannonceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailannonceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailannonceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
