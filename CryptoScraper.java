import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class CryptoScraper {

	public static void main(String[] args) throws IOException {

		Scanner scnr = new Scanner(System.in);
		String costOfCoin;
		boolean repeat = true;
		boolean invalidInput = true;

		while (repeat) {
			costOfCoin = calculateValue();
			System.out.println(costOfCoin);
			System.out.print("\nWould You Like To Find The Price Of a Crypto Again? Yes or No. ");
			String tempQuestion = scnr.next();

			while (tempQuestion.compareTo("Yes") != 0 && tempQuestion.compareTo("No") != 0) {

				System.out.println("INVALID INPUT: Yes or No? ");
				tempQuestion = scnr.next();
			}

			if (tempQuestion.compareTo("No") == 0) {
				repeat = false;
			}

		}

	}

	// finds out which currency the user would like to find the price of
	private static String coinInput() {
		Scanner scnr = new Scanner(System.in);
		String temp;
		System.out.println("What Pricing Do You Want To See?");
		System.out.println("1) BTC\n2) ETH\n3) LTC");
		System.out.print("Please Enter BTC, ETH, or LTC: ");
		temp = scnr.next();
		if (temp.compareTo("BTC") != 0 && temp.compareTo("ETH") != 0 && temp.compareTo("LTC") != 0) {

			System.out.println("Please Enter BTC, ETH,or LTC.");
			coinInput();
		}

		return temp.toLowerCase();
	}

	// finds out which exchange the user would like to use
	private static String exchangeInput() {
		Scanner scnr = new Scanner(System.in);
		String temp;
		
		System.out.println("Which Exchange Would You Like To Use?");
		System.out.println("1) GDAX\n2) Bitstamp\n3) Bitfinex");
		System.out.print("Please Enter GDAX, Bitstamp, or Bitfinex: ");
		temp = scnr.next();
		if (temp.compareTo("GDAX") != 0 && temp.compareTo("Bitstamp") != 0 && temp.compareTo("Bitfinex") != 0) {

			System.out.println("Please Enter GDAX, Bitstamp, or Bitfinex.");
			exchangeInput();
		}

		return temp.toLowerCase();
	}

	// method that calculates which currency the user wants to use and which
	// exchange site
	private static String calculateValue() throws IOException {
		String userCoinChoice;
		String userExchangeChoice;
		Scanner scnr = new Scanner(System.in);

		userCoinChoice = coinInput();
		userExchangeChoice = exchangeInput();
		
		Document doc = Jsoup.connect("https://cryptowat.ch").userAgent("Mozilla/17.0").get();

		Elements links = doc.select("a[href=\"/" + userExchangeChoice + "/" + userCoinChoice + "usd\"]");
		String textName = links.select("div.ranking-row__cell--name").text();
		String textPrice = links.select("span.price-right").text();

		String temp = "\nCurrently the price of " + userCoinChoice.toUpperCase() + " on " + textName + " is $"
				+ textPrice;
		return temp;

	}

}
