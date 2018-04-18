package model.mail;

import com.sun.istack.internal.NotNull;

/**
 * Person Model
 *
 * A Person is defined by their e-mail address.
 * A Person can be a sender victim or a receiver victim, no distinction is operated.
 *
 * A sender is the one whose e-mail address is used as the sender in the prank e-mail
 * and a receiver if someone that will receive the prank e-mail.
 *
 * @author Joël Kaufmann (Jokau), Loïc Schürch (loic-schurch)
 */
public class Person {
    private String emailAddress;    //e-mail address

    public Person(@NotNull String emailAddress) throws IllegalArgumentException {
        String pattern = "\\w+@\\w+\\.\\w+";
        if (emailAddress.matches(pattern)) {
            this.emailAddress = emailAddress;
        } else {
            throw  new IllegalArgumentException("valid address required");
        }
    }

    /**
     * Getter of the e-mail address
     * @return the Person's e-mail address
     */
    public String getEmailAddress() {
        return emailAddress;
    }
}
