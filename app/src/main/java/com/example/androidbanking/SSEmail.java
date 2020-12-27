package com.example.androidbanking;


import java.util.Properties;
import java.util.concurrent.Callable;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;


public class SSEmail implements Callable<String> {

    String to, subject, msg;
    SSEmail(String to, String subject, String msg){
        this.to = to;
        this.subject = subject;
        this.msg = msg;
    }
     void sendEmail(String to,String subject, String msg) {
        final String fromEmail = "adarshdeep3594@gmail.com";
        final String password = "11nov1997";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Authenticator auth = new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };

        Session session = Session.getDefaultInstance(props, auth);

        EmailUtil.sendEmail(session, to, subject, msg);

    }


    @Override
    public String call() throws Exception {
        sendEmail(to,subject,msg);
        return null;
    }
}