package institution.parser;

import institution.model.InstitutionSummary;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;

import net.Downloader;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class InstitutionSummaryParser implements Callable<List<InstitutionSummary>> {

	static final int MAXNUM = 1000000;

	public enum Type {
		jgcg;
	}

	final Type type;
	final String year;
	final String quarter;

	public InstitutionSummaryParser(Type type, String year, String quarter) {
		this.type = type;
		this.year = year;
		this.quarter = quarter;
	}

	public List<InstitutionSummary> call() throws Exception {
		List<InstitutionSummary> ret = new CopyOnWriteArrayList<>();
		Document doc = Downloader.downloadHtml("http://vip.stock.finance.sina.com.cn/q/go.php/vComStockHold/kind/" + type
				+ "/index.phtml?reportdate=" + year + "&quarter=" + quarter + "&num=" + MAXNUM);
		if (doc != null) {
			Elements dataTable = doc.select("#dataTable tbody tr");
			for (int i = 0, size = dataTable.size(); i < size; ++i) {
				Element tr = dataTable.get(i);
				if (tr.select("td[style=text-align:center]").size() > 0) {
					InstitutionSummary obj = new InstitutionSummary();
					obj.setQuarter(year + "_" + quarter);
					Elements tds = tr.select("td");
					obj.setCode(tds.get(0).text());
					obj.setStockName(tds.get(1).text());
					obj.setInstNumber(Integer.parseInt(tds.get(2).text()));
					obj.setInstNumberIncrease(Integer.parseInt(tds.get(3).text()));
					obj.setHoldingRatio(Double.parseDouble(tds.get(4).text()));
					obj.setHoldingRatioIncrease(Double.parseDouble(tds.get(5).text()));
					obj.setASharesRatio(Double.parseDouble(tds.get(6).text()));
					obj.setASharesRatioIncrease(Double.parseDouble(tds.get(7).text()));
					ret.add(obj);
				}
			}
		}
		return ret;
	}

}
