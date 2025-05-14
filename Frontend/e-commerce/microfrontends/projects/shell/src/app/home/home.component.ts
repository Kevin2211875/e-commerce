import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common'; // Importar para usar *ngFor, *ngIf, etc.
import { HttpClientModule } from '@angular/common/http';
import { Product } from '../../../models/product';
import { ProductService } from '../../../services/product.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, HttpClientModule, RouterModule], // Asegúrate de agregar RouterModule aquí
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  categories: any[] = [];
  products: Product[] = [];

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.obtenerProductos();
  }


  obtenerProductos(): void {
    this.productService.getAllProducts().subscribe({
      next: (data) => {
        console.log('Productos obtenidos:', data); // Debug
        this.products = data;
      },
      error: (err) => console.error('Error al obtener productos:', err)
    });
  }
}
