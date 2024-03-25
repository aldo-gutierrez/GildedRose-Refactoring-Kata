package com.gildedrose;

public class ItemEvaluator {

    public int getQualityChange(Item item) {
        int qualityChange;
        if (item.sellIn <= 0) {
            qualityChange = -2;
        } else {
            qualityChange = -1;
        }
        return qualityChange;
    }


    public void updateItem(Item item, int qualityChange) {
        item.quality = item.quality + qualityChange;
        if (item.quality < 0) {
            item.quality = 0;
        } else if (item.quality > 50) {
            item.quality = 50;
        }
        item.sellIn = item.sellIn - 1;
    }

}
