package com.routemax.teamroutemax.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendParcelNotification(String toEmail, String description, String trackingId, String senderName, String senderAddress,
                                   String receiverName, String receiverAddress) {
        String subject = "Your Order is in Transit";
        // String body = "Your order (" + description + ") is in transit.\n\n"
        //             + "Here is your tracking ID: " + trackingId + "\n\n"
        //             + "You can use this ID to track your parcel on our site.";
        String body = "Hi " + receiverName + ",\n\n"
            + "Your parcel has been successfully dispatched! 📦\n\n"
            + "📝 Parcel Details:\n"
            + "• Description: " + description + "\n"
            + "• Tracking ID: " + trackingId + "\n\n"
            + "📮 Sender:\n"
            + senderName + "\n"
            + senderAddress + "\n\n"
            + "📬 Recipient:\n"
            + receiverName + "\n"
            + receiverAddress + "\n\n"
            + "🕒 You can track your parcel using the above tracking ID.\n\n"
            + "Thank you for choosing RouteMax!\n\n"
            + "Warm regards,\n"
            + "RouteMax Team";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}