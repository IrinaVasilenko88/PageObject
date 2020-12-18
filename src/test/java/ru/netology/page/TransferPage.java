package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");

    public void transferMoneyFromFirstCard(){
        $("[data-test-id=amount].input__control").setValue("200");
        $("[data-test-id=from].input__control]").setValue("5559 0000 0000 0001");
        $("[data-test-id=action-transfer][class=button__text]").click();
    }
    public void transferMoneyFromSecondCard(){
        $("[data-test-id=amount].input__control").setValue("200");
        $("[data-test-id=from].input__control]").setValue("5559 0000 0000 0002");
        $("[data-test-id=action-transfer][class=button__text]").click();
    }
}
