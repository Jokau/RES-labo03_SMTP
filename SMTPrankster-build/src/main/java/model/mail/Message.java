package model.mail;

import com.sun.istack.internal.NotNull;

/**
 * Message Model
 *
 * A Message is a full SMTP DATA String (e-mail body), with sender, receivers,
 * subject and text.
 * It contains parsable SMTP DATA flags (as From:, To:, ...), but no SMTP command.
 *
 * @author Joël Kaufmann (Jokau), Loïc Schürch (loic-schurch)
 */
public class Message {
    private Group group;    // group used in the message
    private String subject; // subject of the message
    private String content; // full text content of the message

    /**
     * Constructor of Message.
     * @param group group used in the Message
     * @param subject subject of the Message
     */
    public Message(@NotNull Group group, @NotNull String subject) {
        this.group = group;
        this.subject = subject;
        generateHeader();
    }

    /**
     * Generate SMTP DATA header
     */
    private void generateHeader() {
        StringBuilder strBuilder = new StringBuilder();

        // from
        strBuilder.append("From: ");
        strBuilder.append(group.getSender().getEmailAddress() + "\n");
        // to
        for (Person receiver : group.getReceivers()) {
            strBuilder.append("To: ");
            strBuilder.append(receiver.getEmailAddress() + "\n");
        }
        // subject
        strBuilder.append(subject);
        content = strBuilder.toString();

        //TODO CC if defined in properties

        //TODO see if necessary to have group and subject as class attr
    }
}
