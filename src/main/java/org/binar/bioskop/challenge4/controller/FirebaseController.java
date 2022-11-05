package org.binar.bioskop.challenge4.controller;

import com.google.firebase.FirebaseException;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.binar.bioskop.challenge4.respon.Respon;
import org.binar.bioskop.challenge4.service.firebase.FirebaseMessagingService;
import org.binar.bioskop.challenge4.service.firebase.model.FirebaseNote;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Controller
public class FirebaseController {

    private final FirebaseMessagingService firebaseService;

    public FirebaseController(FirebaseMessagingService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @RequestMapping("/send-notification")
    @ResponseBody
    public String sendNotification(@RequestBody FirebaseNote note,
                                   @RequestParam String token) throws FirebaseMessagingException {

        Respon ress = new Respon();
        ress.setResponCode("200");
        ress.setMessage("succes");
        ress.setData(firebaseService.sendNotification(note, token));
        return null;
    }

    @PostMapping("/send-notif")
    public String sendNotif(@RequestParam String registrationToken) throws FirebaseException {
        Respon ress = new Respon();
        ress.setResponCode("200");
        ress.setMessage("succes");
        ress.setData(firebaseService.sendNotif(registrationToken));
        return null;
    }

}
