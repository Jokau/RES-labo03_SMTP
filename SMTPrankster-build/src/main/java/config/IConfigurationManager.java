package config;

import model.mail.Mail;
import model.mail.Person;

import java.util.ArrayList;

/**
 * Provides the required functionalities for a configuration manager.
 */
public interface IConfigurationManager {

    public ArrayList<Person> getVictims();

    public ArrayList<String> getMessages();
}
