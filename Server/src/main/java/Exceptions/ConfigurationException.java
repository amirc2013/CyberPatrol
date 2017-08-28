package Exceptions;

/**
 * Created by User on 8/28/2017.
 */
public class ConfigurationException extends CyberPatrolException{
    public ConfigurationException(String desc, Throwable cause) {
        super(desc, cause);
    }

    public ConfigurationException(Throwable cause) {
        super(cause);
    }

    public ConfigurationException(String desc) {
        super(desc);
    }
}
