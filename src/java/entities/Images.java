/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

public class Images {
    private int imageId;
    private int productId;
    private String imageName;

    public Images() {
    }

    public Images(int imageId, int productId, String imageName) {
        this.imageId = imageId;
        this.productId = productId;
        this.imageName = imageName;
    }
    
    public Images(int productId, String imageName) {
        this.productId = productId;
        this.imageName = imageName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    
    
}