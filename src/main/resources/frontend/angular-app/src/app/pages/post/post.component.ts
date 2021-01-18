import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommentService } from 'src/app/comments/comments.service';
import { Post } from 'src/app/posts/post.model';
import { PostService } from 'src/app/posts/posts.service';

@Component({
    selector: 'app-post',
    templateUrl: './post.component.html',
    styleUrls: ['./post.component.css'],
})
export class PostComponent implements OnInit {
    post: Post;
    comments: Comment[];

    constructor(
        private route: ActivatedRoute,
        private postService: PostService,
        private commentService: CommentService
    ) {}

    ngOnInit(): void {
        console.log(this.route.snapshot.params);
        // Grab the post
        this.postService
            .getPostById(this.route.snapshot.params.id)
            .subscribe((res) => {
                console.log(res);
                this.post = res;
                // Then grab the comments
                this.commentService.getCommentsByPost(this.post).subscribe(
                    (comments) => {
                        this.comments = comments;
                        console.log((this.comments.length));
                    },
                    (err) => console.log(err)
                );
            });
    }
}
