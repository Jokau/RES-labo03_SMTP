/**
 * Loads all the properties, availaible victims' e-mail address and availaible messages from configurations files.
 *
 * @author Joël Kaufmann (Jokau), Loïc Schürch (loic-schurch)
 */

package config;

import model.mail.Message;
import model.mail.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class ConfigurationManager implements  IConfigurationManager{

    private final static String PROP_FILE = "config.properties";
    private final static String PROP_SERVER_ADDRESS = "smtpServerAddress";
    private final static String PROP_PORT_NUMBER = "smtpPortNumber";
    private final static String PROP_NB_GROUPS = "numberOfGroups";
    private final static String PROP_MIN_GROUP_SIZE = "minimumGroupSize";
    private final static String VICTIMS_FILE = "victims.utf8";
    private final static String MESSAGES_FILE = "messages.utf8";

    //The properties to load
    private String smtpServerAddress;
    private int smtpPortNumber;
    private int numberOfGroups;
    private int minimumGroupSize;

    //The list of availaible victims
    private ArrayList<Person> victims;

    //The list of availaible messages to send
    private ArrayList<Message> messages;

    public ConfigurationManager() {
        loadVictims(PROP_FILE);
        loadMessages(VICTIMS_FILE);
        loadProperties(MESSAGES_FILE);
    }

    /**
     * Loads the properties found in the file provided
     * @param filename the file to load containing the properties
     */
    private void loadProperties(String filename) {

        Properties properties = new Properties();
        InputStream input = null;

        try{
            input = new FileInputStream(filename);
            properties.load(input);

            smtpServerAddress = properties.getProperty(PROP_SERVER_ADDRESS);
            smtpPortNumber    = Integer.parseInt(properties.getProperty(PROP_PORT_NUMBER));
            numberOfGroups    = Integer.parseInt(properties.getProperty(PROP_NB_GROUPS));
            minimumGroupSize  = Integer.parseInt(properties.getProperty(PROP_MIN_GROUP_SIZE));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(input != null) {
                try{
                    input.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void loadVictims(String filename) {
        victims = new ArrayList<Person>();
        FileInputStream fis = null;
        try {
            fis =  new FileInputStream(filename);
            BufferedReader is = new BufferedReader(new InputStreamReader(fis, "UTF-8"));

            String victimRead;
            while( (victimRead = is.readLine()) != null) {
                victims.add(new Person(victimRead));
            }
            is.close();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if(fis != null) {
                try {
                    fis.close();
                } catch( IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void loadMessages(String filename) {
        messages = new ArrayList<Message>();
        FileInputStream fis = null;
        try {
            fis =  new FileInputStream(filename);

            //TODO: lire tous les messages qui sont séparé par un separateur, récupérer les subect, cc, etc...

        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public ArrayList<Person> getVictims() {
        return victims;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

}
