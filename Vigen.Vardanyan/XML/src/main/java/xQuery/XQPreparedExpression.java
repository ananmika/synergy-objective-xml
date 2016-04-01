package main.java.xQuery;

import java.sql.PreparedStatement;

/**
 * Created by Vigen.Vardanyan on 4/1/2016.
 */
public interface XQPreparedExpression extends PreparedStatement {

    XQResultSequence executeQuery();
}
