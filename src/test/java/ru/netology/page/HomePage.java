package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class HomePage {
    private SelenideElement heading = $$("h2").find(text("Путешествие дня"));
    private SelenideElement buttonPayment = $$("button").find(exactText("Купить"));
    private SelenideElement buttonCredit = $$("button").find(exactText("Купить в кредит"));

    public HomePage() {
        heading.shouldBe(Condition.visible);
    }

    public PaymentPage goToPaymentPage() {
        buttonPayment.click();
        return new PaymentPage();
    }

    public CreditPage goToCreditPage() {
        buttonCredit.click();
        return new CreditPage();
    }
}

