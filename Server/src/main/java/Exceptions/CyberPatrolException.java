package Exceptions;

/**
 * Created by User on 8/28/2017.
 */
public class CyberPatrolException extends Exception {
    public CyberPatrolException(String desc, Throwable cause) {
        super(desc, cause);
    }

    public CyberPatrolException(Throwable cause) {
        super(cause);
    }

    public CyberPatrolException(String desc) {
        super(desc);
    }
}
