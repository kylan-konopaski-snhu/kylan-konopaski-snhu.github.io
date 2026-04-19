/*
 * Author: Kylan Konopaski
 * Date: 20 May 2025
 */
import java.util.ArrayList;
public class ContactService {
    private ArrayList<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact) {
        if (contacts.size() == 0) {
            contacts.add(contact);
        } else {
            for (Contact c : contacts) {
                if (contact.getContactId().equalsIgnoreCase(c.getContactId())) {
                    throw new IllegalArgumentException("A contact with that id already exists.");
                }
            }
            contacts.add(contact);
        }
    }

    public void deleteContact(String contactId) {
        var exists = false;
        Contact contactToRemove = null;
        if (!contacts.isEmpty()) {
            for (Contact c : contacts) {
                if (c.getContactId().equalsIgnoreCase(contactId)) {
                    contactToRemove = c;
                    exists = true;
                }
            }
        }
        if (exists) {
            contacts.remove(contactToRemove);
        } else {
            throw new IllegalArgumentException("There is no contact with that id in the contact list");
        }
    }

    public void updateFirstName(String contactId, String firstName) {
        var exists = false;
        for (Contact c : contacts) {
            if (c.getContactId().equalsIgnoreCase(contactId)) {
                c.setFirstName(firstName);
                exists = true;
            }
        }
        if (!exists) {
            throw new IllegalArgumentException("There is no contact with that id in the contact list");
        }
    }
    public void updateLastName(String contactId, String lastName) {
        var exists = false;
        for (Contact c : contacts) {
            if (c.getContactId().equalsIgnoreCase(contactId)) {
                c.setLastName(lastName);
                exists = true;
            }
        }
        if (!exists) {
            throw new IllegalArgumentException("There is no contact with that id in the contact list");
        }
    }
    public void updatePhone(String contactId, String phone) {
        var exists = false;
        for (Contact c : contacts) {
            if (c.getContactId().equalsIgnoreCase(contactId)) {
                c.setPhone(phone);
                exists = true;
            }
        }
        if (!exists) {
            throw new IllegalArgumentException("There is no contact with that id in the contact list");
        }
    }
    public void updateAddress(String contactId, String address) {
        var exists = false;
        for (Contact c : contacts) {
            if (c.getContactId().equalsIgnoreCase(contactId)) {
                c.setAddress(address);
                exists = true;
            }
        }
        if (!exists) {
            throw new IllegalArgumentException("There is no contact with that id in the contact list");
        }
    }
    public ArrayList<Contact> getContacts() {
        return contacts;
    }
}
