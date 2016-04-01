package main.java.xQuery;

/**
 * Created by Vigen.Vardanyan on 4/1/2016.
 */
public class XQException extends Exception {

    private String vendorCode;
    private XQException nextException;

    public XQException(String message) {
        super(message);
    }

    public XQException(String message, String vendorCode) {
        super(message);
        this.vendorCode = vendorCode;
    }

    XQException getNextException() {
        return nextException;
    }

    java.lang.String getVendorCode() {
        return vendorCode;
    }

    void setNextException(XQException next) {
        nextException = next;
    }
}
