package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.getFirstCardInfo;
import static ru.netology.data.DataHelper.getSecondCardInfo;

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
        val firstCardInfo = getFirstCardInfo();
        val firstCardBalance = dashboardPage.getCardBalance(firstCardInfo.getCardNumber());
        val secondCardBalance= dashboardPage.getCardBalance(getSecondCardInfo().getCardNumber());
        dashboardPage.depositToFirstCard();
        val TransferPage = new TransferPage();
        TransferPage.transferMoneyFromSecondCard();
        val firstCardBalanceAfter = dashboardPage.getCardBalance(firstCardInfo.getCardNumber());
        val secondCardBalanceAfter = dashboardPage.getCardBalance(getSecondCardInfo().getCardNumber());
        val expected = secondCardBalanceAfter;
        val actual = dashboardPage.getCardBalance(getSecondCardInfo().getCardNumber());
        Assertions.assertEquals(expected, actual);

    }
    @Test
    void shouldTransferMoneyFromFirstCard() {
        val LoginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = LoginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val firstCardInfo = getFirstCardInfo();
        val firstCardBalance = dashboardPage.getCardBalance(firstCardInfo.getCardNumber());
        val secondCardBalance= dashboardPage.getCardBalance(getSecondCardInfo().getCardNumber());
        dashboardPage.depositToSecondCard();
        val TransferPage = new TransferPage();
        TransferPage.transferMoneyFromFirstCard();
        val firstCardBalanceAfter = dashboardPage.getCardBalance(firstCardInfo.getCardNumber());
        val secondCardBalanceAfter = dashboardPage.getCardBalance(getSecondCardInfo().getCardNumber());
        val expected = firstCardBalanceAfter;
        val actual = dashboardPage.getCardBalance(getFirstCardInfo().getCardNumber());
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void shouldNotTransferMoneyFromSecondCardMoreThanDeposit() {
        val LoginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = LoginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val firstCardInfo = getFirstCardInfo();
        val firstCardBalance = dashboardPage.getCardBalance(firstCardInfo.getCardNumber());
        val secondCardBalance= dashboardPage.getCardBalance(getSecondCardInfo().getCardNumber());
        dashboardPage.depositToFirstCard();
        val TransferPage = new TransferPage();
        TransferPage.transferMoreThanDepositFromSecondCard();
        val firstCardBalanceAfter = dashboardPage.getCardBalance(firstCardInfo.getCardNumber());
        val secondCardBalanceAfter = dashboardPage.getCardBalance(getSecondCardInfo().getCardNumber());
        $("WebElement").shouldHave(text("Недостаточно средств на счете для выполнения данной операции"));

    }
}
