package db;

import java.util.ArrayList;
import java.util.List;

import model.InstHolding;
import model.TopHolding;
import updater.holding.InstHoldingParser;
import updater.holding.TopHoldingParser;
import file.FileUtil;

public class Export {

	public static void instHoldingAsExcel() {
		for (InstHoldingParser.Type type : InstHoldingParser.Type.values()) {
			String tableName = String.valueOf(type);
			List<InstHolding> instHoldingList = InstHoldingTable.getAll(tableName);
			List<String> lines = new ArrayList<>();
			for (InstHolding instHolding : instHoldingList) {
				lines.add(instHolding.toString());
			}
			FileUtil.save("./data/" + tableName + ".csv", lines);
		}
	}

	public static void topHoldingAsExcel() {
		for (TopHoldingParser.Type type : TopHoldingParser.Type.values()) {
			String tableName = String.valueOf(type);
			List<TopHolding> topHoldingList = TopHoldingTable.getAll(tableName);
			List<String> lines = new ArrayList<>();
			for (TopHolding topHolding : topHoldingList) {
				lines.add(topHolding.toString());
			}
			FileUtil.save("./data/" + tableName + ".csv", lines);
		}
	}

	public static void main(String[] args) {
		instHoldingAsExcel();
		topHoldingAsExcel();
	}

}
