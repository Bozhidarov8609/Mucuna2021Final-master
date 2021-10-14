package ex.service.impl;

import ex.service.EmailSenderService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {



    private final JavaMailSender mailSender;
    private final SimpleMailMessage preConfiguredMessage;

    public EmailSenderServiceImpl(JavaMailSender mailSender, SimpleMailMessage preConfiguredMessage) {
        this.mailSender = mailSender;
        this.preConfiguredMessage = preConfiguredMessage;
    }


    public void sendMail(String to, String subject, String body)
    {


        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mucunateam@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
