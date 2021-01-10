import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { PagesModule } from './pages/pages.module';
import { PostsModule } from './posts/posts.module';
import { CommentService } from './comments/comments.service';
import { PostService } from './posts/posts.service';

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    PagesModule,
    PostsModule,
  ],
  providers: [CommentService, PostService],
  bootstrap: [AppComponent]
})
export class AppModule { }
