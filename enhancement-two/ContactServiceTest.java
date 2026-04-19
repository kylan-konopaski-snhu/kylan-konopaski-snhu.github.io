/*
 * Author: Kylan Konopaski
 * Date: 20 May 2025
 */
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class ContactServiceTest {
    private ContactService service;

    @BeforeEach
    void setUp() {
        service = new ContactService();
    }

    @Test
    void testAddContact() {
        Contact contact = new Contact("Jared", "Goff", "1234567890", "123 West St");
        service.addContact(contact);
        assertEquals(1, service.getContacts().size());
    }

    @Test
    void testDeleteContact() {
        Contact contact = new Contact("Jared", "Goff", "1234567890", "123 West St");
        service.addContact(contact);
        service.deleteContact(contact.getContactId());
        assertTrue(service.getContacts().isEmpty());
    }

    @Test
    void testDeleteNonexistentContact() {
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact(0));
    }

    @Test
    void testUpdateFirstName() {
        Contact contact = new Contact("Jared", "Goff", "1234567890", "123 West St");
        service.addContact(contact);
        service.updateFirstName(contact.getContactId(), "John");
        assertEquals("John", contact.getFirstName());
    }

    @Test
    void testUpdateFirstNameNonexistent() {
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName(0, "John"));
    }

    @Test
    void testUpdateFirstNameInvalid() {
        Contact contact = new Contact("Jared", "Goff", "1234567890", "123 West St");
        service.addContact(contact);
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName(contact.getContactId(), "JohnJohnJohn"));
    }

    @Test
    void testUpdateLastName() {
        Contact contact = new Contact("Jared", "Goff", "1234567890", "123 West St");
        service.addContact(contact);
        service.updateLastName(contact.getContactId(), "John");
        assertEquals("John", contact.getLastName());
    }

    @Test
    void testUpdateLastNameNonexistent() {
        assertThrows(IllegalArgumentException.class, () -> service.updateLastName(0, "John"));
    }

    @Test
    void testUpdateLastNameInvalid() {
        Contact contact = new Contact("Jared", "Goff", "1234567890", "123 West St");
        service.addContact(contact);
        assertThrows(IllegalArgumentException.class, () -> service.updateLastName(contact.getContactId(), "JohnJohnJohn"));
    }

    @Test
    void testUpdatePhone() {
        Contact contact = new Contact("Jared", "Goff", "1234567890", "123 West St");
        service.addContact(contact);
        service.updatePhone(contact.getContactId(), "9998887777");
        assertEquals("9998887777", contact.getPhone());
    }

    @Test
    void testUpdatePhoneNonexistent() {
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone(0, "9998887777"));
    }

    @Test
    void testUpdatePhoneInvalid() {
        Contact contact = new Contact("Jared", "Goff", "1234567890", "123 West St");
        service.addContact(contact);
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone(contact.getContactId(), "invalid"));
    }

    @Test
    void testUpdateAddress() {
        Contact contact = new Contact("Jared", "Goff", "1234567890", "123 West St");
        service.addContact(contact);
        service.updateAddress(contact.getContactId(), "Address");
        assertEquals("Address", contact.getAddress());
    }

    @Test
    void testUpdateAddressNonexistent() {
        assertThrows(IllegalArgumentException.class, () -> service.updateAddress(0, "Address"));
    }

    @Test
    void testUpdateAddressInvalid() {
        Contact contact = new Contact("Jared", "Goff", "1234567890", "123 West St");
        service.addContact(contact);
        assertThrows(IllegalArgumentException.class, () -> service.updateAddress(contact.getContactId(), "invalidinvalidinvalidinvalidinvalid"));
    }
}
