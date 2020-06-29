package dao.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private ConnectionPool() {

    }

    private static ConnectionPool instance = null;
    public static ConnectionPool getInstance() {
        if (instance == null) {
            return new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() {
        Context ctx;
        Connection c = null;
        try {
            ctx = new InitialContext();
//            System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
//                    "org.apache.naming.java.javaURLContextFactory");
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mydatabase");
            c = ds.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
}
