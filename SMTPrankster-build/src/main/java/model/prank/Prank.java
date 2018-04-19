package model.prank;

import com.sun.istack.internal.NotNull;
import model.mail.Mail;
import model.mail.Person;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Class that models a Prank
 *
 * A Prank is defined by its message to send, the sender and the receivers.
 * It generates a Mail to send for each receiver.
 *
 * @author Joël Kaufmann (Jokau), Loïc Schürch (loic-schurch)
 */
public class Prank {
    private Person sender;      // Sender
    private List<Person> receivers = new LinkedList<Person>();  // receivers
    private String message;     // content of the message to send

    /**
     * Prank constructor.
     * @param sender sender of the prank
     * @param receivers receivers of the prank
     * @param message text to send
     */
    public Prank(@NotNull Person sender, @NotNull List<Person> receivers, @NotNull String message) {
        this.message = message;
        this.sender = sender;
        this.receivers.addAll(receivers);
    }

    /**
     * Prank constructor.
     * @param sender sender of the prank
     * @param message text to send
     * @param receivers receivers of the prank
     */
    public Prank(@NotNull Person sender, @NotNull String message, @NotNull Person... receivers) {
        this.message = message;
        this.sender = sender;
        for (Person person: receivers) {
            this.receivers.add(person);
        }
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
     * @return a list that contains all the receivers
     */
    public List<Person> getReceivers() {
        return receivers;
    }

    /**
     * Create a mail for each receiver, as the Prank is addressed one by one.
     * The messages created are ready to be sent.
     *
     * @return a list of Mails ready to be sent
     */
    public List<Mail> createMessages() {
        ArrayList<Mail> mails = new ArrayList<Mail>();
        for (Person receiver : receivers) {
            mails.add(new Mail(sender, receiver, message));
        }
        return mails;
    }
}
