package stock.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.Downloader;

import org.jsoup.nodes.Document;

import stock.model.Stock;

public class StockParser implements Callable<List<Stock>> {

	final List<String> codeList;

	public StockParser(List<String> codeList) {
		this.codeList = codeList;
	}

	public List<Stock> call() throws Exception {
		List<Stock> ret = new ArrayList<>();
		Document doc = codeList.size() > 0 ? Downloader.downloadHtml(getUrl()) : null;
		if (doc != null) {
			Pattern p = Pattern.compile("\"(.*?)\"");
			Matcher m = p.matcher(doc.text());
			while (m.find()) {
				String[] splits = m.group(1).split("~");
				Stock stock = new Stock();
				stock.setCode(splits[2]);
				stock.setStockName(splits[1]);
				stock.setOpen(extractNumber(splits[5]));
				stock.setHigh(extractNumber(splits[33]));
				stock.setLow(extractNumber(splits[34]));
				stock.setClose(extractNumber(splits[4]));
				stock.setVolume(extractNumber(splits[36]));
				stock.setVolumeValue(extractNumber(splits[37]));
				stock.setMarketCap(extractNumber(splits[45]));
				stock.setMarketCapFree(extractNumber(splits[44]));
				stock.setAmplitude(extractNumber(splits[43]));
				stock.setTurnoverRate(extractNumber(splits[38]));
				stock.setPb(extractNumber(splits[46]));
				stock.setPe(extractNumber(splits[39]));
				stock.setTime(splits[30]);
				ret.add(stock);
			}
		}
		return ret;
	}

	String getUrl() {
		StringBuilder sb = new StringBuilder("http://qt.gtimg.cn/q=");
		for (String code : codeList) {
			sb.append(getFullCode(code) + ",");
		}
		return sb.toString();
	}

	String getFullCode(String code) {
		return (code.startsWith("6") ? "sh" : "sz") + code;
	}

	double extractNumber(String text) {
		double ret = -1;
		if (text != null) {
			try {
				ret = Double.parseDouble(text);
			} catch (Exception e) {
			}
		}
		return ret;
	}

}
