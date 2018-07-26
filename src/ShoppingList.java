import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class ShoppingList {

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);

		// Welcome
		System.out.println("Welcome to the Big Circle Poké Mart!\n");

		// Build Inventory
		LinkedHashMap<String, Double> inventory = buildInventory();

		// Declare Shopping Cart ArrayLists
		ArrayList<Integer> itemQuantities = new ArrayList<Integer>();
		ArrayList<String> items = new ArrayList<String>();
		ArrayList<Double> prices = new ArrayList<Double>();

		// Enter Shopping Loop
		shoppingLoop(scnr, inventory, itemQuantities, items, prices);

		// Display Checkout Information
		displayCheckoutInformation(itemQuantities, items, prices);

		scnr.close();
	}

	private static void displayCheckoutInformation(ArrayList<Integer> itemQuantities, ArrayList<String> items,
			ArrayList<Double> prices) {
		// Calculate Shopping Cart Total
		double total = calculateCartTotal(itemQuantities, items, prices);

		// Calculate Average Item Price
		double averageItemPrice = calculateAverageItemPrice(itemQuantities, prices);

		System.out.println("\nThanks for shopping with us! Here's your final Order:\n");
		displayShoppingCart(itemQuantities, items, prices);
		System.out.println("\nYour total was: " + total);
		System.out.println("Your average item price was: " + averageItemPrice);
		// TODO: ADD MOST AND LEAST EXPENSIVE ITEMS
	}

	private static double calculateAverageItemPrice(ArrayList<Integer> itemQuantities, ArrayList<Double> prices) {
		double sumItems = 0.0;
		int totalItems = 0;
		for (int i = 0; i < prices.size(); i++) {
			sumItems += prices.get(i) * itemQuantities.get(i);
			totalItems += itemQuantities.get(i);
		}
		return sumItems / totalItems;

	}

	private static double calculateCartTotal(ArrayList<Integer> itemQuantities, ArrayList<String> items,
			ArrayList<Double> prices) {
		double total = 0.0;
		for (int i = 0; i < items.size(); i++) {
			// do some fancy stuff
			total += prices.get(i) * itemQuantities.get(i);
		}
		return total;
	}

	private static void shoppingLoop(Scanner scnr, LinkedHashMap<String, Double> inventory,
			ArrayList<Integer> itemQuantities, ArrayList<String> items, ArrayList<Double> prices) {
		boolean doneShopping = true;
		do {
			// Get User Choice
			int itemNumber = getValidItemNumberChoice(scnr, inventory);

			// Retrieve entry of chosen item
			Entry<String, Double> purchasedItem = getSelectedItem(inventory, itemNumber);

			// Add that entry to the shopping cart
			updateShoppingCart(purchasedItem, itemQuantities, items, prices);

			// Display the shopping cart
			displayShoppingCart(itemQuantities, items, prices);

			System.out.println("\nWould you like to buy more items? (Y/n): ");
			doneShopping = (scnr.nextLine().toLowerCase().trim().equals("n"));

		} while (!doneShopping);
	}

	private static void displayShoppingCart(ArrayList<Integer> itemQuantities, ArrayList<String> items,
			ArrayList<Double> prices) {
		// TODO ADD STRING FORMATTING FOR THE HEADERS
		System.out.println("Your Shopping Cart:");
		System.out.println("#. Item\t\t\tPrice");
		System.out.println("=============================");
		if (items.isEmpty()) {
			System.out.println("Empty!");
		} else {
			for (int i = 0; i < items.size(); i++) {
				System.out.printf("%-23s%6.1f", (i + 1) + ". " + items.get(i) + "(" + itemQuantities.get(i) + "x)",
						(prices.get(i) * itemQuantities.get(i)));
				System.out.println();
			}
		}
	}

	private static int getValidItemNumberChoice(Scanner scnr, LinkedHashMap<String, Double> inventory) {
		int itemChoice = 0;
		boolean inputIsValid = false;
		displayInventory(inventory);

		do {
			try {
				itemChoice = Integer.parseInt(scnr.nextLine());
				validateChoiceInInventory(itemChoice, inventory.size());
				inputIsValid = true;
			} catch (InputMismatchException | IllegalArgumentException ex) {
				System.out.println("Sorry, you must enter a number between 1 and " + inventory.size() + ": ");
			}
		} while (!inputIsValid);
		return itemChoice;
	}

	public static void validateChoiceInInventory(int userInput, int inventoryLength) {
		if (userInput < 1 || userInput > inventoryLength) {
			throw new IllegalArgumentException();
		}
	}

	private static void updateShoppingCart(Entry<String, Double> purchasedItem, ArrayList<Integer> itemQuantities,
			ArrayList<String> items, ArrayList<Double> prices) {
		if (items.contains(purchasedItem.getKey())) {
			// do some fancy stuff
			itemQuantities.set(items.indexOf(purchasedItem.getKey()),
					itemQuantities.get(items.indexOf(purchasedItem.getKey())) + 1);
		} else {
			itemQuantities.add(1);
			items.add(purchasedItem.getKey());
			prices.add(purchasedItem.getValue());
		}
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
		// TODO: ADD STRING FORMATTING FOR THE HEADERS
		System.out.println("#. Item\t\t\tPrice");
		System.out.println("=============================");

		int i = 1;
		for (Entry<String, Double> product : inventory.entrySet()) {
			System.out.printf("%d. " + product.getKey() + "\t\t" + product.getValue() + "%n", i);
			i++;
		}
		System.out.println("\nEnter a number 1-" + inventory.size() + " to add an item to your shopping cart!");
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

}
