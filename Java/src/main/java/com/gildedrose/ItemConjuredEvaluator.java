package com.gildedrose;

public class ItemConjuredEvaluator extends ItemEvaluator {
    @Override
    public int getQualityChange(Item item) {
        int qualityChange;
        if (item.sellIn <= 0) {
            qualityChange = -4;
        } else {
            qualityChange = -2;
        }
        return qualityChange;
    }
}
