import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, tap, throwError } from 'rxjs';
import { CustomResponse } from '../interface/custom-response';
import { Category } from '../interface/category';


@Injectable({ providedIn: 'root'})
export class ApiService {
    private readonly apiUrl = 'http://localhost:8080';

    // inject HttpClient into category service
    constructor(private http: HttpClient) { }

    public getCategories(): Observable<any> {
        return this.http.get(`${this.apiUrl}/categories/`);
    }

    public makeCategory(category: Category): Observable<any> {
        return this.http.post<Category>(`${this.apiUrl}/category/`, category);
    }

    handleError(handleError: any): Observable<never> {
        return throwError(() => new Error('Method not implemented.'))
    }

}