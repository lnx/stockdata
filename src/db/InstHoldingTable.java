package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.InstHolding;
import util.Log;

public class InstHoldingTable {

	public static void ignoreInsert(String tableName, List<InstHolding> instHoldings) {
		if (tableName != null && instHoldings.size() > 0) {
			Connection conn = DbConnection.getConnection();
			if (conn != null) {
				try {
					PreparedStatement ps = conn
							.prepareStatement("insert ignore into "
									+ tableName
									+ "(quarter, code, stock_name, inst_number, inst_number_increase, holding_ratio, holding_ratio_increase, a_shares_ratio, a_shares_ratio_increase) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
					for (InstHolding instHolding : instHoldings) {
						ps.setString(1, instHolding.getQuarter());
						ps.setString(2, instHolding.getCode());
						ps.setString(3, instHolding.getStockName());
						ps.setInt(4, instHolding.getInstNumber());
						ps.setInt(5, instHolding.getInstNumberIncrease());
						ps.setDouble(6, instHolding.getHoldingRatio());
						ps.setDouble(7, instHolding.getHoldingRatioIncrease());
						ps.setDouble(8, instHolding.getASharesRatio());
						ps.setDouble(9, instHolding.getASharesRatioIncrease());
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

	public static List<InstHolding> getAll(String tableName) {
		List<InstHolding> instHoldingList = new ArrayList<>();
		if (tableName != null) {
			Connection conn = DbConnection.getConnection();
			if (conn != null) {
				try {
					String sql = "select * from " + tableName + " order by quarter desc";
					Statement stat = conn.createStatement();
					ResultSet rs = stat.executeQuery(sql);
					while (rs.next()) {
						InstHolding instHolding = new InstHolding();
						instHolding.setQuarter(rs.getString("quarter"));
						instHolding.setCode(rs.getString("code"));
						instHolding.setStockName(rs.getString("stock_name"));
						instHolding.setInstNumber(rs.getInt("inst_number"));
						instHolding.setInstNumberIncrease(rs.getInt("inst_number_increase"));
						instHolding.setHoldingRatio(rs.getDouble("holding_ratio"));
						instHolding.setHoldingRatioIncrease(rs.getDouble("holding_ratio_increase"));
						instHolding.setASharesRatio(rs.getDouble("a_shares_ratio"));
						instHolding.setASharesRatioIncrease(rs.getDouble("a_shares_ratio_increase"));
						instHoldingList.add(instHolding);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return instHoldingList;
	}

}
