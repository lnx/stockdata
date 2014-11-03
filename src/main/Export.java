package main;

import file.FileUtil;
import institution.model.Heavyweight;
import institution.model.InstitutionSummary;
import institution.parser.HeavyweightParser;
import institution.parser.InstitutionSummaryParser;
import institution.table.HeavyweightTable;
import institution.table.InstitutionSummaryTable;

import java.util.ArrayList;
import java.util.List;

public class Export {

	public static void institutionSummaryAsExcel() {
		for (InstitutionSummaryParser.Type type : InstitutionSummaryParser.Type.values()) {
			String tableName = String.valueOf(type);
			List<InstitutionSummary> list = InstitutionSummaryTable.getAll(tableName);
			List<String> lines = new ArrayList<>();
			for (InstitutionSummary obj : list) {
				lines.add(obj.toString());
			}
			FileUtil.save("./data/" + tableName + ".csv", lines);
		}
	}

	public static void heavyweightAsExcel() {
		for (HeavyweightParser.Type type : HeavyweightParser.Type.values()) {
			String tableName = String.valueOf(type);
			List<Heavyweight> list = HeavyweightTable.getAll(tableName);
			List<String> lines = new ArrayList<>();
			for (Heavyweight obj : list) {
				lines.add(obj.toString());
			}
			FileUtil.save("./data/" + tableName + ".csv", lines);
		}
	}

	public static void main(String[] args) {
		institutionSummaryAsExcel();
		heavyweightAsExcel();
	}

}
