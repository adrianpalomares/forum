import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';

import { LoginComponent } from './login.component';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginComponent ],
      imports: [HttpClientModule, ReactiveFormsModule, FormsModule],
      providers: []
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('empty form should be invalid', () => {
    expect(component.form.valid).toBeFalsy()
  })

  it('Should check for proper email format', () => {
    // Setting invalid email
    component.form.setValue({
      email: "improperemail.com", // not valid
      password: "password"
    })
    let email = component.form.controls['email'];
    expect(email.valid).toBeFalse();
    
    component.form.setValue({
      email: "email@email.com",
      password: "password"
    })
    expect(email.valid).toBeTrue();
  })

});
