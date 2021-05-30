package com.example.deneme1;

public class Food {
    private int id;
    private String name;
    private String ingredients;  //malzemeler
    private String recipe;  //yemek tarifi
    private byte[] image;
    private int fav;
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getFav() {
        return fav;
    }

    public void setFav(int fav) { this.fav = fav; }

    public int getUserId(){return userId;}

    public void setUserId(int userId){ this.userId = userId; }


}

