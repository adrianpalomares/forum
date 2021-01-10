import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { PostsModule } from '../posts/posts.module';
import { ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../auth/auth.service';



@NgModule({
  declarations: [HomeComponent, LoginComponent, RegisterComponent],
  imports: [
    CommonModule,
    PostsModule,
    ReactiveFormsModule
  ],
  providers: [AuthService]
})
export class PagesModule { }
