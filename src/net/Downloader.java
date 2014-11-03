package net;

import java.io.IOException;
import java.net.SocketTimeoutException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import util.Log;
import util.Sleep;

public class Downloader {

	static final int RETRY = 3;
	static final int SLEEP = 3;

	public static Document downloadHtml(String url) {
		if (url != null) {
			for (int i = 1; i <= RETRY; ++i) {
				Log.info("download(" + i + ") " + url);
				try {
					Document doc = Jsoup.connect(url).maxBodySize(0).ignoreContentType(true).get();
					if (doc != null) {
						return doc;
					}
				} catch (IOException e) {
					if (e instanceof SocketTimeoutException) {
						if (i < 3) {
							Sleep.sleep(SLEEP * i);
							continue;
						} else {
							Log.info("download(" + i + ") " + url + " fail!");
						}
					}
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
