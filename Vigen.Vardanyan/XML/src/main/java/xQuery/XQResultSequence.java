package main.java.xQuery;

import java.sql.ResultSet;

/**
 * Created by Vigen.Vardanyan on 4/1/2016.
 */
public interface XQResultSequence extends ResultSet {

    boolean next();

    void close();

    String getItemAsString(String columnIndex);
}
