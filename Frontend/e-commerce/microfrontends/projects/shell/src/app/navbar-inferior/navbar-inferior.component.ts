import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Category } from '../../../models/category';
import { CategoryService } from '../../../services/category.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-navbar-inferior',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './navbar-inferior.component.html',
  styleUrl: './navbar-inferior.component.scss'
})

export class NavbarInferiorComponent implements OnInit {
  menuAbierto = false;
  categories: Category[] = [];
  

  constructor(private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.obtenerCategorias();
  }

  toggleMenu() {
    this.menuAbierto = !this.menuAbierto;
    document.body.style.overflow = this.menuAbierto ? 'hidden' : '';
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
  filtrarPorCategoria(selectedCategory: String): void {

  }
}
