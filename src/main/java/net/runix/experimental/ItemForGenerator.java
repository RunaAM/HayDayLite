package net.runix.experimental;

import net.minecraft.item.Item;

public class ItemForGenerator {

    public Item item;
    public int count;

    public String name;

    public ItemForGenerator(Item item, int count){
        this.count = count;
        this.item = item;
    }

    public ItemForGenerator(Item item, String name){
        this.item = item;
        this.name = name;
    }

    public ItemForGenerator(Item item){
        this.item = item;
        this.count = 1;
    }

}
