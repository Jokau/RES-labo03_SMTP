import config.ConfigurationManager;
import exception.NotEnoughVictimsException;
import model.mail.Mail;
import model.prank.Prank;
import model.prank.PrankGenerator;
import smtp.SmtpClient;

import java.io.IOException;
import java.util.ArrayList;

public class SMTPrankster {
    public static void main(String[] args) {

        ConfigurationManager manager = ConfigurationManager.getConfigManager();
        PrankGenerator generator = null;
        try{
             generator = new PrankGenerator();
        } catch(NotEnoughVictimsException e) {
            System.out.println("AH");
        }

        ArrayList<Prank> pranks = generator.getPranks();

        try {
            SmtpClient client = new SmtpClient(manager.getSmtpServerAddress(), manager.getSmtpPortNumber());

            for(Prank p: pranks) {
                for(Mail mail: p.getMails()) {
                    client.sendMail(mail);
                }
            }
            //Mail m = new Mail(new Person("sender@sender.com"), new Person("victim@victim.com"), "mail message");
            //client.sendMail(m);

            client.close();

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
