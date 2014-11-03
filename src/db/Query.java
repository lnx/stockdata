package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class Query {

	public static String getLastQuarter() {
		String ret = "";
		Connection conn = DbConnection.getConnection();
		if (conn != null) {
			try {
				String sql = "SELECT MAX(quarter) FROM qfii";
				Statement stat = conn.createStatement();
				ResultSet rs = stat.executeQuery(sql);
				if (rs.next()) {
					ret = rs.getString(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	public static Set<String> getCodes(String tableName, String quarter) {
		Set<String> ret = new HashSet<>();
		if (tableName != null && quarter != null) {
			Connection conn = DbConnection.getConnection();
			if (conn != null) {
				try {
					String sql = "SELECT CODE FROM " + tableName + " WHERE quarter = '" + quarter + "'";
					Statement stat = conn.createStatement();
					ResultSet rs = stat.executeQuery(sql);
					while (rs.next()) {
						ret.add(rs.getString(1));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	}

}
