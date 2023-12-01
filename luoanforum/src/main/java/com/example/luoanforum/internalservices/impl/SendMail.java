package com.example.luoanforum.internalservices.impl;

import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * @author 落扶苏
 * @version 1.1
 */

@Service("sendMail")
public class SendMail implements Runnable {

    @Value("${senderFrom}")
    //发件人信息
    private String from;
    //发件人邮箱
    @Value("${senderEmail}")
    private String sender;
    //邮箱密码
    @Value("${16BitAuthorizationCode}")
    private String password;
    //邮件发送的服务器
    @Value("${mailServer}")
    private String host;

    private String recipient;
    private String title;
    private String content;

    public void signInMail(String recipient, String code) {
        this.recipient = recipient;
        this.title = "扶苏论坛邮箱登录验证码";
        this.content = "尊敬的用户你好！\r\n" +
                "\t您的验证码是：" + code + "， 请在5分钟内进行验证。如果该验证码不为您本人申请，请无视。";
        this.doRun();
    }

    public void registerMail(String recipient, String code) {
        this.recipient = recipient;
        this.title = "扶苏论坛邮箱注册验证码";
        this.content = "尊敬的用户你好！\r\n" +
                "\t您的验证码是：" + code + "， 请在5分钟内进行验证。如果该验证码不为您本人申请，请无视。";
        this.doRun();
    }

    public void rediscoverMail(String recipient, String code) {
        this.recipient = recipient;
        this.title = "扶苏论坛邮箱找回验证码";
        this.content = "尊敬的用户你好！\r\n" +
                "\t您的验证码是：" + code + "， 请在5分钟内进行验证。如果该验证码不为您本人申请，请无视。";
        this.doRun();
    }

    public void doSendOut(String recipient, String title, String content) {
        this.recipient = recipient;
        this.title = title;
        this.content = content;
        Thread thread = new Thread(this);
        thread.start();

        System.out.println(from);
        System.out.println(password);
        System.out.println(host);
    }

    private void doRun() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            Properties properties = new Properties();

            properties.setProperty("mail.host","smtp.qq.com");

            properties.setProperty("mail.transport.protocol","smtp");

            properties.setProperty("mail.smtp.auth","true");

            //QQ存在一个特性设置SSL加密
            MailSSLSocketFactory sf = null;
            try {
                sf = new MailSSLSocketFactory();
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);

            //创建一个session对象
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(sender,password);
                }
            });

            //开启debug模式
            session.setDebug(true);

            //获取连接对象
            Transport transport = null;
            try {
                transport = session.getTransport();
            } catch (NoSuchProviderException e) {
                e.printStackTrace();
            }

            //连接服务器
            transport.connect(host,from,password);


            //创建一个邮件发送对象
            MimeMessage mimeMessage = new MimeMessage(session);
            //邮件发送人
            mimeMessage.setFrom(new InternetAddress(sender));

            //邮件接收人
            mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(recipient));

            //邮件标题
            mimeMessage.setSubject(title);

            //邮件内容
            mimeMessage.setContent(content,"text/html;charset=UTF-8");

            //发送邮件
            transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());

            transport.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}


