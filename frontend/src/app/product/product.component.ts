import { Component } from '@angular/core';
import { ProductService } from './product.service';
import { Product } from './product';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent {
  products : Product[] = [];

  constructor(private productService: ProductService) {}
  
  ngOnInit() {
    this.getProducts();
  
  }
 
  getProducts(): void {
    this.productService.getProducts()
    .subscribe(products =>  (this.products = products));
  }

}
