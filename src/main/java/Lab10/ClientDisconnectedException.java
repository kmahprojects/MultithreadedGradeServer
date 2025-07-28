package Lab10;
/**
 * ClientDisconnectedException is a class meant to be used to display a disconnect message
 * @author: Kevin Mah
 *  */
public class ClientDisconnectedException extends Exception {
    /**
     * ClientDisconnectedException uses the exception parent to output an error message
     * @param message: a string
     * @return: nothing
     *  */
    public ClientDisconnectedException(String message) {
        super(message);
    }
}

