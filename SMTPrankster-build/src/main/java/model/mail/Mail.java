package model.mail;

import com.sun.istack.internal.NotNull;

import java.util.Base64;
import java.util.Scanner;

/**
 * Mail Model
 *
 * A Mail is a full SMTP DATA String (e-mail body), with a sender, a receiver,
 * the subject and the text.
 * It contains parsable SMTP DATA flags (as From:, To:, ...), but no SMTP command.
 *
 * @author Joël Kaufmann (Jokau), Loïc Schürch (loic-schurch)
 */
public class Mail {
    private Person sender;  // sender
    private Person receiver;// receiver
    private String content; // full DATA content of the mail

    /**
     * Constructor of Mail.
     * @param sender group used in the Mail
     * @param receiver subject of the Mail
     * @param message the Mail text body
     */
    public Mail(@NotNull Person sender, @NotNull Person receiver, @NotNull String message) {
        this.sender = sender;
        this.receiver = receiver;
        generateHeader(message);
    }

    /**
     * Getter of the mail content (full body)
     * @return the Mail's content
     */
    public String getContent() {
        return content;
    }

    /**
     *  Getter of the receiver
     * @return the Mail's receiver
     */
    public Person getReceiver() {
        return receiver;
    }

    /**
     * Getter of the elected sender
     * @return the Mail's sender
     */
    public Person getSender() {
        return sender;
    }

    /**
     * Generate SMTP DATA header
     */
    private void generateHeader(String message) {
        StringBuilder strBuilder = new StringBuilder();
        // from
        strBuilder.append("From: ");
        strBuilder.append(sender.getEmailAddress() + "\r\n" + "Content-Type: text/plain; charset=utf-8" + "\r\n");
        // to
        strBuilder.append("To: ");
        strBuilder.append(receiver.getEmailAddress() + "\r\n");
        // subject and body

        Scanner scanner = new Scanner(message);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String pattern = "^Subject:.*";
            if(line.matches(pattern)) {
                line = "Subject: =?utf-8?B?" + Base64.getEncoder().encodeToString(
                        line.replace("Subject: ", "").getBytes()) + "?=";
            }
            strBuilder.append(line);
            if(scanner.hasNextLine()) {
                strBuilder.append("\r\n");
            }
        }
        scanner.close();
        content = strBuilder.toString();
    }
}
