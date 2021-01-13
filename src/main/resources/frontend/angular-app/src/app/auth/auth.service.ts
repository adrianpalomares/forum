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

    isLoggedIn() {
        const token = localStorage.getItem('token');
        return token != null;
    }

    logout() {
        localStorage.removeItem('token');
    }

    register(username: String, password: String, email: String) {
        return this.httpClient.post<any>(
            `${this.apiUrl}/register`,
            {
                username: username,
                password: password,
                email: email,
            },
            { observe: 'response',}
        );
    }
}
