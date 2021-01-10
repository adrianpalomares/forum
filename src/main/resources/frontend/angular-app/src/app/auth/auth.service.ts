import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class AuthService {
    apiUrl: string = 'api/auth';

    constructor(private httpClient: HttpClient) {}

    login(username: String, password: String) {
        return this.httpClient.post<any>(`${this.apiUrl}/login`, {
            username: username,
            password: password,
        });
    }
}
