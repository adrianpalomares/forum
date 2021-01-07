import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Post } from '../model';
@Injectable()
export class PostService {
  // Url
  apiUrl: string = '/api/posts';
  constructor(private httpClient: HttpClient) {}

  public getPosts() {
    return this.httpClient.get<Post[]>(`${this.apiUrl}`);
  }
}
