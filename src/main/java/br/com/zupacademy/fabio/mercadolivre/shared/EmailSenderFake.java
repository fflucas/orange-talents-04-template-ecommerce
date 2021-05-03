package br.com.zupacademy.fabio.mercadolivre.shared;

import org.springframework.stereotype.Component;

@Component
public class EmailSenderFake implements EmailSender{
    @Override
    public void simpleMessage(String sender, String productName) {
        System.out.println("Hello, "+sender+"\nYou received a new question on your product: "+productName);
    }
}
