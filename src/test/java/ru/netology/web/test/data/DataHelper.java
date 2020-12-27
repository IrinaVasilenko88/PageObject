package ru.netology.web.test.data;

import com.codeborne.selenide.impl.Randomizer;
import lombok.Value;
import lombok.val;
import ru.netology.web.test.page.DashboardPage;
import ru.netology.web.test.page.TransferPage;

import java.util.Random;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardInfo {
        private String cardNumber;
    }

    public static CardInfo getFirstCardInfo() {
        return new CardInfo("5559 0000 0000 0001");
    }

    public static CardInfo getSecondCardInfo() {
        return new CardInfo("5559 0000 0000 0002");
    }

    public static class RandomAmount {
        private int transferSum;
    }

    public static RandomAmount getTransferSum() {
        Random random = new Random();
        int cardBalance = new DashboardPage().getCardBalance();
        int transferSum = random.nextInt(cardBalance);
        return transferSum();
    }


}




