package com.example.apartment_for_sale_bot.configuration;

import com.example.apartment_for_sale_bot.service.TelegramService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class ApartmentParser {
    private TelegramService telegramService;

    public ApartmentParser(TelegramService telegramService) {
        this.telegramService = telegramService;
    }

    public void parser() {
        try {
            String url = "https://www.city24.ee/ru/real-estate-search/apartments-for-sale/tallinn-haabersti-linnaosa,tallinn-kadriorg,tallinn-kesklinna-linnaosa,tallinn-kristiine-linnaosa,tallinn-lasnamae-linnaosa,tallinn-mustamae-linnaosa,tallinn-nomme-linnaosa,tallinn-pirita-linnaosa,tallinn-pohja-tallinna-linnaosa,tallinn-vanalinn/price=eur-na-110000/rooms=1,2,3/size=32-na/id=540-city,64717-city,1240-city,1535-city,1897-city,2413-city,2670-city,3039-city,3166-city,64718-city/pg=3";
            Document document = (Document) Jsoup.connect(url).get();
            Elements apartmentElements = document.select(".cFgNFC");

            for (Element apartmentElement : apartmentElements) {
                String title = apartmentElement.select(".gMHPxv").text();
                String description = apartmentElement.select(".fqSpgB").text();

                //telegramService.sendTelegramMessage(chatId, "Title" + title + "\nDescription" + description);

                System.out.println("Title: " + title);
                System.out.println("Description:" + description);
                System.out.println("--------");
            }
        } catch (IOException e) {
            System.err.println("Error occurred while parsing apartment data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
