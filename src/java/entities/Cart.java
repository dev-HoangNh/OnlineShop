/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

public class Cart {
    private int cartId;
    private int accountId;
    private int quantity;
    private int productId;
    private int sizeId;
    private Product product;
    private Size size;

    public Cart() {
    }

    public Cart(int cartId, int accountId, int quantity, int productId, int sizeId) {
        this.cartId = cartId;
        this.accountId = accountId;
        this.quantity = quantity;
        this.productId = productId;
        this.sizeId = sizeId;
    }
    
    public Cart(int accountId, int quantity, int productId, int sizeId) {
        this.accountId = accountId;
        this.quantity = quantity;
        this.productId = productId;
        this.sizeId = sizeId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
    
    
}
