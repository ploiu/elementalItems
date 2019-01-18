package elementalitems.crafting;

import elementalitems.ElementalItems;
import elementalitems.items.ItemHandler;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class RecipeRepairFlamethrower extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {

	// the amount each crystal repairs the flamethrower
	private final int crystalRepairAmount = 10;

	public RecipeRepairFlamethrower() {
		this.setRegistryName(ElementalItems.MOD_ID + ":recipe_repair_flamethrower");
	}

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {
		// do we already have a flamethrower in the thing?
		boolean hasOneFlamethrower = false;
		boolean matches = false;
		// go through all stacks in the inventory and get the stuff
		int invSize = inv.getSizeInventory();
		for(int i = 0; i < invSize; i++) {
			// the itemStack at i
			ItemStack currentStack = inv.getStackInSlot(i);
			if(ItemHandler.flamethrower.equals(currentStack.getItem())) {
				// we start off at false, so the first time we reach here, it becomes true. Any times after that makes it false
				hasOneFlamethrower ^= true;
				// we can only have 1 flamethrower here
				if(!hasOneFlamethrower) {
					return false;
				}
			} else {
				// this way we don't take any non-fire crystals
				matches = currentStack.isEmpty() || ItemHandler.fireCrystal.equals(currentStack.getItem());
				if(!matches) {
					return false;
				}
			}
		}
		return matches;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		// find the flamethrower in the inventory, get the damage, and add 10 durability for each fire crystal in the inventory as well
		ItemStack flamethrowerStack = ItemStack.EMPTY;
		// the items in the crafting inventory
		ItemStack[] craftingInputs = this.getItemStacksInCraftingGrid(inv);
		// get the number of fire crystal stacks in the grid
		int fireCrystalCount = this.getFireCrystalCountForCrafting(craftingInputs);
		for(int i = 0; i < craftingInputs.length; i++) {
			// the current itemStack
			ItemStack currentStack = craftingInputs[i];
			// check if it's a flamethrower, if yes, update our flamethrowerStack
			if(ItemHandler.flamethrower.equals(currentStack.getItem())) {
				flamethrowerStack = currentStack.copy();
			}
		}
		if(!flamethrowerStack.isEmpty()) {
			// return flamethrowerStack, but with increased durability
			flamethrowerStack.setItemDamage(flamethrowerStack.getItemDamage() - fireCrystalCount * this.crystalRepairAmount);
		}
		return flamethrowerStack;
	}

	@Override
	public boolean canFit(int width, int height) {
		return width > 1 || height > 1;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return ItemStack.EMPTY;
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
		// easier to loop through
		ItemStack[] inputStacks = this.getItemStacksInCraftingGrid(inv);
		// number of crystals used
		int numberOfCrystalsInGrid = this.getFireCrystalCountForCrafting(inputStacks);
		// the Flamethrower in the crafting grid
		ItemStack flamethrowerStack = ItemStack.EMPTY;
		// get the flamethrower
		for(int i = 0; i < inputStacks.length; i++) {
			if(ItemHandler.flamethrower.equals(inputStacks[i].getItem())) {
				flamethrowerStack = inputStacks[i];
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
			if(ItemHandler.flamethrower.equals(inputStacks[i].getItem())) {
				remainingStacks.add(i, ItemStack.EMPTY);
			} else if(ItemHandler.fireCrystal.equals(inputStacks[i].getItem()) && remainingCrystals > 0) {
				remainingStacks.add(i, new ItemStack((Item) ItemHandler.fireCrystal, 1));
				remainingCrystals--;
			} else {
				remainingStacks.add(i, ItemStack.EMPTY);
			}
		}
		return remainingStacks;
	}

	@Override
	public boolean isDynamic() {
		return true;
	}

	/**
	 * gets the number of {@link ItemStack ItemStacks} that have a {@link ItemHandler#fireCrystal} in the passed array of ItemStacks
	 *
	 * @param inputStacks the itemStacks we are checking
	 * @return the number of fire crystals in the crafting grid
	 */
	private int getFireCrystalCountForCrafting(ItemStack[] inputStacks) {
		int fireCrystalCount = 0;
		for(int i = 0; i < inputStacks.length; i++) {
			// if the current itemstack is not empty and is a fire crystal, increment fireCrystalCount
			if(ItemHandler.fireCrystal.equals(inputStacks[i].getItem())) {
				fireCrystalCount++;
			}
		}
		return fireCrystalCount;
	}

	private int getNumberOfUsedFireCrystals(int numberOfFireCrystalsInGrid, ItemStack flamethrowerStack) {
		// get the stack damage
		int stackDamage = flamethrowerStack.getItemDamage();
		// get the repair amount for the crystals
		int totalRepairAmount = this.crystalRepairAmount * numberOfFireCrystalsInGrid;
		// the number of used crystals
		int usedCrystalCount = 0;
		// if the stack damage is larger than the total repair amount, consume all crystals
		if(stackDamage >= totalRepairAmount) {
			usedCrystalCount = numberOfFireCrystalsInGrid;
		} else {
			/* to get the number of used crystals, add (repairAmount - 1) to the stackDamage to handle leftover damage 
			and divide by repairAmount to get the number of used crystals */
			usedCrystalCount = (stackDamage + (this.crystalRepairAmount - 1)) / 10;
		}
		return usedCrystalCount;
	}

	/**
	 * returns an array of {@link ItemStack ItemStacks} in the passed {@link InventoryCrafting}
	 *
	 * @param inv the inventory we are getting the stacks from
	 * @return an array of ItemStacks to iterate over instead of iterating over the inv's item stacks. It's probably
	 * 		faster which is why it's done this way
	 */
	private ItemStack[] getItemStacksInCraftingGrid(InventoryCrafting inv) {
		ItemStack[] craftingStacks = new ItemStack[inv.getSizeInventory()];
		// add all the items from inv
		for(int i = 0; i < inv.getSizeInventory(); i++) {
			craftingStacks[i] = inv.getStackInSlot(i).copy();
		}
		return craftingStacks;
	}
}
