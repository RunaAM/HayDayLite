package net.runix.experimental;

import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;

//This is used for generating recipes so that more of the same item can be added in a recipe
public class ItemForGenerator {

    public Item item;
    public int count;
    public TagKey<Item> tagkey;
    public String name;

    public char tag;

    public ItemForGenerator(Item item, int count){
        this.count = count;
        this.item = item;
    }

    public ItemForGenerator(Item item, String name){
        this.item = item;
        this.name = name;
    }

    public ItemForGenerator(TagKey<Item> tagkey, char tag){
        this.tagkey = tagkey;
        this.tag = tag;
    }
    public ItemForGenerator(Item item, char tag){
        this.item = item;
        this.tag = tag;
    }

    public ItemForGenerator(Item item){
        this.item = item;
        this.count = 1;
    }

}
