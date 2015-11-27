package com;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Dominic on 27-Nov-15.
 */
public class MessageHolder {
        private final SimpleStringProperty emailNumber;
        private final SimpleStringProperty emailAddress;
        private final SimpleStringProperty subject;
        private final SimpleStringProperty content;


        public MessageHolder(String numEmail, String email, String subject, String content) {
            this.emailNumber = new SimpleStringProperty(numEmail);
            this.emailAddress = new SimpleStringProperty(email);
            this.subject = new SimpleStringProperty(subject);
            this.content = new SimpleStringProperty(content);
        }

    public String getEmailNumber() {
        return emailNumber.get();
    }

    public String getEmailAddress() {
        return emailAddress.get();
    }

    public String getSubject() {
        return subject.get();
    }


    public String getContent() {
        return content.get();
    }
}

