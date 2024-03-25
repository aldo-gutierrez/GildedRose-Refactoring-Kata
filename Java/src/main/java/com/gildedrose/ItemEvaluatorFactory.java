package com.gildedrose;

import static com.gildedrose.GildedRose.*;

public class ItemEvaluatorFactory {

    ItemEvaluator getItemEvaluator(String name) {
        ItemEvaluator itemEvaluator;
        if (name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
            itemEvaluator = new ItemSulfurasHandOfRagnarosEvaluator();
        } else if (name.equals(AGED_BRIE)) {
            itemEvaluator = new ItemAgedBrieEvaluator();
        } else if (name.equals(BACKSTAGE_PASSES)) {
            itemEvaluator = new ItemBackStagePassesEvaluator();
        } else if (name.equals(CONJURED_MANA_CAKE)) {
            itemEvaluator = new ItemConjuredEvaluator();
        } else {
            itemEvaluator = new ItemEvaluator();
        }
        return itemEvaluator;
    }
}
