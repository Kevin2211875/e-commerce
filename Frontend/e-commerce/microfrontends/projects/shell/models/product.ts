export class Product {
  id: string;
  name: string;
  description: string;
  image: string;
  category: string;
  brand: string | null;
  price: number;
  publishDate: Date;
  updateDate: Date;
  quantity: number;
  available: boolean;
  internalNotes: string;
  reviews: any[] = [];
  suggestions: string[];
  attributes: { [key: string]: any };

  constructor(
    id: string = "",  // Hacer el id opcional con un valor predeterminado vacío
    name: string = "",
    description: string = "",
    image: string = "",
    category: string = "",
    brand: string | null = null,
    price: number = 0,
    publishDate: Date = new Date(),
    updateDate: Date = new Date(),
    quantity: number = 0,
    available: boolean = true,
    internalNotes: string = "",
    reviews: any[] = [],
    suggestions: string[] = [],
    attributes: { [key: string]: any } = {}
  ) {
    this.id = id;  // Si no se pasa un id, se usará una cadena vacía como valor predeterminado
    this.name = name;
    this.description = description;
    this.image = image;
    this.category = category;
    this.brand = brand;
    this.price = price;
    this.publishDate = publishDate;
    this.updateDate = updateDate;
    this.quantity = quantity;
    this.available = available;
    this.internalNotes = internalNotes;
    this.reviews = reviews;
    this.suggestions = suggestions;
    this.attributes = attributes;
  }
}
