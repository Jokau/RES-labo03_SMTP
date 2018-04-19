import model.mail.Mail;
import model.prank.Prank;
import model.prank.PrankGenerator;
import smtp.SmtpClient;

import java.util.ArrayList;

public class SMTPrankster {
    public void main(String[] args) {
        SmtpClient client = new SmtpClient();

        PrankGenerator generator = new PrankGenerator();

        ArrayList<Prank> pranks = generator.getPranks();

        for(Prank p: pranks) {
            for(Mail mail: p.getMails()) {
                client.sendMail(mail);
            }
        }

    }

}
