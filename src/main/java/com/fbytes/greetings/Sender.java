package com.fbytes.greetings;
/**
 * Created by S on 17.12.2016.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.fbytes.greetings.model.Card;


public class Sender implements Callable<Void> {

    static final String sendAddress="http://www.greeting.com/";

    private Card card;

    public Sender(Card card) {
        this.card=card;
    }


    public Void call() throws Exception {


        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String cardJSON = gson.toJson(card);

        System.out.println("prepared json:");
        System.out.println(cardJSON);


        card.setStatus(CardStatus.SENDING);

        try {
            URL url = new URL(sendAddress+ card.getRecipient());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            OutputStream os = conn.getOutputStream();
            os.write(cardJSON.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED && conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                card.setStatus(CardStatus.SENDFAILED);
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            System.out.println("Output from Server ....");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            conn.disconnect();
            card.setStatus(CardStatus.SENT);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            card.setStatus(CardStatus.SENDFAILED);
        } catch (IOException e) {
            e.printStackTrace();
            card.setStatus(CardStatus.SENDFAILED);
        }

        return null;
    }
}
