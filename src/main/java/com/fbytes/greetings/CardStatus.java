package com.fbytes.greetings;
/**
 * Created by S on 17.12.2016.
 */

public enum CardStatus {
    CREATED{
        @Override
        public String toString() {
            return "created";
        }
    },SENDING{
        @Override
        public String toString() {
            return "sending";
        }
    }, SENT{
        @Override
        public String toString() {
            return "sent   ";
        }
    }, SENDFAILED{
        @Override
        public String toString() {
            return "failed ";
        }
    };

}
