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
        updateQualityFrom0();
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

    /**
     * SOLUTION2
     * Code Refactored
     * 1.  extract variable name = items[i].name;
     * 2.  simplify items[i].quality = items[i].quality - items[i].quality to "= 0"
     */
    public void updateQualityRefactored() {
        for (int i = 0; i < items.length; i++) {
            String name = items[i].name;
            if (!name.equals(AGED_BRIE) && !name.equals(BACKSTAGE_PASSES)) {
                if (items[i].quality > 0) {
                    if (!name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
                        //THIS IF IS THE MODIFIED CODE (1) IF REPEATS BELOW
                        if (name.equals(CONJURED_MANA_CAKE)) {
                            items[i].quality = Math.max(items[i].quality - 2, 0);
                        } else {
                            items[i].quality = items[i].quality - 1;
                        }
                    }
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;

                    if (name.equals(BACKSTAGE_PASSES)) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }

            if (!name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!name.equals(AGED_BRIE)) {
                    if (name.equals(BACKSTAGE_PASSES)) {
                        items[i].quality = 0;
                    } else {
                        if (items[i].quality > 0) {
                            if (!name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
                                //THIS IF IS THE MODIFIED CODE (1) IF REPEATS ABOVE
                                if (name.equals(CONJURED_MANA_CAKE)) {
                                    items[i].quality = Math.max(items[i].quality - 2, 0);
                                } else {
                                    items[i].quality = items[i].quality - 1;
                                }
                            }
                        }
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
    }
    public void updateQualityOld() {
        for (int i = 0; i < items.length; i++) {
            if (!items[i].name.equals("Aged Brie")
                    && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (items[i].quality > 0) {
                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                        items[i].quality = items[i].quality - 1;
                    }
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;

                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }

            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Aged Brie")) {
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].quality > 0) {
                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                                items[i].quality = items[i].quality - 1;
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
    }
}
