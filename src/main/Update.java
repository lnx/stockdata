package main;

import institution.InstitutionUpdater;

import org.joda.time.DateTime;

import stock.StockUpdater;

public class Update {

	static int getYear() {
		return new DateTime().year().get();
	}

	public static void main(String[] args) {
		InstitutionUpdater.updateAll(2005, getYear());
		StockUpdater.updateAll();
	}

}
