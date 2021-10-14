package ex.service;

public interface EmailSenderService {
    void sendMail(String to, String subject, String body);
}
