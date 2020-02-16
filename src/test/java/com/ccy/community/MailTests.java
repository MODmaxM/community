package com.ccy.community;

import com.ccy.community.util.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;

@SpringBootTest
public class MailTests {

    @Resource
    private MailClient mailClient;

    @Resource
    private TemplateEngine templateEngine;

    @Test
    public void testTextEmail() {
        String to = "ccy19971024@gmail.com";
        String subject = "test";
        String content = "test!!!!!!!!!!!!!!!!";
        mailClient.sendMail(to, subject, content);
    }

    @Test
    public void testHtmlEmail() {
        // 给thymeleaf构造传递的参数
        Context context = new Context();
        context.setVariable("username", "ccy");

        String html = templateEngine.process("/mail/demo", context);
        System.out.println(html);
        mailClient.sendMail("ccy19971024@gmail.com", "HTML", html);
    }

}
