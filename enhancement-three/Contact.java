/*
 * Author: Kylan Konopaski
 * Version: 2.1
 * Date updated: 3/30/2026
 */
public class Contact {
	
	// Constants for validation
	public static final int MAX_ID_LENGTH = 5; // used for display purposes only
    public static final int MAX_FIRST_NAME_LENGTH = 10;
    public static final int MAX_LAST_NAME_LENGTH = 20;
    public static final int PHONE_LENGTH = 10;
    public static final int MAX_ADDRESS_LENGTH = 40;
    public static final int MAX_COMBINED_LENGTH = MAX_ID_LENGTH + MAX_FIRST_NAME_LENGTH + MAX_LAST_NAME_LENGTH + PHONE_LENGTH + MAX_ADDRESS_LENGTH; // used for display purposes only
    
    private final int contactId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    
    // auto-assigned ID
    private static int idCounter = 1;

    /*
     * Constructor
     */
    public Contact(String firstName, String lastName, String phone, String address) {
        
    	// Auto-generate ID 
    	this.contactId = idCounter++;
    	
        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
        setAddress(address);
    }
    
    /*
     * Constructor for loading contacts from DB with existing IDs
     */
    public Contact(int contactId, String firstName, String lastName, String phone, String address) {
    	this.contactId = contactId;
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.phone = phone;
    	this.address = address;
    }
    
    /*
     * Sync id counter
     */
    public static void setIdCounter(int value) {
    	idCounter = value;
    }

    /*
     * Validation Methods
     */
    public static void validateFirstName(String firstName) {
    	if (firstName == null || firstName.length() > MAX_FIRST_NAME_LENGTH) {
    		throw new IllegalArgumentException("First name must be non-null and <= " + MAX_FIRST_NAME_LENGTH + " characters.");
    	}
    }

    public static void validateLastName(String lastName) {
    	if (lastName == null || lastName.length() > MAX_LAST_NAME_LENGTH) {
    		throw new IllegalArgumentException("Last name must be non-null and <= " + MAX_LAST_NAME_LENGTH + " characters.");
    	}
    }
    
    public static void validatePhone(String phone) {
    	if (phone == null || phone.length() != PHONE_LENGTH || !phone.matches("\\d+")) {
    		throw new IllegalArgumentException("Phone must be exactly " + PHONE_LENGTH + " digits.");
    	}
    }

    public static void validateAddress(String address) {
    	if (address == null || address.length() > MAX_ADDRESS_LENGTH) {
    		throw new IllegalArgumentException("Address must be non-null and <= " + MAX_ADDRESS_LENGTH + " characters.");
    	}
    }
    
    /*
     * Accessors
     */
    public int getContactId() {
        return contactId;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public String getAddress() {
        return address;
    }
    
    /*
     * Mutators
     */
    public void setFirstName(String firstName) {
    	validateFirstName(firstName);
    	this.firstName = firstName;
    }
    public void setLastName(String lastName) {
    	validateLastName(lastName);
    	this.lastName = lastName;
    }
    public void setPhone(String phone) {
    	validatePhone(phone);
    	this.phone = phone;
    }
    public void setAddress(String address) {
    	validateAddress(address);
    	this.address = address;
    }
}
