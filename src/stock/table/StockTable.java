package stock.table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import stock.model.Stock;
import util.Log;
import db.DbConnection;

public class StockTable {

	public static void update(List<Stock> stockList) {
		if (stockList != null && stockList.size() > 0) {
			Connection conn = DbConnection.getConnection();
			if (conn != null) {
				try {
					String sql = "INSERT INTO stock (code, stock_name, open, high, low, close, volume, volume_value, market_cap, market_cap_free, amplitude, turnover_rate, pb, pe, time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE stock_name = ?, open = ?, high = ?, low = ?, close = ?, volume = ?, volume_value = ?, market_cap = ?, market_cap_free = ?, amplitude = ?, turnover_rate = ?, pb = ?, pe = ?, time = ?";
					PreparedStatement ps = conn.prepareStatement(sql);
					for (Stock obj : stockList) {
						ps.setString(1, obj.getCode());
						ps.setString(2, obj.getStockName());
						ps.setDouble(3, obj.getOpen());
						ps.setDouble(4, obj.getHigh());
						ps.setDouble(5, obj.getLow());
						ps.setDouble(6, obj.getClose());
						ps.setDouble(7, obj.getVolume());
						ps.setDouble(8, obj.getVolumeValue());
						ps.setDouble(9, obj.getMarketCap());
						ps.setDouble(10, obj.getMarketCapFree());
						ps.setDouble(11, obj.getAmplitude());
						ps.setDouble(12, obj.getTurnoverRate());
						ps.setDouble(13, obj.getPb());
						ps.setDouble(14, obj.getPe());
						ps.setString(15, obj.getTime());
						ps.setString(16, obj.getStockName());
						ps.setDouble(17, obj.getOpen());
						ps.setDouble(18, obj.getHigh());
						ps.setDouble(19, obj.getLow());
						ps.setDouble(20, obj.getClose());
						ps.setDouble(21, obj.getVolume());
						ps.setDouble(22, obj.getVolumeValue());
						ps.setDouble(23, obj.getMarketCap());
						ps.setDouble(24, obj.getMarketCapFree());
						ps.setDouble(25, obj.getAmplitude());
						ps.setDouble(26, obj.getTurnoverRate());
						ps.setDouble(27, obj.getPb());
						ps.setDouble(28, obj.getPe());
						ps.setString(29, obj.getTime());
						ps.addBatch();
					}
					ps.executeBatch();
					conn.commit();
					ps.close();
					Log.info("stock insert on duplicate key update success");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
