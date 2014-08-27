package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

}
