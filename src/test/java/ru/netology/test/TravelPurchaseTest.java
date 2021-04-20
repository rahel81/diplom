package ru.netology.test;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.BaseSQL;
import ru.netology.data.DataHelper;
import ru.netology.page.HomePage;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TravelPurchaseTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldPaymentApprovedDebit() {
        val homePage = new HomePage();
        val cardData = homePage.goToPaymentPage();
        val approvedCard = DataHelper.getCardApproved();
        cardData.purchase(approvedCard);
        cardData.setStatusOK();
        val paymentId = BaseSQL.getPaymentId();
        val statusPaymentDebit = BaseSQL.getStatusDebitCardPayment(paymentId);
        val paymentAmount = BaseSQL.getAmountPayment(paymentId);
        assertEquals("APPROVED", statusPaymentDebit);
        assertEquals("4500000", paymentAmount);
    }


    @Test
    void shouldPaymentApprovedCredit() {
        val homePage = new HomePage();
        val cardData = homePage.goToCreditPage();
        val approvedCard = DataHelper.getCardApproved();
        cardData.purchase(approvedCard);
        cardData.setStatusOK();
        val paymentId = BaseSQL.getPaymentId();
        val statusPaymentCredit = BaseSQL.getStatusCreditPayment(paymentId);
        assertEquals("APPROVED", statusPaymentCredit);
    }

    @Test
    void shouldPaymentDeclinedDebit() {
        val homePage = new HomePage();
        val cardData = homePage.goToPaymentPage();
        val declinedCard = DataHelper.getCardDeclined();
        cardData.purchase(declinedCard);
        cardData.setStatusError();
        val paymentId = BaseSQL.getPaymentId();
        val statusPaymentDebit = BaseSQL.getStatusDebitCardPayment(paymentId);
        assertThat(statusPaymentDebit, equalTo("DECLINED"));
    }

    @Test
    void shouldPaymentInvalidHolder() {
        val homePage = new HomePage();
        val cardDebit = homePage.goToPaymentPage();
        val valueInvalid = DataHelper.getCardHolderInvalid();
        cardDebit.purchase(valueInvalid);
        cardDebit.setInputInvalid();
        val cardCredit = homePage.goToCreditPage();
        cardCredit.purchase(valueInvalid);
        cardCredit.setInputInvalid();
    }

    @Test
    void shouldPaymentHolderEmptyField() {
        val homePage = new HomePage();
        val cardDebit = homePage.goToPaymentPage();
        val valueInvalid = DataHelper.getCardHolderEmptyField();
        cardDebit.purchase(valueInvalid);
        cardDebit.setInputInvalid();
        val cardCredit = homePage.goToCreditPage();
        cardCredit.purchase(valueInvalid);
        cardCredit.setInputInvalid();
    }

    @Test
    void shouldPaymentInvalidNumberCard() {
        val homePage = new HomePage();
        val cardDebit = homePage.goToPaymentPage();
        val valueInvalid = DataHelper.getCardNumberInvalid();
        cardDebit.purchase(valueInvalid);
        cardDebit.setInputInvalid();
        val cardCredit = homePage.goToCreditPage();
        cardCredit.purchase(valueInvalid);
        cardCredit.setInputInvalid();
    }

    @Test
    void shouldPaymentNumberEmptyField() {
        val homePage = new HomePage();
        val cardDebit = homePage.goToPaymentPage();
        val valueInvalid = DataHelper.getCardNumberEmptyField();
        cardDebit.purchase(valueInvalid);
        cardDebit.setInputInvalid();
        val cardCredit = homePage.goToCreditPage();
        cardCredit.purchase(valueInvalid);
        cardCredit.setInputInvalid();
    }

    @Test
    void shouldPaymentInvalidMonth() {
        val homePage = new HomePage();
        val cardDebit = homePage.goToPaymentPage();
        val valueInvalid = DataHelper.getCardMonthInvalid();
        cardDebit.purchase(valueInvalid);
        cardDebit.setInputInvalid();
        val cardCredit = homePage.goToCreditPage();
        cardCredit.purchase(valueInvalid);
        cardCredit.setInputInvalid();
    }

    @Test
    void shouldPaymentMonthEmptyField() {
        val homePage = new HomePage();
        val cardDebit = homePage.goToPaymentPage();
        val valueInvalid = DataHelper.getCardMonthEmptyField();
        cardDebit.purchase(valueInvalid);
        cardDebit.setInputInvalid();
        val cardCredit = homePage.goToCreditPage();
        cardCredit.purchase(valueInvalid);
        cardCredit.setInputInvalid();
    }

    @Test
    void shouldPaymentInvalidYear() {
        val homePage = new HomePage();
        val cardDebit = homePage.goToPaymentPage();
        val valueInvalid = DataHelper.getCardYearInvalid();
        cardDebit.purchase(valueInvalid);
        cardDebit.setInputInvalid();
        val cardCredit = homePage.goToCreditPage();
        cardCredit.purchase(valueInvalid);
        cardCredit.setInputInvalid();
    }

    @Test
    void shouldPaymentYearEmptyField() {
        val homePage = new HomePage();
        val cardDebit = homePage.goToPaymentPage();
        val valueInvalid = DataHelper.getCardYearEmptyField();
        cardDebit.purchase(valueInvalid);
        cardDebit.setInputInvalid();
        val cardCredit = homePage.goToCreditPage();
        cardCredit.purchase(valueInvalid);
        cardCredit.setInputInvalid();
    }

    @Test
    void shouldPaymentInvalidCVC() {
        val homePage = new HomePage();
        val cardDebit = homePage.goToPaymentPage();
        val valueInvalid = DataHelper.getCardCVCInvalid();
        cardDebit.purchase(valueInvalid);
        cardDebit.setInputInvalid();
        val cardCredit = homePage.goToCreditPage();
        cardCredit.purchase(valueInvalid);
        cardCredit.setInputInvalid();
    }

    @Test
    void shouldPaymentCVCEmptyField() {
        val homePage = new HomePage();
        val cardDebit = homePage.goToPaymentPage();
        val valueInvalid = DataHelper.getCardCVCEmptyField();
        cardDebit.purchase(valueInvalid);
        cardDebit.setInputInvalid();
        val cardCredit = homePage.goToCreditPage();
        cardCredit.purchase(valueInvalid);
        cardCredit.setInputInvalid();
    }

    @Test
    void shouldPaymentNumberCardFalse() {
        val homePage = new HomePage();
        val cardDebit = homePage.goToPaymentPage();
        val valueInvalid = DataHelper.getCardNumberFalse();
        cardDebit.purchase(valueInvalid);
        cardDebit.setInputInvalid();
        val cardCredit = homePage.goToCreditPage();
        cardCredit.purchase(valueInvalid);
        cardCredit.setInputInvalid();
    }

    @Test
    void shouldPaymentAllFieldEmpty() {
        val homePage = new HomePage();
        val cardDebit = homePage.goToPaymentPage();
        val valueInvalid = DataHelper.getCardAllFieldEmpty();
        cardDebit.purchase(valueInvalid);
        cardDebit.setInputInvalid();
        val cardCredit = homePage.goToCreditPage();
        cardCredit.purchase(valueInvalid);
        cardCredit.setInputInvalid();
    }

    @Test
    void shouldPaymentDeclinedCredit() {
        val homePage = new HomePage();
        val cardData = homePage.goToCreditPage();
        val declinedCard = DataHelper.getCardDeclined();
        cardData.purchase(declinedCard);
        cardData.setStatusError();
        val paymentId = BaseSQL.getPaymentId();
        val statusPaymentCredit = BaseSQL.getStatusCreditPayment(paymentId);
        assertEquals("DECLINED", statusPaymentCredit);
    }
}
