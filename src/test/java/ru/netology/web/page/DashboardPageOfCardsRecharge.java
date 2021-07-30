package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPageOfCardsRecharge {
    private SelenideElement heading = $(withText("Пополнение карты"));
    private SelenideElement fieldAmount = $("[data-test-id=amount] .input__control");
    private SelenideElement fieldFrom = $("[data-test-id=from] .input__control");
    private SelenideElement buttonActionTransfer = $("[data-test-id=action-transfer]");

    public DashboardPageOfCards transferMoney (ElementsCollection buttonForTransfer,
                                               String amountForTransfer,
                                               DataHelper.CardNumber cardNumber) {
        heading.shouldBe(Condition.visible);
        fieldAmount.setValue(amountForTransfer);
        fieldFrom.setValue(cardNumber.getNumber());
        buttonActionTransfer.click();

        return new DashboardPageOfCards();
    }

    public DashboardPageOfCards returnCardsBalancesToInitialAmounts
            (ElementsCollection buttonForTransfer, DataHelper.CardNumber cardNumber) {
        heading.shouldBe(Condition.visible);
        fieldFrom.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        fieldFrom.setValue(cardNumber.getNumber());
        buttonActionTransfer.click();

        return new DashboardPageOfCards();
    }
}
