package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {
    @BeforeAll
    static void setUp() {
        open("http://localhost:9999");
    }
    @Test
    void shouldTransferMoneyFromFirstCard(){
        val LoginPage =new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = LoginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val DashboardPage = new DashboardPage();
        val TransferPage = new TransferPage();
        TransferPage.transferMoneyFromFirstCard();
        DashboardPage.showBalanceOnCards();
    }

}
