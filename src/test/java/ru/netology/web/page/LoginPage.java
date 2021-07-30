package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[name=login]");
    private SelenideElement passwordField = $("[name=password]");
    private SelenideElement loginButton = $("[data-test-id=action-login]");

    public VerificationPage validLogin(DataHelper.AuthInfo authInfo) {
        loginField.setValue(authInfo.getLogin());
        passwordField.setValue(authInfo.getPassword());
        loginButton.click();
        return new VerificationPage();
    }



}
