/*
 * Author: Kylan Konopaski
 * Version: 1.1
 * Date updated: 3/24/2026
 */
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

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
	 * Search all properties by keyword
	 */
	public List<Contact> findByKeyword(String keyword) {
		List<Contact> results = new ArrayList<>();
		
		if (keyword == null || keyword.isEmpty()) {
	        return results;
	    }
		
		String search = keyword.toLowerCase();
		
		for (Contact c : contacts.values()) {
			if (c.getFirstName().toLowerCase().contains(search) 
				|| c.getLastName().toLowerCase().contains(search) 
				|| c.getPhone().toLowerCase().contains(search) 
				|| c.getAddress().toLowerCase().contains(search)
			) {
				results.add(c);
			}
		}
		
		return results;
	}
	
	/*
	 * Searches for a contact by last name.
	 */
	public List<Contact> findByLastName(String lastName) {
		List<Contact> results = new ArrayList<>();
		
		if (lastName == null || lastName.isEmpty()) {
	        return results;
	    }
		
		for (Contact c : contacts.values()) {
			if (c.getLastName().toLowerCase().contains(lastName.toLowerCase())) {
				results.add(c);
			}
		}
		
		return results;
	}
	
	/*
	 * Searches for a contact by first name.
	 */
	public List<Contact> findByFirstName(String firstName) {
		List<Contact> results = new ArrayList<>();
		
		if (firstName == null || firstName.isEmpty()) {
	        return results;
	    }
		
		for (Contact c : contacts.values()) {
			if (c.getFirstName().toLowerCase().contains(firstName.toLowerCase())) {
				results.add(c);
			}
		}
		
		return results;
	}
	
	/*
	 * Searches for a contact by phone.
	 */
	public List<Contact> findByPhone(String phone) {
		List<Contact> results = new ArrayList<>();
		
		if (phone == null || phone.isEmpty()) {
	        return results;
	    }
		
		for (Contact c : contacts.values()) {
			if (c.getPhone().toLowerCase().contains(phone.toLowerCase())) {
				results.add(c);
			}
		}
		
		return results;
	}
	
	/*
	 * Searches for a contact by address.
	 */
	public List<Contact> findByAddress(String address) {
		List<Contact> results = new ArrayList<>();
		
		if (address == null || address.isEmpty()) {
	        return results;
	    }
		
		for (Contact c : contacts.values()) {
			if (c.getAddress().toLowerCase().contains(address.toLowerCase())) {
				results.add(c);
			}
		}
		
		return results;
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
	public List<Contact> getAll() {
		return new ArrayList<>(contacts.values());
	}
}
