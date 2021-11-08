package model;

import junit.framework.TestCase;

public class UserTest extends TestCase {

    public void testGetPatronymic() {
        User user = new User();
        user.setPatronymic("str");
        assertEquals("str", user.getPatronymic());
    }

    public void testGetFirstname() {
        User user = new User();
        user.setFirstname("str");
        assertEquals("str", user.getFirstname());
    }

    public void testGetSecondname() {
        User user = new User();
        user.setSecondname("str");
        assertEquals("str", user.getSecondname());
    }

    public void testGetEmail() {
        User user = new User();
        user.setEmail("str");
        assertEquals("str", user.getEmail());
    }

    public void testGetPassword() {
        User user = new User();
        user.setPassword("str");
        assertEquals("str", user.getPassword());
    }

    public void testGetRole() {
        User user = new User();
        user.setRole("str");
        assertEquals("str", user.getRole());
    }

    public void testIsBlocked() {
        User user = new User();
        user.setBlocked(true);
        assertEquals(true, user.isBlocked());
    }

}