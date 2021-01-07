import { Component, OnInit } from '@angular/core';
import { Post } from '../../posts/post.model';
import { PostService } from '../../posts/posts.service';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  posts: Post[];
  constructor(private postService: PostService) {}

  ngOnInit(): void {
    this.postService.getPosts().subscribe((res) => (this.posts = res));
    console.log(this.posts);
  }
}
