/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.util.List;

public class Product {
    private int productId;
    private String productCode;
    private boolean productStatus;
    private int productOrigin;
    private String type;
    private String productName;
    private String description;
    private String image;
    private float sale;
    private int productCategory;
    private List<Size> sizes;
    private Origin origin;

    public Product() {
    }

    public Product(int productId, String productCode, boolean productStatus, int productOrigin, String type, String productName, String description, String image, float sale) {
        this.productId = productId;
        this.productCode = productCode;
        this.productStatus = productStatus;
        this.productOrigin = productOrigin;
        this.type = type;
        this.productName = productName;
        this.description = description;
        this.image = image;
        this.sale = sale;
    }
    
    public Product(String productCode, boolean productStatus, int productOrigin, String type, String productName, String description, String image, float sale) {
        this.productCode = productCode;
        this.productStatus = productStatus;
        this.productOrigin = productOrigin;
        this.type = type;
        this.productName = productName;
        this.description = description;
        this.image = image;
        this.sale = sale;
    }
    
    public Product(String productCode, boolean productStatus, int productOrigin, int productCategory, String type, String productName, String description, String image, float sale) {
        this.productCode = productCode;
        this.productStatus = productStatus;
        this.productOrigin = productOrigin;
        this.productCategory = productCategory;
        this.type = type;
        this.productName = productName;
        this.description = description;
        this.image = image;
        this.sale = sale;
    }

    public int getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(int productCategory) {
        this.productCategory = productCategory;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public List<Size> getSizes() {
        return sizes;
    }

    public void setSizes(List<Size> sizes) {
        this.sizes = sizes;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    public int getProductOrigin() {
        return productOrigin;
    }

    public void setProductOrigin(int productOrigin) {
        this.productOrigin = productOrigin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getSale() {
        return sale;
    }

    public void setSale(float sale) {
        this.sale = sale;
    }
    
    
    
}
