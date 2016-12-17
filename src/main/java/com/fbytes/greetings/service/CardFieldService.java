package com.fbytes.greetings.service;
/**
 * Created by S on 17.12.2016.
 */

import java.util.HashMap;
import java.util.Map;

import com.fbytes.greetings.model.CardField;


public class CardFieldService {
    static CardFieldService instance;

    Map<String, CardField> fields;

    private CardFieldService() {

        fields=new HashMap<String, CardField>();

        fields.put("name",new CardField("name") {
            @Override
            public void validate(String value) throws ValidationException {
                String pattern = "^[^ ][ A-z]{3,7}$";
                if (!value.matches(pattern))
                    throw (new ValidationException("name should contains from 4 to 7 letter and cant starts with the space"));
            }
        });

        fields.put("age",new CardField("age") {
            @Override
            public void validate(String value) throws ValidationException {
                String pattern = "^[0-9]{1,3}$";
                if (!value.matches(pattern))
                    throw (new ValidationException("age should contains 2-3 digits"));
                ;
            }
        });

        fields.put("carmodel",new CardField("carmodel") {
            Map<String, Integer> carsEngVolLookup = new HashMap<String, Integer>() {
                {
                    put("ford focus", 2);
                    put("ford mustang", 4);
                    put("porsche", 3);
                    put("chevrolet spark", 1);
                }
            };

            @Override
            public void validate(String value) throws ValidationException {
                Integer engVol = carsEngVolLookup.get(value);
                if (engVol != null && engVol <= 2)
                    throw (new ValidationException("car models with engine over 2000cc accepted"));
            }
        });
    }


    public CardField getCardFieldByName(String name){
        return fields.get(name);
    }



    static public CardFieldService getInstance() {
        if (instance==null)
            instance = new CardFieldService();
        return instance;
    }
}
