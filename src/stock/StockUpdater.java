package stock;

import institution.parser.HeavyweightParser;
import institution.parser.InstitutionSummaryParser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import stock.model.Stock;
import stock.parser.StockParser;
import stock.table.StockTable;
import db.Query;

public class StockUpdater {

	static final int CODE = 100;
	static final int THREAD = 10;

	public static void updateAll() {
		updateStock();
	}

	static void updateStock() {
		ExecutorService es = Executors.newFixedThreadPool(THREAD);
		List<Callable<List<Stock>>> tasks = new ArrayList<>();
		Set<String> allCodes = getAllCodes();
		Iterator<String> it = allCodes.iterator();
		while (it.hasNext()) {
			List<String> codeList = new ArrayList<>();
			for (int i = 0; i < CODE && it.hasNext(); ++i) {
				codeList.add(it.next());
				it.remove();
			}
			tasks.add(new StockParser(codeList));
		}
		try {
			List<Stock> stockList = new ArrayList<>();
			List<Future<List<Stock>>> futures = es.invokeAll(tasks);
			for (Future<List<Stock>> future : futures) {
				stockList.addAll(future.get());
			}
			StockTable.update(stockList);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		es.shutdown();
	}

	static Set<String> getAllCodes() {
		Set<String> ret = new HashSet<>();
		String lastQuarter = Query.getLastQuarter();
		Set<String> tableNames = new HashSet<>();
		tableNames.add(InstitutionSummaryParser.Type.jgcg.toString());
		tableNames.add(HeavyweightParser.Type.jjzc.toString());
		tableNames.add(HeavyweightParser.Type.sbzc.toString());
		tableNames.add(HeavyweightParser.Type.qfii.toString());
		for (String tableName : tableNames) {
			ret.addAll(Query.getCodes(tableName, lastQuarter));
		}
		return ret;
	}

}
