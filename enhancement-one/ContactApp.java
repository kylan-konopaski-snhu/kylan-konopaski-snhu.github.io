/*
 * Author: Kylan Konopaski
 * Version: 1.0
 * Date updated: 3/22/2026
 */
import java.util.Scanner;

/*
 * ContactApp provides a simple command-line interface for the user to interact with the contact management system
 */
public class ContactApp {

	public static void main(String[] args) {
		
		// Service layer handles the business logic
		ContactService service = new ContactService();
		
		// Scanner for user input
		Scanner scanner = new Scanner(System.in);
		
		// main application loop
		while (true) {
			System.out.println("-----------------------");
			// Display menu options
			System.out.println("1. Add Contact");
			System.out.println("2. View All Contacts");
			System.out.println("3. View Contact Details");
			System.out.println("4. Delete Contact");
			System.out.println("5. Quit");
			
			int choice = getValidMenuChoice(scanner);
			System.out.println("-----------------------");
			
			switch (choice) {
				case 1:
					addContactValidated(scanner, service);
					break;
					
				case 2:
					viewAllContacts(service);
					break;
					
				case 3:
					viewSingleContact(scanner, service);
					break;
				
				case 4:
					deleteContactIfFound(scanner, service);
					break;
					
				case 5:
					System.out.println("Quitting...");
					scanner.close();
					return;
			}
		}
	}
	
	/*
	 * Menu Validation
	 */
	private static int getValidMenuChoice(Scanner scanner) {
		while (true) {
			System.out.println("Choose an option: ");
			try {
				int choice = Integer.parseInt(scanner.nextLine());
				if (choice >= 1 && choice <= 5) {
					return choice;
				}
			} catch (Exception ignored) {}
			System.out.println("Invalid choice. Enter a number between 1 and 5.");
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
	private static void viewAllContacts(ContactService service) {
		if (service.getContacts().isEmpty()) {
			System.out.println("No contacts found");
			return;
		}
		for (Contact c : service.getContacts()) {
			System.out.println(c.getContactId() + " | " + c.getFirstName() + " " + c.getLastName());
		}
	}
	
	/*
	 * View a single contact
	 */
	private static void viewSingleContact(Scanner scanner, ContactService service) {
		System.out.print("Enter ID: ");
		int searchId = scanner.nextInt();
		scanner.nextLine();  // clears the input buffer
		
		try {
			Contact contactToDisplay = service.getContact(searchId);
			
			System.out.println("\nContact Details:");
		    System.out.println("ID: " + contactToDisplay.getContactId());
		    System.out.println("First Name: " + contactToDisplay.getFirstName());
		    System.out.println("Last Name: " + contactToDisplay.getLastName());
		    System.out.println("Phone: " + contactToDisplay.getPhone());
		    System.out.println("Address: " + contactToDisplay.getAddress());
		    
		} catch (Exception e) {
			System.out.println("Contact not found.");
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
}
