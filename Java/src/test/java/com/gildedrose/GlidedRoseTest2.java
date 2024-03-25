package com.gildedrose;

import org.junit.jupiter.api.Test;

import static com.gildedrose.GildedRose.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlidedRoseTest2 {


    @Test
    void normal() {
        //test normal
        GildedRose app = new GildedRose(new Item[]{new Item("Normal", 10, 10)});
        app.updateQuality();
        Item item = app.items[0];
        assertEquals("Normal", item.name);
        assertEquals(9, item.sellIn);
        assertEquals(9, item.quality);
    }

    //Once the sell by date has passed, Quality degrades twice as fast
    @Test
    void qualityDegradedAfterSellDate() {

        GildedRose app = new GildedRose(new Item[]{new Item("", 0, 10)});
        app.updateQuality();
        Item item = app.items[0];
        assertEquals(-1, item.sellIn);
        assertEquals(8, item.quality);
    }

    //The Quality of an item is never negative
    @Test
    void qualityNeverNegative() {

        GildedRose app = new GildedRose(new Item[]{new Item("", 1, 1)});
        app.updateQuality();
        Item item = app.items[0];
        assertEquals(0, item.sellIn);
        assertEquals(0, item.quality);
        app.updateQuality();
        assertEquals(-1, item.sellIn);
        assertEquals(0, item.quality);

//        try {
//            new Item("", 1, -1);
//            fail("An item with quality -1 has been created");
//        } catch (Exception ex) {
//
//        }
    }

    //"Aged Brie" actually increases in Quality the older it gets
    @Test
    void qualityAgedBrie() {

        GildedRose app = new GildedRose(new Item[]{new Item(AGED_BRIE, 10, 10)});
        app.updateQuality();
        Item item = app.items[0];
        assertEquals(9, item.sellIn);
        assertEquals(11, item.quality);
    }

    //The Quality of an item is never more than 50
    @Test
    void qualityMoreThan50() {

        GildedRose app = new GildedRose(new Item[]{new Item(AGED_BRIE, 10, 50)});
        app.updateQuality();
        Item item = app.items[0];
        assertEquals(9, item.sellIn);
        assertEquals(50, item.quality);

//        try {
//            new Item("", 1, -50);
//            fail("An item with quality more than 50 has been created");
//        } catch (Exception ex) {
//
//        }
    }

    //"Sulfuras", being a legendary item, never has to be sold or decreases in Quality
    @Test
    void sulfuras() {
        GildedRose app = new GildedRose(new Item[]{new Item(SULFURAS_HAND_OF_RAGNAROS, 100, 80)});
        app.updateQuality();
        Item item = app.items[0];
        assertEquals(100, item.sellIn);
        assertEquals(80, item.quality);
    }


    /**
     * "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
     * Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but
     * Quality drops to 0 after the concert
     */
    @Test
    void backstage() {
        GildedRose app = new GildedRose(new Item[]{new Item(BACKSTAGE_PASSES, 11, 10)});
        app.updateQuality();
        Item item = app.items[0];
        assertEquals(10, item.sellIn);
        assertEquals(11, item.quality);
        app.updateQuality();
        assertEquals(9, item.sellIn);
        assertEquals(13, item.quality);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        assertEquals(6, item.sellIn);
        assertEquals(19, item.quality);
        app.updateQuality();
        assertEquals(5, item.sellIn);
        assertEquals(21, item.quality);
        app.updateQuality();
        assertEquals(4, item.sellIn);
        assertEquals(24, item.quality);
        app.updateQuality();
        assertEquals(3, item.sellIn);
        assertEquals(27, item.quality);
        app.updateQuality();
        assertEquals(2, item.sellIn);
        assertEquals(30, item.quality);
        app.updateQuality();
        assertEquals(1, item.sellIn);
        assertEquals(33, item.quality);
        app.updateQuality();
        assertEquals(0, item.sellIn);
        assertEquals(36, item.quality);
        app.updateQuality();
        assertEquals(-1, item.sellIn);
        assertEquals(0, item.quality);
    }


    //"Conjured" items degrade in Quality twice as fast as normal items
    @Test
    void conjured() {
        GildedRose app = new GildedRose(new Item[]{new Item("Conjured Mana Cake", 3, 16)});
        Item item = app.items[0];
        app.updateQuality();
        assertEquals(2, item.sellIn);
        assertEquals(14, item.quality);
        app.updateQuality();
        assertEquals(1, item.sellIn);
        assertEquals(12, item.quality);
        app.updateQuality();
        assertEquals(0, item.sellIn);
        assertEquals(10, item.quality);
        app.updateQuality();
        assertEquals(-1, item.sellIn);
        assertEquals(6, item.quality);
    }
}
