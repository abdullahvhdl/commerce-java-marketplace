package com.respodo.commerce.service.config;

import java.util.Properties;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource("classpath:application.properties")
public class MailConfiguration{


    private final Logger log = LoggerFactory.getLogger(MailConfiguration.class);
    
    private static final String PROP_SMTP_AUTH = "mail.smtp.auth";
    private static final String PROP_STARTTLS = "mail.smtp.starttls.enable";
    private static final String PROP_TRANSPORT_PROTO = "mail.transport.protocol";

    @Resource
    private Environment env;

    @Bean
    public JavaMailSenderImpl javaMailSender() {
        log.debug("Configuring mail server");
        String host = env.getRequiredProperty("mail.server.host");
        int port = env.getRequiredProperty("mail.server.port",Integer.class);
        String user = env.getRequiredProperty("mail.server.username");
        String password = env.getRequiredProperty("mail.server.password");
        String protocol = env.getRequiredProperty("mail.server.protocol");
        Boolean tls = env.getRequiredProperty("mail.server.tls",Boolean.class);
        Boolean ssl = env.getRequiredProperty("mail.server.ssl",Boolean.class);

        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(host);
        sender.setPort(port);
        sender.setUsername(user);
        sender.setPassword(password);
        
        Properties sendProperties = new Properties();
        sendProperties.setProperty(PROP_SMTP_AUTH, ssl.toString());
        sendProperties.setProperty(PROP_STARTTLS, tls.toString());
        sendProperties.setProperty(PROP_TRANSPORT_PROTO, protocol);
        sender.setJavaMailProperties(sendProperties);
        return sender;
    }
}