package helper.akka;

/**
 * Created by yulinguo on 1/22/15.
 * This class is the message sent between akka actors
 * business : what kind of message
 * data : message body
 */
public class DispatchMsg {

    public String business;
    public Object data;

    public DispatchMsg(String business, Object data) {
        this.business = business;
        this.data = data;
    }
}
