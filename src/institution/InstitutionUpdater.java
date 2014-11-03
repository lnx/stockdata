package institution;

import institution.model.Heavyweight;
import institution.model.InstitutionSummary;
import institution.parser.HeavyweightParser;
import institution.parser.InstitutionSummaryParser;
import institution.table.HeavyweightTable;
import institution.table.InstitutionSummaryTable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class InstitutionUpdater {

	static final int THREAD = 10;

	public static void updateAll(int startYear, int endYear) {
		updateInstitutionSummary(startYear, endYear);
		updateHeavyweight(startYear, endYear);
	}

	static void updateInstitutionSummary(int startYear, int endYear) {
		ExecutorService es = Executors.newFixedThreadPool(THREAD);
		for (InstitutionSummaryParser.Type type : InstitutionSummaryParser.Type.values()) {
			List<Callable<List<InstitutionSummary>>> tasks = new ArrayList<>();
			for (int i = startYear; i <= endYear; ++i) {
				for (int j = 1; j <= 4; ++j) {
					tasks.add(new InstitutionSummaryParser(type, String.valueOf(i), String.valueOf(j)));
				}
			}
			try {
				List<InstitutionSummary> institutionSummaryList = new ArrayList<>();
				List<Future<List<InstitutionSummary>>> futures = es.invokeAll(tasks);
				for (Future<List<InstitutionSummary>> future : futures) {
					institutionSummaryList.addAll(future.get());
				}
				InstitutionSummaryTable.ignoreInsert(type.toString(), institutionSummaryList);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		es.shutdown();
	}

	static void updateHeavyweight(int startYear, int endYear) {
		ExecutorService es = Executors.newFixedThreadPool(THREAD);
		for (HeavyweightParser.Type type : HeavyweightParser.Type.values()) {
			List<Callable<List<Heavyweight>>> tasks = new ArrayList<>();
			for (int i = startYear; i <= endYear; ++i) {
				for (int j = 1; j <= 4; ++j) {
					tasks.add(new HeavyweightParser(type, String.valueOf(i), String.valueOf(j)));
				}
			}
			try {
				List<Heavyweight> heavyweightList = new ArrayList<>();
				List<Future<List<Heavyweight>>> futures = es.invokeAll(tasks);
				for (Future<List<Heavyweight>> future : futures) {
					heavyweightList.addAll(future.get());
				}
				HeavyweightTable.ignoreInsert(type.toString(), heavyweightList);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		es.shutdown();
	}

}
