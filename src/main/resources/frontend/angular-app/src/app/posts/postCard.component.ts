import { Component, Input, OnInit } from '@angular/core';
import { Post } from './post.model';

@Component({
    selector: 'app-postcard',
    templateUrl: './postCard.component.html',
})
export class PostCardComponent  {
    @Input() post: Post;
    constructor() {}
    
}
