package com.example.winkel;

public class Item_class {
    private String ItemName;
    private String ItemImg;
    private String ItemRot;
    private String ItemAvb;

    public Item_class(String itemName, String itemImg, String itemRot, String itemAvb) {
        ItemName = itemName;
        ItemImg = itemImg;
        ItemRot = itemRot;
        ItemAvb = itemAvb;
    }

    public Item_class() {
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemImg() {
        return ItemImg;
    }

    public void setItemImg(String itemImg) {
        ItemImg = itemImg;
    }

    public String getItemRot() {
        return ItemRot;
    }

    public void setItemRot(String itemRot) {
        ItemRot = itemRot;
    }

    public String getItemAvb() {
        return ItemAvb;
    }

    public void setItemAvb(String itemAvb) {
        ItemAvb = itemAvb;
    }
}
