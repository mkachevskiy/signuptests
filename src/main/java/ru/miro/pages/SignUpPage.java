package ru.miro.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class SignUpPage {
    private final SelenideElement nameInput = $(byId("name"));
    private final SelenideElement emailInput = $(byId("email"));
    private final SelenideElement passwordInput = $(byId("password"));
    private final SelenideElement termsInput = $(byClassName("mr-checkbox-1__check"));
    private final SelenideElement submitButton = $(byClassName("signup__submit"));

    @Getter
    private final SelenideElement nameError = $(byId("nameError"));
    @Getter
    private final SelenideElement emailError = $(byId("emailError"));
    @Getter
    private final SelenideElement passwordError = $(byXpath("//div[@data-testid='please-enter-your-password-1']"));
    @Getter
    private final SelenideElement passwordHint = $(byId("signup-form-password"));
    @Getter
    private final SelenideElement termsError = $(byId("termsError"));

    public void enterName(String name) {
        nameInput.shouldBe(Condition.visible).clear();
        nameInput.shouldBe(Condition.visible).setValue(name);
    }

    public void enterEmail(String email) {
        emailInput.shouldBe(Condition.visible).clear();
        emailInput.shouldBe(Condition.visible).setValue(email);
    }

    public void enterPassword(String password) {
        passwordInput.shouldBe(Condition.visible).clear();
        passwordInput.shouldBe(Condition.visible).setValue(password);
    }

    public void submitForm() {
        submitButton.shouldBe(Condition.visible).click();
    }

    public EmailConfirmPage signUp(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);

        termsInput.shouldBe(Condition.visible).click();
        submitForm();

        return new EmailConfirmPage();
    }
}
