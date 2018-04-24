package net.boddin.clouddemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@RestController()
@RequestMapping("mail")
public class MailController {

    private final JavaMailSender mailSender;

    @Autowired
    public MailController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @RequestMapping("/send")
    @ResponseBody
    public void sendMail() throws MessagingException {
        MimeMessage msg = mailSender.createMimeMessage();
        msg.addFrom(new Address[]{new InternetAddress("mike@autorisiert.net")});
        msg.addRecipients(Message.RecipientType.TO, "mike.boddin@gmail.com");
        msg.setText("Hallo Mike");
        mailSender.send(msg);
    }
}
