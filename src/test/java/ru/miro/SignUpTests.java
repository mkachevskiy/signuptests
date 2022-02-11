package ru.miro;

import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.miro.pages.EmailConfirmPage;
import ru.miro.pages.SignUpPage;

import static com.codeborne.selenide.Selenide.open;

public class SignUpTests {
    private SignUpPage signUpPage;

    @BeforeEach
    public void setUp() {
        signUpPage = open("https://miro.com/signup/", SignUpPage.class);
    }

    @Test
    public void successfulSighUp() {
        Faker faker = new Faker();
        String name = faker.name().fullName();
        String emailAddress = faker.internet().emailAddress();
        String password = faker.internet().password(true);

        EmailConfirmPage emailConfirmPage = signUpPage.signUp(name, emailAddress, password);
        Assertions.assertTrue(emailConfirmPage.verifySignUpForm());
    }

    @Test
    public void tryToSighUpWithEmptyFields() {
        signUpPage.submitForm();
        Assertions.assertAll("Form validations doesn't work on the page",
                    () -> Assertions.assertEquals("Please enter your name.", signUpPage.getNameError().getText()),
                    () -> Assertions.assertTrue(signUpPage.getNameError().isDisplayed()),
                    () -> Assertions.assertEquals("Enter your email address.", signUpPage.getEmailError().getText()),
                    () -> Assertions.assertTrue(signUpPage.getEmailError().isDisplayed()),
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
        signUpPage.enterPassword(pass);
        Assertions.assertEquals(passHint, signUpPage.getPasswordHint().shouldBe(Condition.visible).getText());
    }

    @ParameterizedTest
    @ValueSource(strings = {"bademail", "bademail.com", "@horse.com", "bad@email"})
    public void verifyBadEmails(String email) {
        signUpPage.enterEmail(email);
        signUpPage.submitForm();
        Assertions.assertEquals("Enter a valid email address.", signUpPage.getEmailError().shouldBe(Condition.visible).getText());
    }
}
