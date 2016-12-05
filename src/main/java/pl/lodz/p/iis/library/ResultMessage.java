package pl.lodz.p.iis.library;

/**
 * Created by Jakub on 2016-12-05.
 */
public class ResultMessage {
    String message;

    public ResultMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
