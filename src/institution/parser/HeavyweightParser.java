package institution.parser;

import institution.model.Heavyweight;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;

import net.Downloader;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HeavyweightParser implements Callable<List<Heavyweight>> {

	static final int MAXNUM = 1000000;

	public enum Type {
		jjzc, sbzc, qfii;
	}

	final Type type;
	final String year;
	final String quarter;

	public HeavyweightParser(Type type, String year, String quarter) {
		this.type = type;
		this.year = year;
		this.quarter = quarter;
	}

	public List<Heavyweight> call() throws Exception {
		List<Heavyweight> ret = new CopyOnWriteArrayList<>();
		Document doc = Downloader.downloadHtml("http://vip.stock.finance.sina.com.cn/q/go.php/vComStockHold/kind/" + type
				+ "/index.phtml?reportdate=" + year + "&quarter=" + quarter + "&num=" + MAXNUM);
		if (doc != null) {
			Elements dataTable = doc.select("#dataTable tbody tr");
			for (int i = 0, size = dataTable.size(); i < size; ++i) {
				Element tr = dataTable.get(i);
				if (tr.select("td[style=text-align:center]").size() > 0) {
					Heavyweight obj = new Heavyweight();
					obj.setQuarter(year + "_" + quarter);
					Elements tds = tr.select("td");
					obj.setCode(tds.get(0).text());
					obj.setStockName(tds.get(1).text());
					obj.setInstNumber(Integer.parseInt(tds.get(3).text()));
					obj.setHoldingNumber(Double.parseDouble(tds.get(4).text()));
					obj.setASharesRatio(Double.parseDouble(tds.get(5).text()));
					obj.setHoldingIncrease(Double.parseDouble(tds.get(6).text()));
					obj.setHoldingRatio(Double.parseDouble(tds.get(7).text()));
					obj.setPreInstNumber(Integer.parseInt(tds.get(8).text()));
					ret.add(obj);
				}
			}
		}
		return ret;
	}

}
