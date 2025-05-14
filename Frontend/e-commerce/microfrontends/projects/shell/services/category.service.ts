import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Category } from '../models/category';
@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private apiUrl = 'http://localhost:8096/category';

  constructor(private http:HttpClient) { }

  getCategories(): Observable<Category[]> {
    return this.http.get<any[]>(`${this.apiUrl}/list`).pipe(
      map(data => data.map(item => Category.fromJson(item)))
    );
  }
}
