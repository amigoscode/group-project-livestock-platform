import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, catchError } from "rxjs";
import { Product } from "./product";



@Injectable()
export class ProductService {
  productsUrl = '/api/v1/products';  // URL to web api


  constructor(
    private http: HttpClient) {
    
  }

  /** GET products from the server */
  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.productsUrl);
  }
}
