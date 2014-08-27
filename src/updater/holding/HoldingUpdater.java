package updater.holding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import model.InstHolding;
import model.TopHolding;
import db.InstHoldingTable;
import db.TopHoldingTable;

public class HoldingUpdater {

	static final int THREAD = 10;

	public static void updateAll(int startYear, int endYear) {
		updateInstHolding(startYear, endYear);
		updateTopHolding(startYear, endYear);
	}

	static void updateInstHolding(int startYear, int endYear) {
		ExecutorService es = Executors.newFixedThreadPool(THREAD);
		for (InstHoldingParser.Type type : InstHoldingParser.Type.values()) {
			List<Callable<List<InstHolding>>> tasks = new ArrayList<>();
			for (int i = startYear; i <= endYear; ++i) {
				for (int j = 1; j <= 4; ++j) {
					tasks.add(new InstHoldingParser(type, String.valueOf(i), String.valueOf(j)));
				}
			}
			try {
				List<InstHolding> instHoldings = new ArrayList<>();
				List<Future<List<InstHolding>>> futures = es.invokeAll(tasks);
				for (Future<List<InstHolding>> future : futures) {
					instHoldings.addAll(future.get());
				}
				InstHoldingTable.ignoreInsert(type.toString(), instHoldings);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		es.shutdown();
	}

	static void updateTopHolding(int startYear, int endYear) {
		ExecutorService es = Executors.newFixedThreadPool(THREAD);
		for (TopHoldingParser.Type type : TopHoldingParser.Type.values()) {
			List<Callable<List<TopHolding>>> tasks = new ArrayList<>();
			for (int i = startYear; i <= endYear; ++i) {
				for (int j = 1; j <= 4; ++j) {
					tasks.add(new TopHoldingParser(type, String.valueOf(i), String.valueOf(j)));
				}
			}
			try {
				List<TopHolding> topHoldings = new ArrayList<>();
				List<Future<List<TopHolding>>> futures = es.invokeAll(tasks);
				for (Future<List<TopHolding>> future : futures) {
					topHoldings.addAll(future.get());
				}
				TopHoldingTable.ignoreInsert(type.toString(), topHoldings);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		es.shutdown();
	}

	public static void main(String[] args) {
		updateAll(2005, 2014);
	}

}
