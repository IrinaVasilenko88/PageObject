package ru.netology.web.test.test;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.web.test.data.DataHelper;
import ru.netology.web.test.page.LoginPage;
import ru.netology.web.test.page.TransferPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.web.test.data.DataHelper.getFirstCardInfo;
import static ru.netology.web.test.data.DataHelper.getSecondCardInfo;

public class MoneyTransferTest {
    @BeforeAll
    static void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTransferMoneyFromSecondCard() {
        val LoginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = LoginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val firstCardBalance = dashboardPage.getCardBalance(getFirstCardInfo().getCardNumber());
        val secondCardBalance= dashboardPage.getCardBalance(getSecondCardInfo().getCardNumber());
        val transferPage = dashboardPage.depositToFirstCard();
        val amount = 5000;
        transferPage.transferMoney(String.valueOf(amount), DataHelper.getSecondCardInfo());
        val expectedFirstCardBalanceAfter =dashboardPage.getCardBalance(getFirstCardInfo().getCardNumber()) + amount;
        val expectedSecondCardBalanceAfter = dashboardPage.getCardBalance(getSecondCardInfo().getCardNumber()) - amount;
        Assertions.assertEquals(expectedFirstCardBalanceAfter, dashboardPage.getCardBalance(getFirstCardInfo().getCardNumber()));
        Assertions.assertEquals(expectedSecondCardBalanceAfter, dashboardPage.getCardBalance(getSecondCardInfo().getCardNumber()));

    }
    @Test
    void shouldTransferMoneyFromFirstCard() {
        val LoginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = LoginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val firstCardBalance = dashboardPage.getCardBalance(getFirstCardInfo().getCardNumber());
        val secondCardBalance= dashboardPage.getCardBalance(getSecondCardInfo().getCardNumber());
        val transferPage = dashboardPage.depositToSecondCard();
        val amount = 6000;
        transferPage.transferMoney(String.valueOf(amount), DataHelper.getFirstCardInfo() );
        val expectedFirstCardBalanceAfter =dashboardPage.getCardBalance(getFirstCardInfo().getCardNumber()) - amount;
        val expectedSecondCardBalanceAfter = dashboardPage.getCardBalance(getSecondCardInfo().getCardNumber()) + amount;
        Assertions.assertEquals(expectedFirstCardBalanceAfter, dashboardPage.getCardBalance(getFirstCardInfo().getCardNumber()));
        Assertions.assertEquals(expectedSecondCardBalanceAfter, dashboardPage.getCardBalance(getSecondCardInfo().getCardNumber()));

    }
    @Test
    void shouldNotTransferMoneyFromSecondCardMoreThanDeposit() {
        val LoginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = LoginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val firstCardInfo = getFirstCardInfo();
        val secondCardBalance= dashboardPage.getCardBalance(getSecondCardInfo().getCardNumber());
        val transferPage = dashboardPage.depositToFirstCard();
        val amount = 11000;
        transferPage.transferMoney(String.valueOf(amount), DataHelper.getSecondCardInfo() );
        transferPage.transferError();

    }
}
