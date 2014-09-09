package updater.holding;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;

import model.TopHolding;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import updater.Downloader;

public class TopHoldingParser implements Callable<List<TopHolding>> {

	static final int MAXNUM = 1000000;

	public enum Type {
		jjzc, sbzc, qfii;
	}

	final Type type;
	final String year;
	final String quarter;
	final String url;
	final List<TopHolding> topHoldings;

	public TopHoldingParser(Type type, String year, String quarter) {
		this.type = type;
		this.year = year;
		this.quarter = quarter;
		this.url = "http://vip.stock.finance.sina.com.cn/q/go.php/vComStockHold/kind/" + type + "/index.phtml?reportdate=" + year + "&quarter="
				+ quarter + "&num=" + MAXNUM;
		this.topHoldings = new CopyOnWriteArrayList<>();
	}

	public List<TopHolding> call() throws Exception {
		Document doc = Downloader.downloadHtml(url);
		if (doc != null) {
			parse(doc);
		}
		return topHoldings;
	}

	void parse(Document doc) {
		Elements dataTable = doc.select("#dataTable tbody tr");
		for (int i = 0, size = dataTable.size(); i < size; ++i) {
			Element tr = dataTable.get(i);
			if (tr.select("td[style=text-align:center]").size() > 0) {
				TopHolding topHolding = new TopHolding();
				topHolding.setQuarter(year + "_" + quarter);
				Elements tds = tr.select("td");
				topHolding.setCode(tds.get(0).text());
				topHolding.setStockName(tds.get(1).text());
				topHolding.setInstNumber(Integer.parseInt(tds.get(3).text()));
				topHolding.setHoldingNumber(Double.parseDouble(tds.get(4).text()));
				topHolding.setASharesRatio(Double.parseDouble(tds.get(5).text()));
				topHolding.setHoldingIncrease(Double.parseDouble(tds.get(6).text()));
				topHolding.setHoldingRatio(Double.parseDouble(tds.get(7).text()));
				topHolding.setPreInstNumber(Integer.parseInt(tds.get(8).text()));
				topHoldings.add(topHolding);
			}
		}
	}

}
