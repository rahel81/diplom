package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {
    private SelenideElement heading = $$("h3").find(text("Оплата по карте"));
    private SelenideElement cardNumberField = $(byText("Номер карты")).parent().$(".input__control");
    private SelenideElement monthField = $(byText("Месяц")).parent().$(".input__control");
    private SelenideElement yearField = $(byText("Год")).parent().$(".input__control");
    private SelenideElement holderField = $(byText("Владелец")).parent().$(".input__control");
    private SelenideElement CVCField = $(byText("CVC/CVV")).parent().$(".input__control");
    private SelenideElement continueButton = $$("button").find(exactText("Продолжить"));
    private SelenideElement statusOK = $(".notification_status_ok");
    private SelenideElement statusError = $(".notification_status_error");
    private SelenideElement inputInvalid = $(".input__sub");

    public PaymentPage() {
        heading.shouldBe(Condition.visible);
    }

    public void purchase(DataHelper.Card card) {
        cardNumberField.setValue(card.getNumber());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        holderField.setValue(card.getHolder());
        CVCField.setValue(card.getCVC());
        continueButton.click();
    }

    public void setStatusOK() {
        statusOK.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void setStatusError() {
        statusError.shouldBe(visible, Duration.ofSeconds(15));
    }

    public boolean setInputInvalid() {
        return inputInvalid.isDisplayed();
    }
}

