/*
 * Author: Kylan Konopaski
 * Version: 2.1
 * Date updated: 3/24/2026
 */
import java.util.List;

public class ContactService {
	
	// Storage layer is separated from service logic
	private ContactStorage storage = new ContactStorage();

	/*
	 * Adds new contact
	 */
    public void addContact(Contact contact) {
        storage.add(contact);
    }

    /*
     * Deletes a contact by ID
     */
    public void deleteContact(int contactId) {
        storage.delete(contactId);
    }

    /*
     * Helper method to retrieve a contact or throws an exception if not found.
     * This removes duplicated lookup logic across the existing update methods.
     */
    private Contact getContactOrThrow(int contactId) {
    	Contact contact = storage.findById(contactId);
    	if (contact == null) {
    		throw new IllegalArgumentException("THere is no contact with that id.");
    	}
    	return contact;
    }
    
    /*
     * Gets a contact by ID
     */
    public Contact getContact(int contactId) {
    	return getContactOrThrow(contactId);
    }
    
    /*
     * Returns contacts matching a keyword in any property
     */
    public List<Contact> searchByKeyword(String keyword) {
    	return storage.findByKeyword(keyword);
    }
    
    /*
     * Returns contacts matching last name
     */
    public List<Contact> searchByLastName(String lastName) {
    	return storage.findByLastName(lastName);
    }
    
    /*
     * Returns contacts matching first name
     */
    public List<Contact> searchByFirstName(String firstName) {
    	return storage.findByFirstName(firstName);
    }
    
    /*
     * Returns contacts matching phone
     */
    public List<Contact> searchByPhone(String phone) {
    	return storage.findByPhone(phone);
    }
    
    /*
     * Returns contacts matching address
     */
    public List<Contact> searchByAddress(String address) {
    	return storage.findByAddress(address);
    }
    
    /*
     * Updates the first name of a contact
     */
    public void updateFirstName(int contactId, String firstName) {
        Contact contact = getContactOrThrow(contactId);
        contact.setFirstName(firstName);
    }
    
    /*
     * Updates the last name of a contact
     */
    public void updateLastName(int contactId, String lastName) {
    	Contact contact = getContactOrThrow(contactId);
        contact.setLastName(lastName);
    }
    
    /*
     * Updates the phone number of a contact
     */
    public void updatePhone(int contactId, String phone) {
    	Contact contact = getContactOrThrow(contactId);
        contact.setPhone(phone);
    }
    
    /*
     * Updates the address of a contact
     */
    public void updateAddress(int contactId, String address) {
    	Contact contact = getContactOrThrow(contactId);
        contact.setAddress(address);
    }
    
    /*
     * Returns all contacts
     */
    public List<Contact> getContacts() {
    	return storage.getAll();
    }
}
