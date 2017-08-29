package Exceptions;

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
