package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Selenide.*;

public class DashboardPageOfCards {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private static ElementsCollection buttonsRefill = $$("[data-test-id=action-deposit]");

    public DashboardPageOfCards() {

        heading.shouldBe(Condition.visible);
    }

    public int getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalance(text);
    }

    public int getSecondCardBalance() {
        val text = cards.last().text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public static class buttonForTransfer {
        private ElementsCollection buttonsRefill;
    }

    public static ElementsCollection clickFirstButton() {
        buttonsRefill.first().click();
        return buttonsRefill;
    }

    public static ElementsCollection clickSecondButton() {
        buttonsRefill.last().click();
        return buttonsRefill;
    }
}
