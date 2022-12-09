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
    private final SelenideElement submitEmailButton = $(byXpath("//button[@data-testid='mr-form-signup-btn-start-1']"));
    private final SelenideElement submitNameButton = $(byXpath("//button[@data-testid='mr-form-signup-btn-start-2']"));
    private final SelenideElement submitPasswordButton = $(byXpath("//button[@data-testid='mr-form-signup-btn-start-3']"));

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
        submitNameButton.shouldBe(Condition.visible).click();
    }

    public void enterEmail(String email) {
        emailInput.shouldBe(Condition.visible).clear();
        emailInput.shouldBe(Condition.visible).setValue(email);
        submitEmailButton.shouldBe(Condition.visible).click();
    }

    public void enterPassword(String password) {
        passwordInput.shouldBe(Condition.visible).clear();
        passwordInput.shouldBe(Condition.visible).setValue(password);
    }

    public void submitPasswordForm() {
        submitPasswordButton.shouldBe(Condition.visible).click();
    }

    public EmailConfirmPage signUp(String name, String email, String password) {
        enterEmail(email);
        enterName(name);

        enterPassword(password);
        termsInput.shouldBe(Condition.visible).click();
        submitPasswordForm();

        return new EmailConfirmPage();
    }
}
