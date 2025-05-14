import { Product } from './product';

describe('Product', () => {
  it('should create an instance', () => {
    const product = new Product();  // Ahora funciona sin pasar parámetros

    expect(product).toBeTruthy();
  });
});
