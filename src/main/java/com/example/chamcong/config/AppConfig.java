//package com.example.chamcong.config;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.ui.Model;
//
//import java.util.Collection;
//import java.util.Map;
//import java.util.Properties;
//
//@Configuration
//public class AppConfig {
//    @Bean
//    public JavaMailSender javaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smtp.gmail.com");
//        mailSender.setPort(587);
//        mailSender.setUsername("vuhoan485@gmail.com");
//        mailSender.setPassword("uccucseuoncvifis");
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.debug", "true");
//        return mailSender;
//    }
//
//    @Bean
//    Model model() {
//
//        return new Model() {
//            @Override
//            public Model addAttribute(String attributeName, Object attributeValue) {
//                return null;
//            }
//
//            @Override
//            public Model addAttribute(Object attributeValue) {
//                return null;
//            }
//
//            @Override
//            public Model addAllAttributes(Collection<?> attributeValues) {
//                return null;
//            }
//
//            @Override
//            public Model addAllAttributes(Map<String, ?> attributes) {
//                return null;
//            }
//
//            @Override
//            public Model mergeAttributes(Map<String, ?> attributes) {
//                return null;
//            }
//
//            @Override
//            public boolean containsAttribute(String attributeName) {
//                return false;
//            }
//
//            @Override
//            public Object getAttribute(String attributeName) {
//                return null;
//            }
//
//            @Override
//            public Map<String, Object> asMap() {
//                return null;
//            }
//        };
//    }
//
//    @Bean
//    ModelMapper modelMapper() {
//        return new ModelMapper();
//    }
//
//}
