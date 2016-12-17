package com.fbytes.greetings.model;
/**
 * Created by S on 17.12.2016.
 */


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.fbytes.greetings.CardStatus;
import com.google.gson.annotations.Expose;

public class Card {

    @Expose
    private Integer cardTemplateId;
    @Expose
    private Map<String, String> fieldsValues=new HashMap<String, String>();       // fieldName-fieldValue

    private String recipient;
    private LocalDateTime createDt;
    private CardStatus status;
    private LocalDateTime lastStatusChange;

    public Card(Integer cardTemplateId, String recipient){
        createDt=LocalDateTime.now();
        this.cardTemplateId = cardTemplateId;
        this.recipient=recipient;
        setStatus(CardStatus.CREATED);
    }

    public void addValue(String fieldsName, String value){
        fieldsValues.put(fieldsName, value);
    }


    public void setStatus(CardStatus newStatus){
        lastStatusChange=LocalDateTime.now();
        status=newStatus;
    }

    public LocalDateTime getCreateDt() {
        return createDt;
    }

    public CardStatus getStatus() {
        return status;
    }

    public LocalDateTime getLastStatusChange() {
        return lastStatusChange;
    }

    public Map<String, String> getFieldsValues() {
        return fieldsValues;
    }

    public String getRecipient() {
        return recipient;
    }

    @Override
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return new StringBuilder()
                .append(createDt.format(formatter))
                .append("   ")
                .append(recipient)
                .append("   ")
                .append(cardTemplateId)
                .append("   ")
                .append(status.toString())
                .toString();

    }
}
