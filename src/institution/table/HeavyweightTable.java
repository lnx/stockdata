package institution.table;

import institution.model.Heavyweight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.Log;
import db.DbConnection;

public class HeavyweightTable {

	public static void ignoreInsert(String tableName, List<Heavyweight> heavyweightList) {
		if (tableName != null && heavyweightList.size() > 0) {
			Connection conn = DbConnection.getConnection();
			if (conn != null) {
				try {
					String sql = "INSERT IGNORE INTO "
							+ tableName
							+ "(quarter, code, stock_name, inst_number, holding_number, a_shares_ratio, holding_increase, holding_ratio, pre_inst_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement ps = conn.prepareStatement(sql);
					for (Heavyweight obj : heavyweightList) {
						ps.setString(1, obj.getQuarter());
						ps.setString(2, obj.getCode());
						ps.setString(3, obj.getStockName());
						ps.setInt(4, obj.getInstNumber());
						ps.setDouble(5, obj.getHoldingNumber());
						ps.setDouble(6, obj.getASharesRatio());
						ps.setDouble(7, obj.getHoldingIncrease());
						ps.setDouble(8, obj.getHoldingRatio());
						ps.setInt(9, obj.getPreInstNumber());
						ps.addBatch();
					}
					ps.executeBatch();
					conn.commit();
					ps.close();
					Log.info(tableName + " insert ignore success");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static List<Heavyweight> getAll(String tableName) {
		List<Heavyweight> ret = new ArrayList<>();
		if (tableName != null) {
			Connection conn = DbConnection.getConnection();
			if (conn != null) {
				try {
					String sql = "SELECT * FROM " + tableName + " ORDER BY quarter DESC";
					Statement stat = conn.createStatement();
					ResultSet rs = stat.executeQuery(sql);
					while (rs.next()) {
						Heavyweight obj = new Heavyweight();
						obj.setQuarter(rs.getString("quarter"));
						obj.setCode(rs.getString("code"));
						obj.setStockName(rs.getString("stock_name"));
						obj.setInstNumber(rs.getInt("inst_number"));
						obj.setHoldingNumber(rs.getDouble("holding_number"));
						obj.setASharesRatio(rs.getDouble("a_shares_ratio"));
						obj.setHoldingIncrease(rs.getDouble("holding_increase"));
						obj.setHoldingRatio(rs.getDouble("holding_ratio"));
						obj.setPreInstNumber(rs.getInt("pre_inst_number"));
						ret.add(obj);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	}

}
