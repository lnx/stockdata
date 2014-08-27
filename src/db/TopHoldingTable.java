package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

}
