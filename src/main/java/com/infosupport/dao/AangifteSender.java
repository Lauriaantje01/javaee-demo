package com.infosupport.dao;


import com.infosupport.domein.Aangifte;
import jakarta.annotation.Resource;
import jakarta.ejb.Schedule;
import jakarta.ejb.Stateless;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSProducer;
import jakarta.jms.Queue;

@Stateless
public class AangifteSender {

    @Resource(name = "jms/aangifte")
    private Queue queue;

    @Resource(name = "jms/connectionFactory")
    private ConnectionFactory connectionFactory;

    @Schedule(hour = "*", minute = "*", second = "*/20", persistent = false )
    public void doeIets() {
        Aangifte aangifte = new Aangifte("12345", 300);
        System.out.println("hallo hallo");
        try(var context = connectionFactory.createContext()){
            JMSProducer producer = context.createProducer();
            producer.send(queue, aangifte.toString());
        }
    }


}
