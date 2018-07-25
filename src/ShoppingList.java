import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class ShoppingList {

	// private static int getValidMenuChoice(Scanner scnr, LinkedHashMap<String,
	// Double> inventory) {
	// int menuChoice = 0;
	// boolean inputIsValid = false;
	// do {
	// try {
	// menuChoice = scnr.nextInt();
	// validateChoiceInMenu(menuChoice, inventory.size());
	// inputIsValid = true;
	// } catch (InputMismatchException | IllegalArgumentException ex) {
	// System.out.println("Sorry, you must enter a number between 1 and " +
	// inventory.size() + ": ");
	// }
	// scnr.nextLine(); /* clear garbage */
	// } while (!inputIsValid);
	// return menuChoice;
	// }
	//
	// public static void validateChoiceInMenu(int input, int menuLength) {
	// if (input < 1 || input > menuLength) {
	// throw new IllegalArgumentException();
	// }
	// }

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);

		// Welcome
		System.out.println("Welcome to the Big Circle Poké Mart!\n");

		// Build Inventory
		LinkedHashMap<String, Double> inventory = buildInventory();

		// Display Inventory
		displayInventory(inventory);

		// Get User Choice
		// TODO take and validate user input
		int userInput = 2;

		// Retrieve entry of chosen item
		Entry<String, Double> purchasedItem = getSelectedItem(inventory, userInput);

		// Add that to a shoppingCart

		ArrayList<Integer> itemQuantities = new ArrayList<Integer>();
		ArrayList<String> items = new ArrayList<String>();
		ArrayList<Double> prices = new ArrayList<Double>();

		// if Arrays.asList(yourArray).contains(yourValue)
		// don't add the item, update the quantity
		// else, add the items
		
		items.add("Great Ball");
		prices.add(600.0);
		itemQuantities.add(1);

		if (items.contains(purchasedItem.getKey())) {
			// do some fancy stuff
			itemQuantities.set(items.indexOf(purchasedItem.getKey()),
					itemQuantities.get(items.indexOf(purchasedItem.getKey())) + 1);
		} else {
			itemQuantities.add(1);
			items.add(purchasedItem.getKey());
			prices.add(purchasedItem.getValue());
		}

		System.out.println(itemQuantities);
		System.out.println(items);
		System.out.println(prices);

		scnr.close();
	}

	private static Entry<String, Double> getSelectedItem(LinkedHashMap<String, Double> inventory, int userInput) {
		int count = 1;
		Entry<String, Double> desiredProduct = null;

		for (Entry<String, Double> product : inventory.entrySet()) {
			if (count == userInput) {
				desiredProduct = product;
				break;
			}
			count++;
		}
		return desiredProduct;
	}

	private static void displayInventory(LinkedHashMap<String, Double> inventory) {
		System.out.println("#. Item\t\t\tPrice");
		System.out.println("=============================");

		int i = 1;
		for (Entry<String, Double> product : inventory.entrySet()) {
			System.out.printf("%d. " + product.getKey() + "\t\t" + product.getValue() + "%n", i);
			i++;
		}
	}

	private static LinkedHashMap<String, Double> buildInventory() {
		LinkedHashMap<String, Double> inventory = new LinkedHashMap<String, Double>() {
			private static final long serialVersionUID = -1126640885363620913L;
			{
				put("Poké Ball", 200.0);
				put("Great Ball", 600.0);
				put("Ultra Ball", 800.0);
				put("Potion", 200.0);
				put("Antidote", 200.0);
				put("Escape Rope", 900.0);
				put("Repel", 400.0);
				put("Honey", 100.0);
			}
		};
		return inventory;
	}

	// ITEM SELECTION LOOP

	// DISPLAY A LIST OF ITEMS & PRICES //

	// CHOICE VALIDATION LOOP

	// LET USER CHOOSE AN ITEM

	// VALIDATE ITEM EXISTS

	// ADD ITEM-PRICE TO USER'S ORDER

	// DISPLAY USER'S ORDER (ITEMS AND PRICES)

	// DISPLAY AVERAGE PRICE OF ITEM

}
