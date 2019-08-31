package com.example.testproject.Model;

public class ImageDetail {
    String imageName;
    int image;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
    public ImageDetail(String name, int image)
    {
        this.image=image;
        this.imageName=name;
    }

    public  ImageDetail(){

    }
}
