/*
 * Author: Kylan Konopaski
 * Version: 1.0
 * Date updated: 3/21/2026
 */
import java.util.HashMap;
import java.util.Collection;

/*
 * ContactStorage is responsible for storing and retrieving Contact objects.
 * This class separates data management from business logic.
 */
public class ContactStorage {
	
	// HashMap allows for fast lookup of contacts by ID. O(1) time complexity.
	private HashMap<Integer, Contact> contacts = new HashMap<>();
	
	/*
	 * Adds a new contact to storage.
	 * Throws an exception if a contact with the same ID already exists.
	 */
	public void add(Contact contact) {
		if (contacts.containsKey(contact.getContactId())) {
			throw new IllegalArgumentException("A contact with that id already exists.");
		}
		contacts.put(contact.getContactId(), contact);
	}
	
	/*
	 * Finds a contact by its ID.
	 * Returns null if the contact does not exist.
	 */
	public Contact findById(int contactId) {
		return contacts.get(contactId);
	}
	
	/*
	 * Deletes a contact by ID.
	 * Throws an exception if the contact does not exist.
	 */
	public void delete(int contactId) {
		if (!contacts.containsKey(contactId)) {
			throw new IllegalArgumentException("There is no contact with that id.");
		}
		contacts.remove(contactId);
	}
	
	/*
	 * Returns all stored contacts.
	 */
	public Collection<Contact> getAll() {
		return contacts.values();
	}
}
