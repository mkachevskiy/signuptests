package ru.miro;

import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.miro.pages.EmailConfirmPage;
import ru.miro.pages.SignUpPage;

import java.io.File;

import static com.codeborne.selenide.Selenide.open;

public class SignUpTests {
    private SignUpPage signUpPage;
    private String name;
    private String emailAddress;
    private String password;

    @BeforeAll
    public static void clearAllureResults() {
        try {
            File results = new File("allure-results/");
            FileUtils.cleanDirectory(results);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @BeforeEach
    public void setUp() {
        signUpPage = open("https://miro.com/signup/", SignUpPage.class);

        Faker faker = new Faker();
        name = faker.name().fullName();
        emailAddress = faker.internet().emailAddress();
        password = faker.internet().password(true);
    }

    @Test
    public void successfulSighUp() {
        EmailConfirmPage emailConfirmPage = signUpPage.signUp(name, emailAddress, password);
        Assertions.assertTrue(emailConfirmPage.verifySignUpForm());
    }

    @Test
    public void tryToSighUpWithEmptyEmail() {
        signUpPage.enterEmail("");
        Assertions.assertEquals("Enter your email address.", signUpPage.getEmailError().getText());
        Assertions.assertTrue(signUpPage.getEmailError().isDisplayed());
    }

    @Test
    public void tryToSighUpWithEmptyName() {
        signUpPage.enterEmail(emailAddress);
        signUpPage.enterName("");
        Assertions.assertEquals("Please enter your name.", signUpPage.getNameError().getText());
        Assertions.assertTrue(signUpPage.getNameError().isDisplayed());
    }

    @Test
    public void tryToSighUpWithEmptyPasswordAndTerms() {
        signUpPage.enterEmail(emailAddress);
        signUpPage.enterName(name);
        signUpPage.enterPassword("");
        signUpPage.submitPasswordForm();
        Assertions.assertAll("Form validations doesn't work on the page",
                () -> Assertions.assertEquals("Enter your password.", signUpPage.getPasswordError().getText()),
                () -> Assertions.assertTrue(signUpPage.getPasswordError().isDisplayed()),
                () -> Assertions.assertEquals("Please agree with the Terms to sign up.", signUpPage.getTermsError().getText()),
                () -> Assertions.assertTrue(signUpPage.getTermsError().isDisplayed()));
    }

    @ParameterizedTest
    @CsvSource({"1234 , Please use 8+ characters for secure password.",
            "12345678, Weak password",
            "sdfsd5678, So-so password",
            "ASDzxc!@#, Good password",
            "ASDzxc!@#123, Great password"})
    public void verifyPasswordHints(String pass, String passHint) {
        signUpPage.enterEmail(emailAddress);
        signUpPage.enterName(name);
        signUpPage.enterPassword(pass);

        Assertions.assertEquals(passHint, signUpPage.getPasswordHint().shouldBe(Condition.visible).getText());
    }

    @ParameterizedTest
    @ValueSource(strings = {"bademail", "bademail.com", "@horse.com", "bad@email"})
    public void verifyBadEmails(String email) {
        signUpPage.enterEmail(email);
        Assertions.assertEquals("Enter a valid email address.", signUpPage.getEmailError().shouldBe(Condition.visible).getText());
    }
}
