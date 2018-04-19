package smtp;

import model.mail.Mail;

import java.io.IOException;

/**
 * Provides the required functionalities for a SMTP client
 */
public interface ISmtpClient {
    public void sendMail(Mail mail) throws IOException;
}
