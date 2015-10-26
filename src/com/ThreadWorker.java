package com;

import javax.mail.*;
import java.util.Properties;

/**
 * Created by Dominic on 25-Oct-15.
 */
public class ThreadWorker implements Runnable {

    private static final long THIRTYSECONDS = 30000;
    private String host;
    private String storeType;
    private String user;
    private String password;

    public ThreadWorker(String host, String storeType, final String user,
                             final String password) {
        this.host = host;
        this.user = user;
        this.password = password;
    }



    @Override
    public void run() {
        Folder emailFolder = null;
        Store store = null;
        try {

            // create properties field
            Properties properties = new Properties();

            properties.put("mail.pop3s.host", host);
            properties.put("mail.pop3s.port", "995");
            properties.put("mail.pop3s.starttls.enable", "true");

            // Setup authentication, get session
            Session emailSession = Session.getInstance(properties,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(
                                    user, password);
                        }
                    });
            // emailSession.setDebug(true);

            // create the POP3 store object and connect with the pop server
            store = emailSession.getStore("pop3s");

            store.connect();

            // create the folder object and open it
            emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            // retrieve the messages from the folder in an array and print it
            //Message[] messages = emailFolder.getMessages();

            while (true) {
                System.out.println(emailFolder.getNewMessageCount());
                Thread.sleep(THIRTYSECONDS);
            }

//            System.out.println("messages.length---" + messages.length);
//
//            for (int i = 0, n = messages.length; i < n; i++) {
//                Message message = messages[i];
//                System.out.println("---------------------------------");
//                System.out.println("Email Number " + (i + 1));
//                System.out.println("Subject: " + message.getSubject());
//                System.out.println("From: " + message.getFrom()[0]);
//                System.out.println("Text: " + message.getContent().toString());
//            }

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                emailFolder.close(false);
                store.close();
            } catch (MessagingException e) {
                e.printStackTrace();
            }

        }
    }
}
