package com.infosupport.facade;

import jakarta.ejb.EJBException;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;

@MessageDriven(name = "AangifteMdb")
public class AangifteMdb implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try{
            String m = message.getBody(String.class);
            System.out.println(m);
        } catch (JMSException e) {
            throw new EJBException(e);
        }
    }
}
