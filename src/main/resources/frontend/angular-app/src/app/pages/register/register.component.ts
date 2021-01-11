import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AuthService } from 'src/app/auth/auth.service';

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
    form: FormGroup;
    constructor(private fb: FormBuilder, private authService: AuthService) {}

    ngOnInit(): void {
        this.form = this.fb.group({

        });
    }
}
