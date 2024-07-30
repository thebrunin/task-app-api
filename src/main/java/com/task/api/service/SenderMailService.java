package com.task.api.service;

import com.task.api.model.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SenderMailService {

    @Autowired
    private JavaMailSender mailSender;

    private void send(String sendTo, String subject, String message) throws MessagingException{
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(sendTo);
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);
    }

    public void sendWithAttachment(String sendTo, String subject, String message) throws MessagingException, IOException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(sendTo);
        helper.setSubject(subject);
        helper.setText(message);

        String attachmentPath = "static/img.JPG";
        Resource resource = new ClassPathResource(attachmentPath);

        if (resource.exists()) {
            String fileName = resource.getFilename();
            FileSystemResource file = new FileSystemResource(resource.getFile());
            helper.addAttachment(fileName, file);
        } else {
            //todo else handler
        }

        mailSender.send(mimeMessage);
    }

    public void sendCreateAccountEmail(User user) {
        try {
            String subject = "Create account";
            String message = "Hello, thank u for choosing us";
            send(user.getEmail(), subject, message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}