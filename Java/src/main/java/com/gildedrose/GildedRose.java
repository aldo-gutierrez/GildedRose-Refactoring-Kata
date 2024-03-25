package com.gildedrose;

class GildedRose {
    Item[] items;

    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String CONJURED_MANA_CAKE = "Conjured Mana Cake";

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        updateQualityExpandable();
    }

    /**
     * Solution 3
     */
    public void updateQualityExpandable() {
        for (Item item : items) {
            ItemEvaluator itemEvaluator = new ItemEvaluatorFactory().getItemEvaluator(item.name);
            int qualityChange = itemEvaluator.getQualityChange(item);
            itemEvaluator.updateItem(item, qualityChange);
        }
    }
    /**
     * SOLUTION 1
     * New code from 0,
     */
    public void updateQualityFrom0() {
        for (Item item : items) {

            // change quality
            int qualityChange;
            if (item.name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
                qualityChange = 0;
            } else if (item.name.equals(AGED_BRIE)) {
                qualityChange = 1;
            } else if (item.name.equals(BACKSTAGE_PASSES)) {
                if (item.sellIn <= 0) {
                    item.quality = 0;
                    qualityChange = 0;
                } else {
                    if (item.sellIn <= 5) {
                        qualityChange = 3;
                    } else if (item.sellIn <= 10) {
                        qualityChange = 2;
                    } else {
                        qualityChange = 1;
                    }
                }
            } else if (item.name.equals(CONJURED_MANA_CAKE)) {
                if (item.sellIn <= 0) {
                    qualityChange = -4;
                } else {
                    qualityChange = -2;
                }
            } else {
                if (item.sellIn <= 0) {
                    qualityChange = -2;
                } else {
                    qualityChange = -1;
                }
            }
            //validate quality range and change quality
            if (!item.name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
                item.quality = item.quality + qualityChange;
                if (item.quality < 0) {
                    item.quality = 0;
                } else if (item.quality > 50) {
                    item.quality = 50;
                }
            }

            // change sellIn
            item.sellIn = item.name.equals(SULFURAS_HAND_OF_RAGNAROS) ? item.sellIn : item.sellIn - 1;

        }
    }

}
