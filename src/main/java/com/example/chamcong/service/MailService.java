package com.example.chamcong.service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface MailService {

    void sendMail(String link, String emailTo) throws MessagingException, UnsupportedEncodingException;
}
