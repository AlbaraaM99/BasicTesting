import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ContactBookTest {

    static ContactBook contactBook;

    @BeforeAll
    static void setUp() {
        contactBook = new ContactBook();
    }

    @Test
    public void testAddContact() {
        Contact testContact = new Contact("AmazonStudent", "1111122334");

        // ✅ TODO 3: Get current contact count
        int currentNumOfContacts = contactBook.getNumberOfContacts();

        // ✅ TODO 1: Add contact
        contactBook.addContact(testContact);

        // ✅ Assert that count increased by 1
        assertEquals(currentNumOfContacts + 1, contactBook.getNumberOfContacts());
    }

    @ParameterizedTest
    @CsvSource({
            "Alex, 1239292",
            "Taylor, 23939258",
            "Alice, 33939252",
            "Clark, 43939251",
            "Toni, 53939257",
            "Casey, 63939258"
    })
    public void testAddContact_onSizeLimitExceed_returnsFalseAndNotException(String name, String phone) {
        Contact testContact = new Contact(name, phone);

        // Should return true for first 5 contacts, false on 6th
        boolean result = contactBook.addContact(testContact);
        if (contactBook.getNumberOfContacts() <= 5) {
            assertTrue(result);
        } else {
            assertFalse(result);
        }
    }

    @Test
    public void testAddContact_onSameNumber_ThrowsIllegalArgumentException() {
        Contact testContact1 = new Contact("Alex", "11111");
        Contact testContact2 = new Contact("Toni", "11111");

        // First one is added
        contactBook.addContact(testContact1);

        // Second one should throw
        assertThrows(IllegalArgumentException.class, () -> {
            contactBook.addContact(testContact2);
        });
    }

    @Test
    public void testSearchContactByPhone() {
        // ✅ TODO 7
        Contact testContactForPhone = new Contact("Toni", "184048");
        contactBook.addContact(testContactForPhone);

        Contact foundContact = contactBook.searchContactByPhone("184048");

        assertNotNull(foundContact);
        assertEquals("Toni", foundContact.getName());
        assertEquals("184048", foundContact.getPhoneNumber());
    }

    @Test
    public void testRemoveContactByPhone_found_returnsTrue() {
        Contact testContactForPhone = new Contact("Wills", "939503");
        contactBook.addContact(testContactForPhone);

        // ✅ TODO 9
        assertTrue(contactBook.deleteContactByPhone("939503"));
    }

    @Test
    public void testRemoveContactByPhone_notFound_throwsIllegalArgumentException() {
        // ✅ TODO 10
        assertThrows(IllegalArgumentException.class, () -> {
            contactBook.deleteContactByPhone("923746439503");
        });
    }
}
