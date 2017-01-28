package ru.stqa.jft.mantis.model;

/**
 * Created by Anna on 2017-01-28.
 */
public class MailMessage {

    public String to;
    public String text;

    public MailMessage(String to, String text) {
        this.to = to;
        this.text = text;
    }
}
