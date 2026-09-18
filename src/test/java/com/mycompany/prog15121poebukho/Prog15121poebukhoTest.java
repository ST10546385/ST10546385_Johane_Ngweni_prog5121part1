/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.prog15121poebukho;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Prog15121poebukhoTest.java
 *
 * @author Game
 */
public class Prog15121poebukhoTest {

    // Expected messages, kept in one place so a wording change is a one-line fix.
    private static final String BAD_USERNAME =
            "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
    private static final String BAD_PASSWORD =
            "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
    private static final String REGISTERED =
            "User has been registered successfully.";
    private static final String LOGIN_OK =
            "Welcome! It is great to see you again.";
    private static final String LOGIN_FAIL =
            "Username or password incorrect, please try again.";

    // ---------- checkUserName ----------

    @Test
    public void testCheckUserName_valid() {
        assertTrue(Prog15121poebukho.checkUserName("Kyl_1"));   // 5 chars, has _
        assertTrue(Prog15121poebukho.checkUserName("a_"));      // short but valid
        assertTrue(Prog15121poebukho.checkUserName("_"));       // boundary: just an underscore
    }

    @Test
    public void testCheckUserName_invalid() {
        assertFalse(Prog15121poebukho.checkUserName("user"));          // no underscore
        assertFalse(Prog15121poebukho.checkUserName("long_username")); // too long
        assertFalse(Prog15121poebukho.checkUserName("kyle_1"));        // 6 chars: just over the limit
        assertFalse(Prog15121poebukho.checkUserName(""));              // empty
    }

    // ---------- checkPasswordComplexity ----------

    @Test
    public void testCheckPasswordComplexity_valid() {
        assertTrue(Prog15121poebukho.checkPasswordComplexity("Ch&&sec@ke99!"));
        assertTrue(Prog15121poebukho.checkPasswordComplexity("Passw0rd!")); // 9 chars
        assertTrue(Prog15121poebukho.checkPasswordComplexity("Abcdef1!")); // exactly 8 chars
    }

    @Test
    public void testCheckPasswordComplexity_invalid() {
        assertFalse(Prog15121poebukho.checkPasswordComplexity("password"));  // no capital/number/special
        assertFalse(Prog15121poebukho.checkPasswordComplexity("Password"));  // no number/special
        assertFalse(Prog15121poebukho.checkPasswordComplexity("Pass1234"));  // no special char
        assertFalse(Prog15121poebukho.checkPasswordComplexity("password1!")); // no capital
        assertFalse(Prog15121poebukho.checkPasswordComplexity("Pas1!"));     // too short
        assertFalse(Prog15121poebukho.checkPasswordComplexity(""));          // empty
    }

    // ---------- checkCellPhoneNumber ----------

    @Test
    public void testCheckCellPhoneNumber_valid() {
        assertTrue(Prog15121poebukho.checkCellPhoneNumber("+27123456789"));
        assertTrue(Prog15121poebukho.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCheckCellPhoneNumber_invalid() {
        assertFalse(Prog15121poebukho.checkCellPhoneNumber("0123456789"));    // no international code
        assertFalse(Prog15121poebukho.checkCellPhoneNumber("+2712345678"));   // one digit short
        assertFalse(Prog15121poebukho.checkCellPhoneNumber("+271234567890")); // one digit too many
        assertFalse(Prog15121poebukho.checkCellPhoneNumber("+27abcdefghi"));  // letters
        assertFalse(Prog15121poebukho.checkCellPhoneNumber(""));              // empty
    }

    // ---------- registerUser ----------

    @Test
    public void testRegisterUser_success() {
        assertEquals(REGISTERED,
                Prog15121poebukho.registerUser("user_", "Passw0rd!"));
    }

    @Test
    public void testRegisterUser_badUsername() {
        assertEquals(BAD_USERNAME,
                Prog15121poebukho.registerUser("user", "Passw0rd!"));
    }

    @Test
    public void testRegisterUser_badPassword() {
        assertEquals(BAD_PASSWORD,
                Prog15121poebukho.registerUser("user_", "password"));
    }

    @Test
    public void testRegisterUser_bothBad_reportsUsernameFirst() {
        // Username is checked first, so that is the message we expect.
        assertEquals(BAD_USERNAME,
                Prog15121poebukho.registerUser("user", "password"));
    }

    // ---------- loginUser ----------

    @Test
    public void testLoginUser_success() {
        assertTrue(Prog15121poebukho.loginUser("Kyl_1", "Ch&&sec@ke99!", "Kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginUser_failure() {
        String storedUsername = "user_";
        String storedPassword = "Passw0rd!";

        assertFalse(Prog15121poebukho.loginUser("wrong", "Passw0rd!", storedUsername, storedPassword));
        assertFalse(Prog15121poebukho.loginUser("user_", "wrong", storedUsername, storedPassword));
        assertFalse(Prog15121poebukho.loginUser("USER_", "Passw0rd!", storedUsername, storedPassword)); // case sensitive
        assertFalse(Prog15121poebukho.loginUser("user_", "passw0rd!", storedUsername, storedPassword)); // case sensitive
    }

    // ---------- returnLoginStatus ----------

    @Test
    public void testReturnLoginStatus() {
        assertEquals(LOGIN_OK, Prog15121poebukho.returnLoginStatus(true));
        assertEquals(LOGIN_FAIL, Prog15121poebukho.returnLoginStatus(false));
    }
}