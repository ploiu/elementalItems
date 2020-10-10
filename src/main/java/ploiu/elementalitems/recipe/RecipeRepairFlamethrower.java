package ploiu.elementalitems.recipe;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import ploiu.elementalitems.items.ElementalItemsItemRegistry;

public class RecipeRepairFlamethrower extends SpecialRecipe {

	// the amount each crystal repairs the flamethrower
	private final int crystalRepairAmount = 30; //ElementalItemsConfig.flamethrowerRepairAmountPerFireCrystal; TODO

	public RecipeRepairFlamethrower(ResourceLocation idIn) {
		super(idIn);
	}

	@Override
	public boolean matches(CraftingInventory inv, World worldIn) {
		// do we already have a flamethrower in the thing?
		boolean hasOneFlamethrower = false;
		boolean matches = false;
		// go through all stacks in the inventory and get the stuff
		int invSize = inv.getSizeInventory();
		for(int i = 0; i < invSize; i++) {
			// the itemStack at i
			ItemStack currentStack = inv.getStackInSlot(i);
			if(ElementalItemsItemRegistry.flamethrower.equals(currentStack.getItem())) {
				// we start off at false, so the first time we reach here, it becomes true. Any times after that makes it false
				hasOneFlamethrower ^= true;
				// we can only have 1 flamethrower here
				if(!hasOneFlamethrower) {
					return false;
				}
			} else {
				// this way we don't take any non-fire crystals
				matches = currentStack.isEmpty() || ElementalItemsItemRegistry.fireCrystal.equals(currentStack.getItem());
				if(!matches) {
					return false;
				}
			}
		}
		return matches;
	}

	@Override
	public ItemStack getCraftingResult(CraftingInventory inv) {
		// find the flamethrower in the inventory, get the damage, and add crystalRepairAmount durability for each fire crystal in the inventory as well
		ItemStack flamethrowerStack = ItemStack.EMPTY;
		// the items in the crafting inventory
		ItemStack[] craftingInputs = this.getItemStacksInCraftingGrid(inv);
		// get the number of fire crystal stacks in the grid
		int fireCrystalCount = this.getFireCrystalCountForCrafting(craftingInputs);
		for(ItemStack currentStack : craftingInputs) {
			// the current itemStack
			// check if it's a flamethrower, if yes, update our flamethrowerStack
			if(ElementalItemsItemRegistry.flamethrower.equals(currentStack.getItem())) {
				flamethrowerStack = currentStack.copy();
			}
		}
		if(!flamethrowerStack.isEmpty()) {
			// return flamethrowerStack, but with increased durability
			flamethrowerStack.setDamage(flamethrowerStack.getDamage() - fireCrystalCount * this.crystalRepairAmount);
		}
		return flamethrowerStack;
	}

	@Override
	public boolean canFit(int width, int height) {
		return width > 1 || height > 1;
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(CraftingInventory inv) {
		// easier to loop through
		ItemStack[] inputStacks = this.getItemStacksInCraftingGrid(inv);
		// number of crystals used
		int numberOfCrystalsInGrid = this.getFireCrystalCountForCrafting(inputStacks);
		// the Flamethrower in the crafting grid
		ItemStack flamethrowerStack = ItemStack.EMPTY;
		// get the flamethrower
		for(ItemStack inputStack : inputStacks) {
			if(ElementalItemsItemRegistry.flamethrower.equals(inputStack.getItem())) {
				flamethrowerStack = inputStack;
				break;
			}
		}
		// get the number of remaining fire crystals and create an ItemStack of fire crystals with that amount
		int usedFireCrystals = this.getNumberOfUsedFireCrystals(numberOfCrystalsInGrid, flamethrowerStack);
		int remainingCrystals = numberOfCrystalsInGrid - usedFireCrystals;
		// create a nonNullList and fill it with our remaining fire crystals
		NonNullList<ItemStack> remainingStacks = NonNullList.create();
		for(int i = 0; i < inputStacks.length; i++) {
			// add the current stack if it's not the flamethrower, else add air
			if(ElementalItemsItemRegistry.flamethrower.equals(inputStacks[i].getItem())) {
				remainingStacks.add(i, ItemStack.EMPTY);
			} else if(ElementalItemsItemRegistry.fireCrystal.equals(inputStacks[i].getItem()) && remainingCrystals > 0) {
				remainingStacks.add(i, new ItemStack(ElementalItemsItemRegistry.fireCrystal, 1));
				remainingCrystals--;
			} else {
				remainingStacks.add(i, ItemStack.EMPTY);
			}
		}
		return remainingStacks;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		return ElementalItemsRecipeRegistry.repairFlamethrowerRecipe;
	}

	/**
	 * gets the number of {@link ItemStack ItemStacks} that have a {@link ElementalItemsItemRegistry#fireCrystal} in the passed array of ItemStacks
	 *
	 * @param inputStacks the itemStacks we are checking
	 * @return the number of fire crystals in the crafting grid
	 */
	private int getFireCrystalCountForCrafting(ItemStack[] inputStacks) {
		int fireCrystalCount = 0;
		for(ItemStack inputStack : inputStacks) {
			// if the current itemstack is not empty and is a fire crystal, increment fireCrystalCount
			if(ElementalItemsItemRegistry.fireCrystal.equals(inputStack.getItem())) {
				fireCrystalCount++;
			}
		}
		return fireCrystalCount;
	}

	private int getNumberOfUsedFireCrystals(int numberOfFireCrystalsInGrid, ItemStack flamethrowerStack) {
		// get the stack damage
		int stackDamage = flamethrowerStack.getDamage();
		// get the repair amount for the crystals
		int totalRepairAmount = this.crystalRepairAmount * numberOfFireCrystalsInGrid;
		// the number of used crystals
		int usedCrystalCount;
		// if the stack damage is larger than the total repair amount, consume all crystals
		if(stackDamage >= totalRepairAmount) {
			usedCrystalCount = numberOfFireCrystalsInGrid;
		} else {
			/* to get the number of used crystals, add (repairAmount - 1) to the stackDamage to handle leftover damage 
			and divide by repairAmount to get the number of used crystals */
			usedCrystalCount = (stackDamage + (this.crystalRepairAmount - 1)) / this.crystalRepairAmount;
		}
		return usedCrystalCount;
	}

	/**
	 * returns an array of {@link ItemStack ItemStacks} in the passed {@link CraftingInventory}
	 *
	 * @param inv the inventory we are getting the stacks from
	 * @return an array of ItemStacks to iterate over instead of iterating over the inv's item stacks. It's probably
	 * 		faster which is why it's done this way
	 */
	private ItemStack[] getItemStacksInCraftingGrid(CraftingInventory inv) {
		ItemStack[] craftingStacks = new ItemStack[inv.getSizeInventory()];
		// add all the items from inv
		for(int i = 0; i < inv.getSizeInventory(); i++) {
			craftingStacks[i] = inv.getStackInSlot(i).copy();
		}
		return craftingStacks;
	}
}