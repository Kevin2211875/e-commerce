import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from '../../../models/product';
import { ProductService } from '../../../services/product.service';
import { ReviewService } from '../../../services/review.service';  // Importar el servicio de reseñas
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})

export class ProductComponent implements OnInit {
  product: Product = new Product();
  averageRating: number = 0;
  relatedProducts: Product[] = [];

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    private reviewService: ReviewService  // Inyectar el servicio de reseñas
  ) {}

  ngOnInit(): void {
    const productId = this.route.snapshot.paramMap.get('id');
    if (productId) {
      this.loadProduct(productId);
    }
  }

  loadProduct(id: string): void {
    this.productService.getProductById(id).subscribe({
      next: (data) => {
        this.product = data;
        this.loadReviews(id);  // Cargar reseñas después de cargar el producto
        this.loadRelatedProducts(data.category, data.id);
      },
      error: (err) => console.error('Error al cargar producto:', err)
    });
  }

  // Función para cargar las reseñas
  loadReviews(productId: string): void {
    this.reviewService.getReviews(productId).subscribe({
      next: (reviews) => {
        this.product.reviews = reviews;  // Asignar las reseñas al producto
        this.calculateAverageRating();  // Calcular el promedio de la calificación
      },
      error: (err) => console.error('Error al cargar reseñas:', err)
    });
  }

  calculateAverageRating(): void {
    if (this.product?.reviews?.length) {
      const total = this.product.reviews.reduce((sum, r) => sum + r.rating, 0);
      this.averageRating = total / this.product.reviews.length;
    } else {
      this.averageRating = 0;
    }
  }

  getStars(): number[] {
    return Array(Math.round(this.averageRating)).fill(0);
  }

  getEmptyStars(): number[] {
    return Array(5 - Math.round(this.averageRating)).fill(0);
  }

  loadRelatedProducts(category: string, excludeId: string): void {
    this.productService.getAllProducts().subscribe({
      next: (products) => {
        this.relatedProducts = products
          .filter(p => p.category === category && p.id !== excludeId)
          .slice(0, 3); // Máximo 3
      },
      error: (err) => console.error('Error al cargar productos relacionados:', err)
    });
  }
}
