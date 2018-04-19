package exception;

/**
 * Exception used in Group to ensure that the group has enough
 * victims to play the prank.
 */
public class NotEnoughVictimsException extends Exception {
    public NotEnoughVictimsException(String message) {
        super(message);
    }
}
