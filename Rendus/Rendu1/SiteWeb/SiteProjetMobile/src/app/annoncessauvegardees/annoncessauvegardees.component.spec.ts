import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnnoncessauvegardeesComponent } from './annoncessauvegardees.component';

describe('AnnoncessauvegardeesComponent', () => {
  let component: AnnoncessauvegardeesComponent;
  let fixture: ComponentFixture<AnnoncessauvegardeesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnnoncessauvegardeesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnnoncessauvegardeesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
