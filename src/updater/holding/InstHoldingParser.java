package updater.holding;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;

import model.InstHolding;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import updater.Downloader;

public class InstHoldingParser implements Callable<List<InstHolding>> {

	static final int MAXNUM = 1000000;

	public enum Type {
		jgcg;
	}

	final Type type;
	final String year;
	final String quarter;
	final String url;
	final List<InstHolding> instHoldings;

	public InstHoldingParser(Type type, String year, String quarter) {
		this.type = type;
		this.year = year;
		this.quarter = quarter;
		this.url = "http://vip.stock.finance.sina.com.cn/q/go.php/vComStockHold/kind/" + type + "/index.phtml?reportdate=" + year + "&quarter="
				+ quarter + "&num=" + MAXNUM;
		this.instHoldings = new CopyOnWriteArrayList<>();
	}

	public List<InstHolding> call() throws Exception {
		Document doc = Downloader.downloadHtml(url);
		if (doc != null) {
			parse(doc);
		}
		return instHoldings;
	}

	void parse(Document doc) {
		Elements dataTable = doc.select("#dataTable tbody tr");
		for (int i = 0, size = dataTable.size(); i < size; ++i) {
			Element tr = dataTable.get(i);
			if (tr.select("td[style=text-align:center]").size() > 0) {
				InstHolding InstHolding = new InstHolding();
				InstHolding.setQuarter(year + "_" + quarter);
				Elements tds = tr.select("td");
				InstHolding.setCode(tds.get(0).text());
				InstHolding.setStockName(tds.get(1).text());
				InstHolding.setInstNumber(Integer.parseInt(tds.get(2).text()));
				InstHolding.setInstNumberIncrease(Integer.parseInt(tds.get(3).text()));
				InstHolding.setHoldingRatio(Double.parseDouble(tds.get(4).text()));
				InstHolding.setHoldingRatioIncrease(Double.parseDouble(tds.get(5).text()));
				InstHolding.setASharesRatio(Double.parseDouble(tds.get(6).text()));
				InstHolding.setASharesRatioIncrease(Double.parseDouble(tds.get(7).text()));
				instHoldings.add(InstHolding);
			}
		}
	}

}
