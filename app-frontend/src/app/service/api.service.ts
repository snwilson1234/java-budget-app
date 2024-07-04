import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, tap, throwError } from 'rxjs';
import { CustomResponse } from '../interface/custom-response';


@Injectable({ providedIn: 'root'})
export class ApiService {
    private readonly apiUrl = 'http://localhost:8080';

    // inject HttpClient into category service
    constructor(private http: HttpClient) { }

    // categories$ = <Observable<CustomResponse>>
    // this.http.get<CustomResponse>(`${this.apiUrl}/categories/`)
    // .pipe(
    //     tap(console.log),
    //     catchError(this.handleError)
    // );

    public getCategories(): Observable<any> {
        return this.http.get(`${this.apiUrl}/categories/`);
    }

    handleError(handleError: any): Observable<never> {
        return throwError(() => new Error('Method not implemented.'))
    }

}