export interface Product {
    id: number;
    title: string;
    description: string;
    category: string;
    stock: number;
    price: number;
    weight: number;
    age: number;
    date_of_processing: Date;
    processing_status: string;
    seller_id: number;
}