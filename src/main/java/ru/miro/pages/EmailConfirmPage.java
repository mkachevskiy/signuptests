package ru.miro.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;

public class EmailConfirmPage {
    @Getter
    private final SelenideElement title = $(byClassName("signup__title-form"));

    @Getter
    private final SelenideElement codeField = $(byId("code"));

    public boolean verifySignUpForm() {
        return title.isDisplayed() && title.getText().equals("Check your email") && codeField.isEnabled();
    }
}
