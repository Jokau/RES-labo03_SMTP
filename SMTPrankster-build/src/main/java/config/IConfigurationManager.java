package config;

import model.mail.Message;
import model.mail.Person;

import java.util.ArrayList;

/**
 * Interface permettant d'obtenir les messages ainsi que les victimes disponible
 */
public interface IConfigurationManager {

    public ArrayList<Person> getVictims();

    public ArrayList<Message> getMessages();
}
