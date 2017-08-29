package Exceptions;

public class CommunicationException extends CyberPatrolException{
    public CommunicationException(String desc, Throwable cause) {
        super(desc, cause);
    }

    public CommunicationException(Throwable cause) {
        super(cause);
    }

    public CommunicationException(String desc) {
        super(desc);
    }
}
