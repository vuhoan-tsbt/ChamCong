package com.example.chamcong.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Service
public class MailServiceImpl implements MailService{

    private final JavaMailSender javaMailSender;
    public MailServiceImpl(JavaMailSender javaMailSender){
        this.javaMailSender =  javaMailSender;
    }

    @Override
    public void sendMail(String text, String emailTo) throws MessagingException, UnsupportedEncodingException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("vuhoan485@gmail.com");
        message.setTo(emailTo);
        message.setSubject("Xin Chào! Để Thay Đổi Mật Khẩu Bạn Hãy Truy Cập Vào Đường Link Dưới Đây");
        message.setText(text);
        javaMailSender.send(message);
    }
}
