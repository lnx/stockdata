package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import util.Log;

public class DbConnection {

	static Connection conn;
	static final Object LOCK = new Object();

	public static Connection getConnection() {
		if (conn == null) {
			synchronized (LOCK) {
				if (conn == null) {
					try {
						Class.forName("com.mysql.jdbc.Driver");
						conn = DriverManager.getConnection(
								"jdbc:mysql://localhost:3306/stockforces?rewriteBatchedStatements=true&characterEncoding=UTF-8", "root", "");
						conn.setAutoCommit(false);
						Log.info("connect success");
					} catch (SQLException | ClassNotFoundException e) {
						Log.info("connect fail");
						e.printStackTrace();
					}
				}
			}
		}
		return conn;
	}

}
