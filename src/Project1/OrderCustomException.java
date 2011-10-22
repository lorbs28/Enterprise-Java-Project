package Project1;


/**
 * Class:         OrderCustomException
 * Function/Duty: This class my simple custom exception class.  Instead of
 *                writing anything complex in this class, the constructor can
 *                can just be called and a custom message can be added as needed
 *                wherever the exception is being handled
 * @author Bryan Lor
 */
public class OrderCustomException extends Exception {

    public OrderCustomException(String message) {
        super(message);
    }
}
