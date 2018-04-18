package model.mail;

import java.util.ArrayList;
import java.util.List;

/**
 * Group Model
 *
 * A Group is a group of Persons.
 *
 * The number n of Persons in a group is define in /config/config.properties.
 * Within a group, one person is going to be the sender and the other are the receivers.
 *
 * @author Joël Kaufmann (Jokau), Loïc Schürch (loic-schurch)
 */
public class Group {

    private Person sender;                                      // Person that is the sender of the prank
    private List<Person> receivers = new ArrayList<Person>();   // List of all receivers


    /**
     * Constructor of a Group.
     * @param sender the Person whose e-mail address is used as sender
     * @param receivers the Persons whose e-mail addresses are used as receivers
     */
    public Group(Person sender, List<Person> receivers) {
        this.sender = sender;
        this.receivers.addAll(receivers);
    }

    /**
     * Constructor of a Group.
     * @param sender the Person whose e-mail address is used as sender
     * @param receivers the Persons whose e-mail addresses are used as receivers
     */
    public Group(Person sender, Person... receivers) {
        this.sender = sender;
        for (Person receiver : receivers) {
            this.receivers.add(receiver);
        }
    }

    /**
     * Getter of sender.
     * @return the sender
     */
    public Person getSender() {
        return sender;
    }

    /**
     * Getter of the receivers.
     * @return the receivers
     */
    public List<Person> getReceivers() {
        return receivers;
    }
}
