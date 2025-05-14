import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Review } from '../models/review';
import { Product } from '../models/product';
@Injectable({
  providedIn: 'root'
})
export class ReviewService {
    private apiUrl = 'http://localhost:8096/product';

    constructor(private http:HttpClient) { }

    getReviews(productId: string): Observable<Review[]> {
        return this.http.get<Review[]>(`${this.apiUrl}/${productId}/reviews`);
      }
    addReview(productId: string, review: Review): Observable<Product> {
        return this.http.post<Product>(`${this.apiUrl}/${productId}/reviews`, review);
      }
    updateReview(productId: string, userId: string, review: Review): Observable<Product> {
        return this.http.put<Product>(`${this.apiUrl}/${productId}/reviews/${userId}`, review);
      }
    deleteReview(productId: string, userId: string): Observable<Product> {
        return this.http.delete<Product>(`${this.apiUrl}/${productId}/reviews/${userId}`);
      }
      
}
