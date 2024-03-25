package com.gildedrose;

public class ItemBackStagePassesEvaluator extends ItemEvaluator {
    @Override
    public int getQualityChange(Item item) {
        int qualityChange;
        if (item.sellIn <= 0) {
            qualityChange = -item.quality;
        } else {
            if (item.sellIn <= 5) {
                qualityChange = 3;
            } else if (item.sellIn <= 10) {
                qualityChange = 2;
            } else {
                qualityChange = 1;
            }
        }
        return qualityChange;
    }
}
