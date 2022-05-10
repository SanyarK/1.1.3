package jm.task.core.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static Connection conn = null;
    private static Util instance = null;

    private Util() {
        try {
            if (null == conn || conn.isClosed()) {
                Properties props = getProps();
                conn = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.username"), props.getProperty("db.password"));
            }
        } catch (IOException | SQLException var2) {
            var2.printStackTrace();
        }

    }

    public static Util getInstance() {
        if (null == instance) {
            instance = new Util();
        }

        return instance;
    }

    public static Connection getConnection() {
        return conn;
    }

    private static Properties getProps() throws IOException {
        Properties props = new Properties();

        try {
            InputStream in = Files.newInputStream(Paths.get(Util.class.getResource("/database.properties").toURI()));

            Properties var2;
            try {
                props.load(in);
                var2 = props;
            } catch (Throwable var5) {
                if (in != null) {
                    try {
                        in.close();
                    } catch (Throwable var4) {
                        var5.addSuppressed(var4);
                    }
                }

                throw var5;
            }

            if (in != null) {
                in.close();
            }

            return var2;
        } catch (URISyntaxException | IOException var6) {
            throw new IOException("Database config file not found", var6);
        }
    }
}
