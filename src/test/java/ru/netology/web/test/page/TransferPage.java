package ru.netology.web.test.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.test.data.DataHelper;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");

    public void transferMoney(String amount, DataHelper.CardInfo cardInfo){
        $("[data-test-id='amount'] .input__control").setValue(amount);
        $("[data-test-id='from'] .input__control").setValue(cardInfo.getCardNumber());
        $("[data-test-id='action-transfer']").click();
    }
   public SelenideElement transferError(){
        return $(withText("Недостаточно средств на счете для выполнения данной операции"));
   }
 }
