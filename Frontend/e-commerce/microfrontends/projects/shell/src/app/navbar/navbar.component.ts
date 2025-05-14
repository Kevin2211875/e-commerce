import { Component, OnInit } from '@angular/core';
import { Category } from '../../../models/category';
import { CategoryService } from '../../../services/category.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})

export class NavbarComponent implements OnInit {

  selectedCategory: String = '';
  categories: Category[] = [];

  constructor(private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.obtenerCategorias();
  }


  // Contador de productos en el carrito
  cartCount: number = 0;

  // Función para agregar productos al carrito
  addToCart() {
    this.cartCount++;  // Aumenta el número de productos en el carrito
    this.updateCartCount();  // Actualiza el contador en la vista
  }

  // Función para actualizar el contador
  updateCartCount() {
    if (this.cartCount > 0) {
      // El contador será visible si hay productos en el carrito
      // No se necesita hacer nada aquí si ya estamos usando la interpolación en el HTML
    } else {
      // Si no hay productos, se ocultan los elementos del carrito
      this.cartCount = 0;
    }
  }
  obtenerCategorias(): void {
    this.categoryService.getCategories().subscribe({
      next: (data: Category[]) => {
        this.categories = data;
      },
      error: (error) => {
        console.error('Error al obtener categorías', error);
      }
    })
  }
  //Llenare esta funcion cuando esten listos los servicos y clases de producto: Atte. Diego
  filtrarPorCategoria(): void {

  }

}
