/*
 * Author: Kylan Konopaski
 * Date: 20 May 2025
 */
public class Contact {
    private final String contactId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Contact(String contactId, String firstName, String lastName, String phone, String address) {
        if (contactId == null || contactId.length() > 10) {
            throw new IllegalArgumentException("ContactID is required and must be less than 10 characters.");
        }
        if (firstName == null || firstName.length() > 10) {
            throw new IllegalArgumentException("First name is required and must be less than 10 characters.");
        }
        if (lastName == null || lastName.length() > 10) {
            throw new IllegalArgumentException("Last name is required and must be less than 10 characters.");
        }
        if (phone == null || phone.length() != 10) {
            throw new IllegalArgumentException("Phone number is required and must be exactly 10 characters.");
        }
        if (address == null || address.length() > 30) {
            throw new IllegalArgumentException("Address is required and must be less than 30 characters.");
        }

        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }
    public String getContactId() {
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
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.length() > 10) {
            throw new IllegalArgumentException("First name is required and must be less than 10 characters.");
        }
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        if (lastName == null || lastName.length() > 10) {
            throw new IllegalArgumentException("Last name is required and must be less than 10 characters.");
        }
        this.lastName = lastName;
    }
    public void setPhone(String phone) {
        if (phone == null || phone.length() != 10) {
            throw new IllegalArgumentException("Phone number is required and must be exactly 10 characters.");
        }
        this.phone = phone;
    }
    public void setAddress(String address) {
        if (address == null || address.length() > 30) {
            throw new IllegalArgumentException("Address is required and must be less than 30 characters.");
        }
        this.address = address;
    }
}
