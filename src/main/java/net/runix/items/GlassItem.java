package net.runix.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class GlassItem extends Item {
    public GlassItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user){
        ItemStack itemStack = super.finishUsing(stack,world,user);
        if(user instanceof PlayerEntity && ((PlayerEntity)user).getAbilities().creativeMode){
            return itemStack;
        }
        return new ItemStack(Items.GLASS_BOTTLE);
    }

    @Override
    public SoundEvent getEatSound(){
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }

}
