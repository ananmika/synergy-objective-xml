package main.java.xQuery;

/**
 * Created by Vigen.Vardanyan on 4/1/2016.
 */
public interface XQDataSource {

    XQConnection getConnection() throws XQException;
}
