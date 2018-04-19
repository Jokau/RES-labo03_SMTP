package model.mail;

import com.sun.istack.internal.NotNull;
import exception.NotEnoughVictimsException;

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

    public static int MIN_NB_OF_VICTIMS = 3;

    private Person sender;                                      // Person that is the sender of the prank
    private List<Person> receivers = new ArrayList<Person>();   // List of all receivers

    /**
     * Constructor of a Group.
     * @param sender the Person whose e-mail address is used as sender
     * @param receivers the Persons whose e-mail addresses are used as receivers
     */
    public Group(@NotNull Person sender,@NotNull List<Person> receivers) throws NotEnoughVictimsException {
        if (receivers.size() < MIN_NB_OF_VICTIMS - 1) {
            throw new NotEnoughVictimsException("Each Group should contain at least "
                    + MIN_NB_OF_VICTIMS + " victims, found : " +  receivers.size());
        }
        this.sender = sender;
        this.receivers.addAll(receivers);
    }

    /**
     * Getter of the sender.
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
