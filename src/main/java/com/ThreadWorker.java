package com;

import javax.mail.*;
import javax.mail.event.FolderListener;
import javax.mail.event.MessageCountEvent;
import javax.mail.event.MessageCountListener;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.Properties;

/**
 * Created by Dominic on 25-Oct-15.
 */
public class ThreadWorker implements Runnable {

    private static final long THIRTY_SECONDS = 30000;
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

            Properties properties = new Properties();

            properties.put("mail.pop3s.host", host);
            properties.put("mail.pop3s.port", "995");
            properties.put("mail.pop3s.starttls.enable", "true");

            Session emailSession = Session.getInstance(properties,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(
                                    user, password);
                        }
                    });
            // emailSession.setDebug(true);

            store = emailSession.getStore("pop3s");
            store.connect();

            emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

//            Message[] messages = emailFolder.getMessages();
//            printMessages(messages);

            while (true) {
                System.out.println(emailFolder.getNewMessageCount());
                if (emailFolder.hasNewMessages()){
                    System.out.print(emailFolder.getNewMessageCount());
                    playSound();
                }
                emailFolder.close(false);
                Thread.sleep(THIRTY_SECONDS);
            }
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

    public void playSound(){
        try {
            AudioInputStream audioInputStream;
            audioInputStream = AudioSystem.getAudioInputStream(ThreadWorker.class.getResource("sounds/blip.wav"));
            final Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void printMessages(Message[] message){
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
    }
}
