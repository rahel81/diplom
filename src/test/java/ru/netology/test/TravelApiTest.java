package ru.netology.test;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.RestAPI;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.netology.data.RestAPI.getCreditPayment;

public class TravelApiTest {

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
    public void shouldStatusApprovedDebitCard() {
        val cardApproved = DataHelper.getCardApproved();
        final String status = RestAPI.getDebitCardPayment(cardApproved);
        assertTrue(status.contains("APPROVED"), "Должен подтверждать кредит по карте со статусом APPROVED");
    }

    @Test
    public void shouldStatusApprovedCreditCard() {
        val validInfo = DataHelper.getCardApproved();
        final String status = RestAPI.getCreditPayment(validInfo);
        assertTrue(status.contains("APPROVED"), "Должен подтверждать кредит по карте со статусом APPROVED");
    }

    @Test
    public void shouldStatusDeclinedDebitCard() {
        val validInfo = DataHelper.getCardDeclined();
        final String status = RestAPI.getDebitCardPayment(validInfo);
        assertTrue(status.contains("DECLINED"), "Не должен подтверждать покупку по карте со статусом APPROVED");
    }

    @Test
    public void shouldStatusDeclinedCreditCard() {
        val validInfo = DataHelper.getCardDeclined();
        final String response = getCreditPayment(validInfo);
        assertTrue(response.contains("DECLINED"), "Не должен подтверждать кредит по карте со статусом APPROVED");
    }
}
