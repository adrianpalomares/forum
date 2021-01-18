import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Post } from 'src/app/posts/post.model';
import { PostService } from 'src/app/posts/posts.service';

@Component({
    selector: 'app-post',
    templateUrl: './post.component.html',
    styleUrls: ['./post.component.css'],
})
export class PostComponent implements OnInit {
    post: Post;

    constructor(
        private route: ActivatedRoute,
        private postService: PostService
    ) {}

    ngOnInit(): void {
        console.log(this.route.snapshot.params);
        // Grab the post
        this.postService
            .getPostById(this.route.snapshot.params.id)
            .subscribe((res) => {
                console.log(res);
                this.post = res;
            });
    }
}
