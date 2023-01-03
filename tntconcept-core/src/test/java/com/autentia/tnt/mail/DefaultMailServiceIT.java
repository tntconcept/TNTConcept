package com.autentia.tnt.mail;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.junit.Ignore;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

public class DefaultMailServiceIT{

    static final public GenericContainer<?> smptServer;

    static{
        smptServer = new GenericContainer<>(
                DockerImageName.parse("reachfive/fake-smtp-server:latest"))
                .withExposedPorts(1025, 1080).waitingFor(Wait.forHttp("/").forPort(1080));

        smptServer.start();
    }

    @Test
    public void sendTest() throws MessagingException{
        DefaultMailService defaultMailService = new DefaultMailService(true, smptServer.getHost(),
                String.valueOf(smptServer.getFirstMappedPort()),
                "fjmpaez@autentia.com", "nopassword", "false", "false", "true");

        defaultMailService.send("fjmpaez@autentia.com", "test", "text");
    }

    @Test
    public void sendMultipleOutputStreams() throws MessagingException{
        DefaultMailService defaultMailService = new DefaultMailService(true, smptServer.getHost(),
                String.valueOf(smptServer.getFirstMappedPort()),
                "fjmpaez@autentia.com", "nopassword", "false", "false", "true");

        defaultMailService.sendOutputStreams(new String[]{"fjmpaez@autentia.com", "desktop.support@autentia.com"},
                "test", "text",
                Map.of(new ByteArrayInputStream(new byte[]{}), "fileName"));
    }

    @Test
    public void sendOutputStreams() throws MessagingException{
        DefaultMailService defaultMailService = new DefaultMailService(true, smptServer.getHost(),
                String.valueOf(smptServer.getFirstMappedPort()),
                "fjmpaez@autentia.com", "nopassword", "false", "false", "true");

        defaultMailService.sendOutputStreams("fjmpaez@autentia.com", "test", "text",
                Map.of(new ByteArrayInputStream(new byte[]{}), "fileName"));
    }

    @Test
    public void sendFiles() throws MessagingException{
        DefaultMailService defaultMailService = new DefaultMailService(true, smptServer.getHost(),
                String.valueOf(smptServer.getFirstMappedPort()),
                "fjmpaez@autentia.com", "nopassword", "false", "false", "true");

        File attachment = new File(getClass().getClassLoader().getResource("mail/attachment.txt").getFile());

        defaultMailService.sendFiles("fjmpaez@autentia.com", "test", "text", List.of(attachment));
    }

    @Test
    public void sendHtmlTest() throws MessagingException{
        DefaultMailService defaultMailService = new DefaultMailService(true, smptServer.getHost(),
                String.valueOf(smptServer.getFirstMappedPort()),
                "fjmpaez@autentia.com", "nopassword", "false", "false", "true");

        defaultMailService.sendHtml("fjmpaez@autentia.com", "test", "<html><body><b>hello</b></body></html>");
    }

    @Test
    @Ignore("Use this test to check gmail tls connection")
    /**
     * Create an application password here: https://myaccount.google.com/security for your account in order to use this test
     */
    public void sendTLSOnGmailTest() throws MessagingException{
        DefaultMailService defaultMailService = new DefaultMailService(true, "smtp.gmail.com", "465",
                "oneuser@autentia.com", "one_application_password", "true", "true", "true");

        defaultMailService.send("oneuser@autentia.com", "test", "text");
    }

}