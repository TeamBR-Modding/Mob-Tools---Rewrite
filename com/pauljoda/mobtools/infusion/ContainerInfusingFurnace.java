package com.pauljoda.mobtools.infusion;

import com.pauljoda.mobtools.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerInfusingFurnace extends Container {
    
    protected TileEntityInfusingFurnace tileEntity;
     private int lastCookTime = 0;
     private int lastItemBurnTime = 0;


    public ContainerInfusingFurnace (InventoryPlayer par1InventoryPlayer, TileEntityInfusingFurnace infuser)
    {
         
             tileEntity = infuser;
             addSlotToContainer(new Slot(tileEntity, 0, 20, 35));
             addSlotToContainer(new Slot(tileEntity, 1, 80, 35));
           
             addSlotToContainer(new SlotInfuser(par1InventoryPlayer.player, tileEntity, 2, 144, 35));
             int var3;
             for (var3 = 0; var3 < 3; ++var3)
             {
                 for (int var4 = 0; var4 < 9; ++var4)
                 {
                     this.addSlotToContainer(new Slot(par1InventoryPlayer, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
                 }
             }

             for (var3 = 0; var3 < 9; ++var3)
             {
                 this.addSlotToContainer(new Slot(par1InventoryPlayer, var3, 8 + var3 * 18, 142));
             }
         }

         public void addCraftingToCrafters(ICrafting par1ICrafting)
         {
             super.addCraftingToCrafters(par1ICrafting);
             par1ICrafting.sendProgressBarUpdate(this, 0, this.tileEntity.furnaceCookTime);
             par1ICrafting.sendProgressBarUpdate(this, 2, this.tileEntity.currentItemBurnTime);
         }
         /**
          * Looks for changes made in the container, sends them to every listener.
          */
         public void detectAndSendChanges()
         {
             super.detectAndSendChanges();

             for (int var1 = 0; var1 < this.crafters.size(); ++var1)
             {
                 ICrafting var2 = (ICrafting)this.crafters.get(var1);

                 if (this.lastCookTime != this.tileEntity.furnaceCookTime)
                 {
                     var2.sendProgressBarUpdate(this, 0, this.tileEntity.furnaceCookTime);
                 }

                 if (this.lastItemBurnTime != this.tileEntity.currentItemBurnTime)
                 {
                     var2.sendProgressBarUpdate(this, 2, this.tileEntity.currentItemBurnTime);
                 }
             }

             this.lastCookTime = this.tileEntity.furnaceCookTime;
             this.lastItemBurnTime = this.tileEntity.currentItemBurnTime;
         }

         @SideOnly(Side.CLIENT)
         public void updateProgressBar(int par1, int par2)
         {
             if (par1 == 0)
             {
                 this.tileEntity.furnaceCookTime = par2;
             }

             if (par1 == 2)
             {
                 this.tileEntity.currentItemBurnTime = par2;
             }
         }

         public boolean canInteractWith(EntityPlayer par1EntityPlayer)
         {
             return this.tileEntity.isUseableByPlayer(par1EntityPlayer);
         }

         /**
          * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
          */
         @Override
         public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
         {
             ItemStack itemstack = null;
             Slot slot = (Slot)this.inventorySlots.get(par2);

             if (slot != null && slot.getHasStack())
             {
                 ItemStack itemstack1 = slot.getStack();
                 itemstack = itemstack1.copy();

                 if (par2 == 2)
                 {
                     if (!this.mergeItemStack(itemstack1, 3, 39, true))
                     {
                         return null;
                     }

                     slot.onSlotChange(itemstack1, itemstack);
                 }
                 else if (par2 != 1 && par2 != 0)
                 {
                     if (RecipiesInfuser.isInput(itemstack1) == true)
                     {
                         if (!this.mergeItemStack(itemstack1, 0, 1, false))
                         {
                             return null;
                         }
                     }
                     else if (Reference.isValidFusee(itemstack1))
                     {
                         if (!this.mergeItemStack(itemstack1, 1, 2, false))
                         {
                             return null;
                         }
                     }
                     else if (par2 >= 3 && par2 < 30)
                     {
                         if (!this.mergeItemStack(itemstack1, 30, 39, false))
                         {
                             return null;
                         }
                     }
                     else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                     {
                         return null;
                     }
                 }
                 else if (!this.mergeItemStack(itemstack1, 3, 39, false))
                 {
                     return null;
                 }

                 if (itemstack1.stackSize == 0)
                 {
                     slot.putStack((ItemStack)null);
                 }
                 else
                 {
                     slot.onSlotChanged();
                 }

                 if (itemstack1.stackSize == itemstack.stackSize)
                 {
                     return null;
                 }

                 slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
             }

             return itemstack;
         }
}