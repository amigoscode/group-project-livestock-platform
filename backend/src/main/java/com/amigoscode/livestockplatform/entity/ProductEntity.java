package com.amigoscode.livestockplatform.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "product", schema = "public", catalog = "postgres")
public class ProductEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "title", nullable = true, length = 50)
    private String title;
    @Basic
    @Column(name = "description", nullable = true, length = 240)
    private String description;
    @Basic
    @Column(name = "category", nullable = true, length = 100)
    private String category;
    @Basic
    @Column(name = "stock", nullable = true)
    private Integer stock;
    @Basic
    @Column(name = "price", nullable = true)
    private Integer price;
    @Basic
    @Column(name = "weight", nullable = true)
    private Integer weight;
    @Basic
    @Column(name = "age", nullable = true)
    private Integer age;
    @Basic
    @Column(name = "date_of_processing", nullable = true)
    private Date dateOfProcessing;
    @Basic
    @Column(name = "processing_status", nullable = true, length = 50)
    private String processingStatus;
    @Basic
    @Column(name = "seller_id", nullable = false)
    private int sellerId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getDateOfProcessing() {
        return dateOfProcessing;
    }

    public void setDateOfProcessing(Date dateOfProcessing) {
        this.dateOfProcessing = dateOfProcessing;
    }

    public String getProcessingStatus() {
        return processingStatus;
    }

    public void setProcessingStatus(String processingStatus) {
        this.processingStatus = processingStatus;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ProductEntity that = (ProductEntity) o;
        return id == that.id && sellerId == that.sellerId && Objects.equals(title, that.title)
                && Objects.equals(description, that.description) && Objects.equals(category, that.category)
                && Objects.equals(stock, that.stock) && Objects.equals(price, that.price)
                && Objects.equals(weight, that.weight) && Objects.equals(age, that.age)
                && Objects.equals(dateOfProcessing, that.dateOfProcessing)
                && Objects.equals(processingStatus, that.processingStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, category, stock, price, weight, age, dateOfProcessing,
                processingStatus, sellerId);
    }
}
