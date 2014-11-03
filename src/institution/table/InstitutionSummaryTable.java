package institution.table;

import institution.model.InstitutionSummary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.Log;
import db.DbConnection;

public class InstitutionSummaryTable {

	public static void ignoreInsert(String tableName, List<InstitutionSummary> institutionSummaryList) {
		if (tableName != null && institutionSummaryList.size() > 0) {
			Connection conn = DbConnection.getConnection();
			if (conn != null) {
				try {
					String sql = "INSERT IGNORE INTO "
							+ tableName
							+ "(quarter, code, stock_name, inst_number, inst_number_increase, holding_ratio, holding_ratio_increase, a_shares_ratio, a_shares_ratio_increase) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement ps = conn.prepareStatement(sql);
					for (InstitutionSummary obj : institutionSummaryList) {
						ps.setString(1, obj.getQuarter());
						ps.setString(2, obj.getCode());
						ps.setString(3, obj.getStockName());
						ps.setInt(4, obj.getInstNumber());
						ps.setInt(5, obj.getInstNumberIncrease());
						ps.setDouble(6, obj.getHoldingRatio());
						ps.setDouble(7, obj.getHoldingRatioIncrease());
						ps.setDouble(8, obj.getASharesRatio());
						ps.setDouble(9, obj.getASharesRatioIncrease());
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

	public static List<InstitutionSummary> getAll(String tableName) {
		List<InstitutionSummary> ret = new ArrayList<>();
		if (tableName != null) {
			Connection conn = DbConnection.getConnection();
			if (conn != null) {
				try {
					String sql = "SELECT * FROM " + tableName + " ORDER BY quarter DESC";
					Statement stat = conn.createStatement();
					ResultSet rs = stat.executeQuery(sql);
					while (rs.next()) {
						InstitutionSummary obj = new InstitutionSummary();
						obj.setQuarter(rs.getString("quarter"));
						obj.setCode(rs.getString("code"));
						obj.setStockName(rs.getString("stock_name"));
						obj.setInstNumber(rs.getInt("inst_number"));
						obj.setInstNumberIncrease(rs.getInt("inst_number_increase"));
						obj.setHoldingRatio(rs.getDouble("holding_ratio"));
						obj.setHoldingRatioIncrease(rs.getDouble("holding_ratio_increase"));
						obj.setASharesRatio(rs.getDouble("a_shares_ratio"));
						obj.setASharesRatioIncrease(rs.getDouble("a_shares_ratio_increase"));
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
