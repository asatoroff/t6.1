package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPageOfCards;
import ru.netology.web.page.DashboardPageOfCardsRecharge;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTests {

    @Test
    void shouldTransferMoneyFromFirstCardToTheSecond() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val dashboardPageOfCardsRecharge = new DashboardPageOfCardsRecharge();
        val dashboardPageOfCards = new DashboardPageOfCards();

        val balanceFirstCardBeforTransfer = dashboardPageOfCards.getFirstCardBalance();
        val balanceSecondCardBeforTransfer = dashboardPageOfCards.getSecondCardBalance();

        dashboardPageOfCardsRecharge.transferMoney(DashboardPageOfCards.clickSecondButton(),
                DataHelper.getValidAmount(), DataHelper.getFirstCardNumber());

        val expectedBalanceFirstCardAfterTransfer = dashboardPageOfCards.getFirstCardBalance();
        val expectedBalanceSecondCardAfterTransfer = dashboardPageOfCards.getSecondCardBalance();

        val actualChangedFirstCardBalance = balanceFirstCardBeforTransfer - Integer.parseInt(DataHelper.getValidAmount());
        val actualChangedSecondCardBalance = balanceSecondCardBeforTransfer + Integer.parseInt(DataHelper.getValidAmount());

        assertEquals(expectedBalanceFirstCardAfterTransfer, actualChangedFirstCardBalance);
        assertEquals(expectedBalanceSecondCardAfterTransfer, actualChangedSecondCardBalance);

        dashboardPageOfCardsRecharge.returnCardsBalancesToInitialAmounts
                (DashboardPageOfCards.clickFirstButton(), DataHelper.getSecondCardNumber());

        val initialBalanceFirstCardBeforTransfer = dashboardPageOfCards.getFirstCardBalance();
        val initialBalanceSecondCardBeforTransfer = dashboardPageOfCards.getSecondCardBalance();

        assertEquals(balanceFirstCardBeforTransfer, initialBalanceFirstCardBeforTransfer);
        assertEquals(balanceSecondCardBeforTransfer, initialBalanceSecondCardBeforTransfer);
        }


    @Test
    void shouldNotTransferMoneyFromFirstCardToTheSecond() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val dashboardPageOfCardsRecharge = new DashboardPageOfCardsRecharge();
        val dashboardPageOfCards = new DashboardPageOfCards();

        val balanceFirstCardBeforTransfer = dashboardPageOfCards.getFirstCardBalance();
        val balanceSecondCardBeforTransfer = dashboardPageOfCards.getSecondCardBalance();

        dashboardPageOfCardsRecharge.transferMoney (DashboardPageOfCards.clickSecondButton(),
                DataHelper.getInvalidAmount(), DataHelper.getFirstCardNumber());

        val balanceFirstCardAfterTransfer = dashboardPageOfCards.getFirstCardBalance();
        val balanceSecondCardAfterTransfer = dashboardPageOfCards.getSecondCardBalance();
        assertEquals(balanceFirstCardBeforTransfer, balanceFirstCardAfterTransfer);
        assertEquals(balanceSecondCardBeforTransfer, balanceSecondCardAfterTransfer);

    }
}
