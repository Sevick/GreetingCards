package com.fbytes.greetings.model;
/**
 * Created by S on 17.12.2016.
 */

import java.util.Set;

public class CardTemplate {

    Integer id;
    String name;
    String greetingText;
    Set<CardField> fields;

    public CardTemplate(Integer id, String name, String greetingText, Set<CardField> fields){
        this.id=id;
        this.name=name;
        this.greetingText=greetingText;
        this.fields=fields;
    };

    @Override
    public String toString(){
        return id.toString()+": "+name;
    }

    public Set<CardField> getFields() {
        return fields;
    }

    public Integer getId() {
        return id;
    }


    public void setFields(Set<CardField> fields) {
        this.fields = fields;
    }

    public String getGreetingText() {
        return greetingText;
    }

    public void setGreetingText(String greetingText) {
        this.greetingText = greetingText;
    }
}
