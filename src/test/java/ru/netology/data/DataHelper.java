package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;
import java.util.Random;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class Card {
        private String number, month, year, holder, CVC;
    }

    static Faker faker = new Faker(new Locale("en"));
    static Random random = new Random();
    static int monthNumber = random.nextInt(10);
    static String monthInvalid = Integer.toString(monthNumber);
    static int yearNumber = random.nextInt(22);
    static String yearInvalid = Integer.toString(yearNumber);
    static int CVC = random.nextInt(444);
    static String validCVC = Integer.toString(CVC);


    public static Card getCardApproved() {
        return new Card("4444 4444 4444 4441", "04", "23", faker.name().fullName(), validCVC);
    }

    public static Card getCardDeclined() {
        return new Card("4444 4444 4444 4442", "04", "23", faker.name().fullName(), validCVC);
    }

    public static Card getCardHolderInvalid() {
        return new Card("4444 4444 4444 4441", "04", "23", "123", validCVC);
    }
    public static Card getCardHolderEmptyField() {
        return new Card("4444 4444 4444 4441", "04", "23", "", validCVC);
    }
    public static Card getCardNumberInvalid() {
        return new Card("4444 4444 4444 444", "04", "23", faker.name().fullName(), validCVC);
    }

    public static Card getCardNumberEmptyField() {
        return new Card(" ", "04", "23", faker.name().fullName(), validCVC);
    }

    public static Card getCardMonthInvalid() {
        return new Card("4444 4444 4444 4441", "monthInvalid", "23", faker.name().fullName(), validCVC);
    }

    public static Card getCardMonthEmptyField() {
        return new Card("4444 4444 4444 4441", " ", "23", faker.name().fullName(), validCVC);
    }

    public static Card getCardYearInvalid() {
        return new Card("4444 4444 4444 4441", "04", "yearInvalid", faker.name().fullName(), validCVC);
    }

    public static Card getCardYearEmptyField() {
        return new Card("4444 4444 4444 4441", "04", " ", faker.name().fullName(), validCVC);
    }

    public static Card getCardCVCInvalid() {
        return new Card("4444 4444 4444 4441", "04", "23", faker.name().fullName(), "@@@");
    }

    public static Card getCardCVCEmptyField() {
        return new Card("4444 4444 4444 4441", "04", "23", faker.name().fullName(), " ");
    }

    public static Card getCardNumberFalse() {
        return new Card("4444 4444 4444 4444", "04", "23", faker.name().fullName(), validCVC);
    }

    public static Card getCardAllFieldEmpty() {
        return new Card(" ", " ", " ", " ", " ");
    }
}

