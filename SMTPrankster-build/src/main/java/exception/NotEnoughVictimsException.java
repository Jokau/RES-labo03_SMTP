package exception;

/**
 * Exception used in Group to ensure that the group is large enough
 */
public class NotEnoughVictimsException extends Exception {
    public NotEnoughVictimsException(String message) {
        super(message);
    }
}
