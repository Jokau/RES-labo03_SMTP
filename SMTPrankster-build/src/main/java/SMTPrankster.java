import config.ConfigurationManager;
import exception.NotEnoughVictimsException;
import model.mail.Mail;
import model.prank.Prank;
import model.prank.PrankGenerator;
import smtp.SmtpClient;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Program entry Class
 */
public class SMTPrankster {

    public static void main(String[] args) {

        ConfigurationManager manager = ConfigurationManager.getConfigManager();
        PrankGenerator generator = null;
        try{
             generator = new PrankGenerator();
        } catch(NotEnoughVictimsException e) {
            System.err.println(e.getMessage());
            System.err.println("Please edit config/victims.utf8 or config/config.properties "
                    + "to reach minimum of victims per group");
            System.exit(1);
        }

        ArrayList<Prank> pranks = generator.getPranks();

        SmtpClient client = null;
        try {
            client = new SmtpClient(manager.getSmtpServerAddress(), manager.getSmtpPortNumber());

            for(Prank p: pranks) {
                for(Mail mail: p.getMails()) {
                    client.sendMail(mail);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }
}
