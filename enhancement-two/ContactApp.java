/*
 * Author: Kylan Konopaski
 * Version: 1.2
 * Date updated: 3/27/2026
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/*
 * ContactApp provides a simple command-line interface for the user to interact with the contact management system
 */
public class ContactApp {

	private enum SortType {
		NONE,
		ID,
		FIRST_NAME,
		LAST_NAME
	}
	
	private enum SortOrder {
		ASCENDING,
		DESCENDING
	}
	
	public static void main(String[] args) {
		
		// Service layer handles the business logic
		ContactService service = new ContactService();
		
		// Test Cases - uncomment when needed
		service.addContact(new Contact("Kylan", "Konopaski", "1234567890", "123 W Test St"));
		service.addContact(new Contact("Jared", "Goff", "0987654321", "123 W Test St"));
		service.addContact(new Contact("Dan", "Cambell", "5556667777", "456 N School Ave"));
		service.addContact(new Contact("Cade", "Cunningham", "1112223333", "777 W Detroit Blvd"));
		
		// Scanner for user input
		Scanner scanner = new Scanner(System.in);
		
		// main application loop
		while (true) {
			printSeparatorLine();
			// Display menu options
			System.out.println("1. Add Contact");
			System.out.println("2. View All Contacts");
			System.out.println("3. View or Update Contact Details");
			System.out.println("4. Advanced Contact Search");
			System.out.println("5. Delete Contact");
			System.out.println("6. Quit");
			
			int choice = getValidMenuChoiceRange(scanner, 1, 6);
			printSeparatorLine();
			
			switch (choice) {
				case 1:
					addContactValidated(scanner, service);
					break;
					
				case 2:
					viewAllContacts(scanner, service);
					break;
					
				case 3:
					viewOrUpdateSingleContact(scanner, service);
					break;
					
				case 4:
					advancedContactSearch(scanner, service);
					break;
				
				case 5:
					deleteContactIfFound(scanner, service);
					break;
					
				case 6:
					System.out.println("Quitting...");
					scanner.close();
					return;
			}
		}
	}
	
	/*
	 * Menu Validation
	 */
	private static int getValidMenuChoiceRange(Scanner scanner, int min, int max) {
		while (true) {
			System.out.println("Choose an option: ");
			try {
				int choice = Integer.parseInt(scanner.nextLine());
				if (choice >= min && choice <= max) {
					return choice;
				}
			} catch (Exception ignored) {}
			System.out.println("Invalid choice. Enter a number between " + min + " and " + max + ".");
		}
	}
	
	/*
	 * Add Contact
	 */
	private static void addContactValidated(Scanner scanner, ContactService service) {
		String first = getValidFirstName(scanner);
		String last = getValidLastName(scanner);
		String phone = getValidPhone(scanner);
		String address = getValidAddress(scanner);
		
		Contact contact = new Contact(first, last, phone, address);
		service.addContact(contact);
		
		System.out.println("Contact added! ID: " + contact.getContactId());
	}
	
	/*
	 * View all contacts
	 */
	private static void viewAllContacts(Scanner scanner, ContactService service) {
		displayAndSortLoop(scanner, service.getContacts());
	}
	
	/*
	 * View a single contact
	 */
	private static void viewOrUpdateSingleContact(Scanner scanner, ContactService service) {
		System.out.print("Enter ID: ");
		int searchId = scanner.nextInt();
		scanner.nextLine();  // clears the input buffer
		
		try {
			Contact contactToDisplay = service.getContact(searchId);
			
			printContactDetails(contactToDisplay);
		    promptUpdate(scanner, service, contactToDisplay);
		    
		} catch (Exception e) {
			System.out.println("Contact not found.");
		}
		
	}
	
	/*
	 * Prints a single contact's details
	 */
	private static void printContactDetails(Contact contactToDisplay) {
		System.out.println("\n[Contact Details]");
	    System.out.println("ID: " + contactToDisplay.getContactId());
	    System.out.println("First Name: " + contactToDisplay.getFirstName());
	    System.out.println("Last Name: " + contactToDisplay.getLastName());
	    System.out.println("Phone: " + contactToDisplay.getPhone());
	    System.out.println("Address: " + contactToDisplay.getAddress());
	}
	
	/*
	 * Prompts the user to update a contact
	 */
	private static void promptUpdate(Scanner scanner, ContactService service, Contact contactToDisplay) {
		while (true) {
			System.out.print("\nWould you like to update this contact? (y/n): ");
			String choice = scanner.nextLine().trim().toLowerCase();
			
			if (choice.equals("y")) {
				updateContactMenu(scanner, service, contactToDisplay);
				return;
			} else if (choice.equals("n")) {
				return;
			} else {
				System.out.println("Invalid input. Enter 'y' or 'n'.");
			}
		}
	}
	
	/*
	 * Displays the contact update menu
	 */
	private static void updateContactMenu(Scanner scanner, ContactService service, Contact contactToDisplay) {
		
		// main update loop
		while (true) {
			printSeparatorLine();
			printContactDetails(contactToDisplay);
			System.out.println("\n[Update Details]");
			// Display menu options
			System.out.println("1. First Name");
			System.out.println("2. Last Name");
			System.out.println("3. Phone");
			System.out.println("4. Address");
			System.out.println("5. Back");
			
			int choice = getValidMenuChoiceRange(scanner, 1, 5);
			int contactId = contactToDisplay.getContactId();
			printSeparatorLine();
			
			
			switch (choice) {
				
				case 1:
					String first = getValidFirstName(scanner);
					service.updateFirstName(contactId, first);
					System.out.println("First name updated.");
					break;
				case 2:
					String last = getValidLastName(scanner);
					service.updateLastName(contactId, last);
					System.out.println("Last name updated.");
					break;
				case 3:
					String phone = getValidPhone(scanner);
					service.updatePhone(contactId, phone);
					System.out.println("Phone number updated.");
					break;
				case 4:
					String address = getValidAddress(scanner);
					service.updateAddress(contactId, address);
					System.out.println("Address updated.");
					break;
				case 5:
					return;
			}
		}
	}
	
	/*
	 * Advanced Contact Search Menu
	 */
	private static void advancedContactSearch(Scanner scanner, ContactService service) {
		
		// search menu loop
		while (true) {
			
			printSeparatorLine();
			System.out.println("\n[Advanced Contact Search]");
			// Display menu options
			System.out.println("1. Keyword");
			System.out.println("2. First Name");
			System.out.println("3. Last Name");
			System.out.println("4. Phone");
			System.out.println("5. Address");
			System.out.println("6. Back");
			
			int choice = getValidMenuChoiceRange(scanner, 1, 6);
			printSeparatorLine();
			
			switch (choice) {
			case 1:
				searchKeyword(scanner, service);
				break;
				
			case 2:
				searchFirstName(scanner, service);
				break;
				
			case 3:
				searchLastName(scanner, service);
				break;
				
			case 4:
				searchPhone(scanner, service);
				break;
				
			case 5:
				searchAddress(scanner, service);
				break;
				
			case 6:
				return;
			}
		}
	}
	
	
	/*
	 * Searches contacts by keyword input
	 */
	private static void searchKeyword(Scanner scanner, ContactService service) {
		System.out.println("Enter a keyword: ");
		String keyword = scanner.nextLine();
		
		displayAndSortLoop(scanner, service.searchByKeyword(keyword));
	}
	
	/*
	 * Searches contacts by first name input
	 */
	private static void searchFirstName(Scanner scanner, ContactService service) {
		System.out.println("Enter first name or part of one: ");
		String keyword = scanner.nextLine();
		
		displayAndSortLoop(scanner, service.searchByFirstName(keyword));
	}
	
	/*
	 * Searches contacts by last name input
	 */
	private static void searchLastName(Scanner scanner, ContactService service) {
		System.out.println("Enter last name or part of one: ");
		String keyword = scanner.nextLine();

		displayAndSortLoop(scanner, service.searchByLastName(keyword));
	}
	
	/*
	 * Searches contacts by phone input
	 */
	private static void searchPhone(Scanner scanner, ContactService service) {
		System.out.println("Enter phone number or part of one: ");
		String keyword = scanner.nextLine();
		
		displayAndSortLoop(scanner, service.searchByPhone(keyword));
	}
	
	/*
	 * Searches contacts by address input
	 */
	private static void searchAddress(Scanner scanner, ContactService service) {
		System.out.println("Enter address or part of one: ");
		String keyword = scanner.nextLine();
		
		displayAndSortLoop(scanner, service.searchByAddress(keyword));
	}
	
	/*
	 * Display results of a contact search
	 */
	private static void displayResults(List<Contact> results, SortType sortType, SortOrder sortOrder) {
		if (results.isEmpty()) {
			System.out.println("No contacts found.");
			return;
		}
		Comparator<Contact> comparator;
		
		// Sort if applicable
		List<Contact> sorted = new ArrayList<>(results);
		
		switch(sortType) {
			case ID:
				comparator = Comparator.comparing(Contact::getContactId);
				break;
			case FIRST_NAME:
				comparator = Comparator.comparing(Contact::getFirstName);
				break;
			case LAST_NAME:
				comparator = Comparator.comparing(Contact::getLastName);
				break;
			default:
				comparator = null;
		}
		
		if (comparator != null) {
			if (sortOrder == SortOrder.DESCENDING) {
				comparator = comparator.reversed();
			}
			sorted.sort(comparator);
		}
		
		
		int idWidth = Contact.MAX_ID_LENGTH;
		int firstWidth = Contact.MAX_FIRST_NAME_LENGTH;
		int lastWidth = Contact.MAX_LAST_NAME_LENGTH;
		int phoneWidth = Contact.PHONE_LENGTH;
		int addressWidth = Contact.MAX_ADDRESS_LENGTH;
		
		String format = "%-" + idWidth + "s | %-" + firstWidth + "s | %-" + lastWidth + "s | %-" + phoneWidth + "s | %-" + addressWidth + "s%n";
		
		// Header
		System.out.printf(format, "ID", "First Name", "Last Name", "Phone", "Address");
		
		printSeparatorLine();
		
		for (Contact c : sorted) {
			System.out.printf(format,
					c.getContactId(),
					c.getFirstName(),
					c.getLastName(),
					c.getPhone(),
					c.getAddress());
		}
	}
	
	/*
	 * Sort loop for displaying results
	 */
	private static void displayAndSortLoop(Scanner scanner, List<Contact> results) {
		SortType sortType = SortType.NONE;
		SortOrder sortOrder = SortOrder.ASCENDING;
		
		while (true) {
			displayResults(results, sortType, sortOrder);
			
			System.out.print("\nWould you like to sort results? (y/n): ");
			String input = scanner.nextLine().trim().toLowerCase();
			
			if (input.equals("n")) {
				return;
			} else if (input.equals("y")) {
				sortType = getSortType(scanner);
				sortOrder = getSortOrder(scanner);
			} else {
				System.out.println("Invalid input.");
			}
		}
	}
	
	/*
	 * Displays sort menu and returns sort type
	 */
	private static SortType getSortType(Scanner scanner) {
		printSeparatorLine();
		System.out.println("\n[Sort Options]");
		// Display menu options
		System.out.println("1. By ID (default)");
		System.out.println("2. By First Name");
		System.out.println("3. By Last Name");
		
		int choice = getValidMenuChoiceRange(scanner, 1, 3);
		printSeparatorLine();
		
		switch (choice) {
			
			case 1: return SortType.ID;
			case 2: return SortType.FIRST_NAME;
			case 3: return SortType.LAST_NAME;
			default: return SortType.NONE;
		}
	}
	
	/*
	 * Displays sort order menu and returns sort order
	 */
	private static SortOrder getSortOrder(Scanner scanner) {
		printSeparatorLine();
		System.out.println("\n[Sort Order]");
		// Display menu options
		System.out.println("1. Ascending (default)");
		System.out.println("2. Descending");
		
		int choice = getValidMenuChoiceRange(scanner, 1, 2);
		printSeparatorLine();
		
		switch (choice) {
			
			case 1: return SortOrder.ASCENDING;
			case 2: return SortOrder.DESCENDING;
			default: return SortOrder.ASCENDING;
		}
	}
	
	/*
	 * Delete a contact
	 */
	private static void deleteContactIfFound(Scanner scanner, ContactService service) {
		System.out.print("Enter ID: ");
		int deleteId = scanner.nextInt();
		scanner.nextLine();  // clears the input buffer
		
		try {
			service.deleteContact(deleteId);
			System.out.println("Contact ID: " + deleteId + " deleted.");
		    
		} catch (Exception e) {
			System.out.println("Contact not found.");
		}
	}
	
	/*
	 * Prompts user until a valid first name is entered
	 */
	private static String getValidFirstName(Scanner scanner) {
		while (true) {
			System.out.print("First Name: ");
			String input = scanner.nextLine();
			
			try {
				Contact.validateFirstName(input);
				return input;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/*
	 * Prompts user until a valid last name is entered
	 */
	private static String getValidLastName(Scanner scanner) {
		while (true) {
			System.out.print("Last Name: ");
			String input = scanner.nextLine();
			
			try {
				Contact.validateLastName(input);
				return input;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/*
	 * Prompts user until a valid phone is entered
	 */
	private static String getValidPhone(Scanner scanner) {
		while (true) {
			System.out.print("Phone: ");
			String input = scanner.nextLine();
			
			try {
				Contact.validatePhone(input);
				return input;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/*
	 * Prompts user until a valid address is entered
	 */
	private static String getValidAddress(Scanner scanner) {
		while (true) {
			System.out.print("Address: ");
			String input = scanner.nextLine();
			
			try {
				Contact.validateAddress(input);
				return input;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
	}
	
	/*
	 * Prints a separator line for display
	 */
	private static void printSeparatorLine() {
		System.out.println("-".repeat(Contact.MAX_COMBINED_LENGTH));
	}
}
