package dao;

import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author khail
 */
public class SendMail {

    public static String generateRandomToken() {
        Random random = new Random();
        return String.valueOf(random.nextInt(9999));
    }

    public static void sendMail(String recepient, String token) throws Exception {
        System.out.println("Prepare to sent email.");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        String myAccountEmail = "huydang139203@gmail.com";
        String password = "gdhwygwcnhlnbzzz";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Confirm Token");
            message.setContent("<!DOCTYPE html>\r\n"
                    + "<html>\r\n"
                    + "<body>\r\n"
                    + "\r\n"
                    + "<p>Hi,</p>\r\n"
                    + "<p>You recently requested a token for your account. Take the token below.</p>\r\n"
                    + "<p>Your token is: " + token + "</p>\r\n"
                    + "<p>Many Thanks!</p>\r\n"
                    + "\r\n"
                    + "</body>\r\n"
                    + "</html>", "text/html; charset=UTF-8");
            Transport.send(message);
            System.out.println("Notification sent successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    

}
