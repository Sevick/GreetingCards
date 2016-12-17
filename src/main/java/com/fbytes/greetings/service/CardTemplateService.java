package com.fbytes.greetings.service;
/**
 * Created by S on 17.12.2016.
 */

import java.util.*;

import com.fbytes.greetings.model.CardTemplate;

public class CardTemplateService {
    static private CardTemplateService instance;
    private Integer lastCardTemplateId = 0;
    private Map<Integer, CardTemplate> templates = new HashMap<>();
    private CardFieldService cardFieldService = CardFieldService.getInstance();

    private CardTemplateService() {

        templates.put(lastCardTemplateId, new CardTemplate(lastCardTemplateId++,
                "NewYear greeting card", "Happy new year, dear %name%",
                new LinkedHashSet(Arrays.asList(cardFieldService.getCardFieldByName("name")))));
        templates.put(lastCardTemplateId, new CardTemplate(lastCardTemplateId++,
                "Happy birthday greeting card", "Happy birthday, %name%! %age% - is a good time",
                new LinkedHashSet(Arrays.asList(cardFieldService.getCardFieldByName("name"), cardFieldService.getCardFieldByName("age")))));
        templates.put(lastCardTemplateId, new CardTemplate(lastCardTemplateId++, "Anniversary greeting card",
                "Happy %age% anniversary, dear %name%",
                new LinkedHashSet(Arrays.asList(cardFieldService.getCardFieldByName("name"), cardFieldService.getCardFieldByName("age")))));
        templates.put(lastCardTemplateId, new CardTemplate(lastCardTemplateId++,
                "New car greeting card", "Congratulations with a new car! Wish you enjoy your %carmodel%",
                new LinkedHashSet(Arrays.asList(cardFieldService.getCardFieldByName("carmodel")))));
    }


    static public CardTemplateService getInstance() {
        if (instance == null)
            instance = new CardTemplateService();
        return instance;
    }


    public CardTemplate getTemplateById(Integer id) {
        return templates.get(id);
    }

    public Collection<CardTemplate> getTemplates() {
        return templates.values();
    }

}
