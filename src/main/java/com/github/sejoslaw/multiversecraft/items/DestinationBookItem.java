package com.github.sejoslaw.multiversecraft.items;

import com.github.sejoslaw.multiversecraft.MultiverseCraftItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

/**
 * Works like a "from anywhere to saved location" teleport.
 *
 * Right-Click -> Saves current Player location and dimension.
 * Again Right-Click -> Teleports Player to saved location and dimension.
 *
 * @author Sejoslaw
 */
public class DestinationBookItem extends Item {
    public DestinationBookItem(Settings settings) {
        super(settings);
    }

    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        // TODO: Right-Click -> register position and dimension into NBT
        // TODO: If NBT exists AND Right-Click -> teleport Player

        ItemStack bookStack = user.getStackInHand(hand);

        if (!bookStack.getRegistryEntry().matchesKey(MultiverseCraftItems.DESTINATION_BOOK_KEY)) {
            return ActionResult.PASS;
        }

        if (this.containsRequiredNbt(bookStack)) {
            // TODO: Teleport
        } else {
            // TODO: Write NBT (encode) + add enchant overlay
        }

        return ActionResult.SUCCESS;
    }

    private boolean containsRequiredNbt(ItemStack bookStack) {
        // TODO: Validate if ItemStack contains required NBT (position and dimension)
    }
}
