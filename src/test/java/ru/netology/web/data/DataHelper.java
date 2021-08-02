package ru.netology.web.data;

import lombok.Value;
import java.util.Random;


public class DataHelper {
    public DataHelper() {}

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {

        return new VerificationCode("12345");
    }

    @Value
    public static class CardNumber {
        private String number;
    }

    public static CardNumber getFirstCardNumber() {

        return new CardNumber("5559 0000 0000 0001");
    }

    public static CardNumber getSecondCardNumber() {

        return new CardNumber("5559 0000 0000 0002");
    }

    @Value
    public static class AmountForTransfer {
        private String amount;
    }

    public static String getValidAmount() {
        String validAmount = "200";
        return validAmount;
    }

    public static String getInvalidAmount() {
        String invalidAmount = "20000";
        return invalidAmount;
    }
}
