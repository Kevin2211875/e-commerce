export class Category {
    id: string;
    name: string;
    description: string;
    active: boolean;
  
    constructor(id: string, name: string, description: string, active: boolean) {
      this.id = id;
      this.name = name;
      this.description = description;
      this.active = active;
    }
  
    
    static fromJson(json: any): Category {
      return new Category(
        json.id,
        json.name,
        json.description,
        json.active
      );
    }
}
