package main.java.xQuery;

import java.io.InputStream;

/**
 * Created by Vigen.Vardanyan on 4/1/2016.
 */
public interface XQConnection {

    XQPreparedExpression prepareExpression(InputStream stream) throws XQException;
}
