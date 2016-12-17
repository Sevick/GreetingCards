package com.fbytes.greetings.service;
/**
 * Created by S on 17.12.2016.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.fbytes.greetings.model.Card;
import com.fbytes.greetings.model.CardField;
import com.fbytes.greetings.model.CardTemplate;

public class CardService {

    static CardService instance;

    private List<Card> cards =new ArrayList<>();

    private CardService(){
    }

    static public CardService getInstance() {
        if (instance == null)
            instance = new CardService();
        return instance;
    }


    public Card createCardFromTemplate(CardTemplate cardTemplate) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter the recipient name:");
        String recipient=scanner.next();
        Card newCard =new Card(cardTemplate.getId(), recipient);

        System.out.println("and fill all required fields...");
        cardTemplate.getFields().forEach(cardField -> {
            String value="";
            Boolean validated=false;

            while (!validated) {
                System.out.print(cardField.getName() + ":");
                value = scanner.next();

                try {
                    cardField.validate(value);
                    newCard.addValue(cardField.getName(), value);
                    validated=true;
                } catch (CardField.ValidationException e) {
                    System.out.println("Bad value:"+value);
                    System.out.println(e.getMessage());
                }
            }
        });

        cards.add(newCard);
        return newCard;
    }


    public String createCardText(CardTemplate cardTemplate, Card card){
        String patternString = "%(" + card.getFieldsValues().keySet().stream().collect(Collectors.joining("|")) + ")%";
        String template = cardTemplate.getGreetingText();
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(template);

        StringBuffer sb = new StringBuffer();
        while(matcher.find()) {
            System.out.println("Found group:"+matcher.group(1));
            matcher.appendReplacement(sb, card.getFieldsValues().get(matcher.group(1)));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public List<Card> getCards() {
        return cards;
    }
}
