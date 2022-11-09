package org.binar.bioskop.challenge4.service.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.extern.slf4j.Slf4j;
import org.binar.bioskop.challenge4.service.firebase.model.FirebaseNote;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

@Service
@Slf4j
public class FirebaseMessagingService {

    private final FirebaseMessaging firebaseMessaging;

    public FirebaseMessagingService(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }


    public String sendNotification(FirebaseNote note, String topic) throws FirebaseMessagingException {
        Notification notification = Notification
                .builder()
                .setTitle(note.getSubject())
                .setBody(note.getContent())
                .setImage(note.getImage())
                .build();

        Message message = Message
                .builder()
//                .setToken(topic)
                .setTopic(topic)
                .setNotification(notification)
                .putAllData(note.getData())
                .build();
        log.info(firebaseMessaging.send(message));
        log.info("Send Firebase Messaging Success");
        return firebaseMessaging.send(message);
    }

    public String sendNotif(String token) throws FirebaseMessagingException {
        // this registration token comes from the client FCM SDKs.
        String registrationToken = token;

        // see documentation on defining a message payload.
        Message message = Message.builder()
                .putData("score", "850")
                .putData("time", "2:45")
                .setToken(registrationToken)
                .setTopic("test")
                .build();

        // Send a message to the device corresponding to the provided
        // registration token.
        String response =  firebaseMessaging.send(message);
        // Response is a messaging ID String.
        log.info("Successfully send message: " +response);

        return response;
    }


}
