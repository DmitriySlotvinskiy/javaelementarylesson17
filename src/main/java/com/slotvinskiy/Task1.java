package com.slotvinskiy;

import com.google.gson.Gson;

import java.net.HttpURLConnection;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

//1) Написать консольную программу которая спрашивает у пользователя дату в формате 25.03.2020;
// И выводит курс доллара за эту дату или описание ошибки, используя HttpURLConnection.
//https://api.privatbank.ua/p24api/exchange_rates?json&date=01.12.2014

public class Task1 {

    public static final LocalDate CURRENT_DATE = LocalDate.now();
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.M.yyyy");
    public static final LocalDate PB_ESTABLISH_DATE = LocalDate.parse("19.03.1992", FORMATTER);
    public static final String URL = "https://api.privatbank.ua/p24api/exchange_rates?json&date=";

    public static void main(String[] args) {

        String date = "";
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Please enter date in format day.month.year: ");
            do {
                date = scanner.nextLine();
            } while (!isCorrect(date));
        } catch (Exception e) {
            showErrorMessage();
        }

        Response response = HttpUtil.sendRequest(URL + date);
        if (response.getResponseCode() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            ClassForParseResponseJson responseJson = gson.fromJson(response.getBody(), ClassForParseResponseJson.class);
            System.out.println(responseJson.get("USD"));
        } else {
            System.out.println("Something went wrong. Status code " + response.getResponseCode());
        }
    }

    private static boolean isCorrect(String date) {
        if (date == null || date.equals("") || date.length() < 8) {
            showErrorMessage();
            return false;
        }
        LocalDate inputDate = null;
        try {
            inputDate = LocalDate.parse(date, FORMATTER);
        } catch (Exception e) {
            showErrorMessage();
            return false;
        }
        if (inputDate.isAfter(ChronoLocalDate.from(PB_ESTABLISH_DATE)) &&
                inputDate.isBefore(ChronoLocalDate.from(CURRENT_DATE))) {
            return true;
        } else {
            showErrorMessage();
            return false;
        }
    }

    private static void showErrorMessage() {
        System.out.println("Wrong data format! Try to enter date one more time. Format day.month.year: ");
    }
}
