public class ContactBook {

    private static final int MAX_CONTACTS = 5;
    private Contact[] contacts;
    private int numberOfContacts;

    public ContactBook() {
        numberOfContacts = 0;
        contacts = new Contact[MAX_CONTACTS];
    }

    public int getNumberOfContacts() {
        return numberOfContacts;
    }

    public boolean contactWithSameNumberExists(Contact contact) {
        for (int i = 0; i < numberOfContacts; i++) {
            if (contacts[i].getPhoneNumber().equalsIgnoreCase(contact.getPhoneNumber())) {
                return true;
            }
        }
        return false;
    }

    // TODOs 2, 4, 5, 6 combined: addContact method
    public boolean addContact(Contact contact) {
        if (numberOfContacts < MAX_CONTACTS) {
            if (contactWithSameNumberExists(contact)) {
                throw new IllegalArgumentException("Phone number already exists");
            }
            contacts[numberOfContacts++] = contact;
            return true;
        } else {
            return false;
        }
    }

    // TODO 8: search by phone number
    public Contact searchContactByPhone(String phoneNumber) {
        for (int i = 0; i < numberOfContacts; i++) {
            if (contacts[i].getPhoneNumber().equalsIgnoreCase(phoneNumber)) {
                return contacts[i];
            }
        }
        return null;
    }

    // TODO 11: delete by phone number with exception
    public boolean deleteContactByPhone(String phoneNumber) {
        for (int i = 0; i < numberOfContacts; i++) {
            if (contacts[i].getPhoneNumber().equalsIgnoreCase(phoneNumber)) {
                System.arraycopy(contacts, i + 1, contacts, i, numberOfContacts - i - 1);
                contacts[--numberOfContacts] = null;
                return true;
            }
        }
        throw new IllegalArgumentException("Record not found to delete");
    }
}
