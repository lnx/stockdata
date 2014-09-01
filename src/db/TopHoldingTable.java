package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.TopHolding;
import util.Log;

public class TopHoldingTable {

	public static void ignoreInsert(String tableName, List<TopHolding> topHoldings) {
		if (tableName != null && topHoldings.size() > 0) {
			Connection conn = DbConnection.getConnection();
			if (conn != null) {
				try {
					PreparedStatement ps = conn
							.prepareStatement("insert ignore into "
									+ tableName
									+ "(quarter, code, stock_name, inst_number, holding_number, a_shares_ratio, holding_increase, holding_ratio, pre_inst_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
					for (TopHolding topHolding : topHoldings) {
						ps.setString(1, topHolding.getQuarter());
						ps.setString(2, topHolding.getCode());
						ps.setString(3, topHolding.getStockName());
						ps.setInt(4, topHolding.getInstNumber());
						ps.setDouble(5, topHolding.getHoldingNumber());
						ps.setDouble(6, topHolding.getASharesRatio());
						ps.setDouble(7, topHolding.getHoldingIncrease());
						ps.setDouble(8, topHolding.getHoldingRatio());
						ps.setInt(9, topHolding.getPreInstNumber());
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

	public static List<TopHolding> getAll(String tableName) {
		List<TopHolding> topHoldingList = new ArrayList<>();
		if (tableName != null) {
			Connection conn = DbConnection.getConnection();
			if (conn != null) {
				try {
					String sql = "select * from " + tableName + " order by quarter desc";
					Statement stat = conn.createStatement();
					ResultSet rs = stat.executeQuery(sql);
					while (rs.next()) {
						TopHolding topHolding = new TopHolding();
						topHolding.setQuarter(rs.getString("quarter"));
						topHolding.setCode(rs.getString("code"));
						topHolding.setStockName(rs.getString("stock_name"));
						topHolding.setInstNumber(rs.getInt("inst_number"));
						topHolding.setHoldingNumber(rs.getDouble("holding_number"));
						topHolding.setASharesRatio(rs.getDouble("a_shares_ratio"));
						topHolding.setHoldingIncrease(rs.getDouble("holding_increase"));
						topHolding.setHoldingRatio(rs.getDouble("holding_ratio"));
						topHolding.setPreInstNumber(rs.getInt("pre_inst_number"));
						topHoldingList.add(topHolding);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return topHoldingList;
	}

}
