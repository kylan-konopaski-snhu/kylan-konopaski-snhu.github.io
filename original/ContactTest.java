/*
 * Author: Kylan Konopaski
 * Date: 20 May 2025
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class ContactTest {
    @Test
    void testValidContact() {
        Contact contact = new Contact("1234567890", "Jared", "Goff", "1234567890", "123 West St");
        assertEquals("1234567890", contact.getContactId());
        assertEquals("Jared", contact.getFirstName());
        assertEquals("Goff", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("123 West St", contact.getAddress());
    }

    @Test
    void testNullContactId() {
        assertThrows(IllegalArgumentException.class, () -> {
            Contact contact = new Contact(null, "Jared", "Goff", "1234567890", "123 West St");
        });
    }

    @Test
    void testTooLongContactId() {
        assertThrows(IllegalArgumentException.class, () -> {
            Contact contact = new Contact("12345678900", "Jared", "Goff", "1234567890", "123 West St");
        });
    }

    @Test
    void testNullFirstName() {
        assertThrows(IllegalArgumentException.class, () -> {
            Contact contact = new Contact("12345678900", null, "Goff", "1234567890", "123 West St");
        });
    }

    @Test
    void testTooLongFirstName() {
        assertThrows(IllegalArgumentException.class, () -> {
            Contact contact = new Contact("12345678900", "JaredJaredJared", "Goff", "1234567890", "123 West St");
        });
    }

    @Test
    void testNullLastName() {
        assertThrows(IllegalArgumentException.class, () -> {
            Contact contact = new Contact("12345678900", "Jared", null, "1234567890", "123 West St");
        });
    }

    @Test
    void testTooLongLastName() {
        assertThrows(IllegalArgumentException.class, () -> {
            Contact contact = new Contact("12345678900", "Jared", "GoffGoffGoff", "1234567890", "123 West St");
        });
    }

    @Test
    void testNullPhone() {
        assertThrows(IllegalArgumentException.class, () -> {
            Contact contact = new Contact("12345678900", "Jared", "Goff", null, "123 West St");
        });
    }

    @Test
    void testTooLongPhone() {
        assertThrows(IllegalArgumentException.class, () -> {
            Contact contact = new Contact("12345678900", "Jared", "Goff", "12345678900", "123 West St");
        });
    }

    @Test
    void testTooShortPhone() {
        assertThrows(IllegalArgumentException.class, () -> {
            Contact contact = new Contact("12345678900", "Jared", "Goff", "123456789", "123 West St");
        });
    }

    @Test
    void testNullAddress() {
        assertThrows(IllegalArgumentException.class, () -> {
            Contact contact = new Contact("12345678900", "Jared", "Goff", "1234567890", null);
        });
    }

    @Test
    void testTooLongAddress() {
        assertThrows(IllegalArgumentException.class, () -> {
            Contact contact = new Contact("12345678900", "Jared", "Goff", "1234567890", "123 West St123 West St123 West St");
        });
    }
}
