package com.example.pk.yndproject.model;

import java.util.ArrayList;

/**
 * Created by PK on 18.08.2017.
 */

public class ImageItemList extends ArrayList<ImageItemList> {

    private ArrayList<ImageItem> imageItems;

    public ArrayList<ImageItem> getImageItems() {
        return imageItems;
    }

    public void setImageItems(ArrayList<ImageItem> imageItems) {
        this.imageItems = imageItems;
    }

}
