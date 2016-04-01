package main.java.xQuery;

/**
 * Created by Vigen.Vardanyan on 4/1/2016.
 */
public class SaxonXQDataSource implements XQDataSource {
    protected XQDataSource inner;

    @Override
    public XQConnection getConnection() throws XQException {
        return this.inner.getConnection();
    }
}
