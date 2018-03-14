import java.io.FileNotFoundException;

import org.json.simple.parser.ParseException;

import data.BioTree;
import data.DataStore;
import data.FileProcessor;
import data.Record;
import search.BasicSearch;
import sort.KDT;

public class Main {
	public static void main(String[] args) {
		printLogo();
		//load data
		try {
			BioTree.init("data/biotree/");
			DataStore.records = new KDT<Record>("data/kdt.ser");
		} catch (Exception e0) {
			try {
				BioTree.init();
				FileProcessor.initProcessing();
			} catch (NumberFormatException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BioTree.write("data/biotree/");
			DataStore.records.writeToFile("data/kdt.ser");
		};
		BasicSearch.init();
	}
	
	private static void printLogo() {
		System.out.println("======== TRAWLEXPERT ALPHA v1 ========");
		System.out.println("                                   _...----.\r\n" + 
				"                       _,      _,-'_...--'`/\r\n" + 
				"                     ,^__\\__ ,'  ,'      /(\r\n" + 
				"                 _,.'  |  | `-._;        \\/\r\n" + 
				"              ,-'  |  /  /   \\  \\-.       \\\r\n" + 
				"            ,'   \\ \\  \\ <    /  <  \\     ,:\r\n" + 
				"           /     | <   `.\\   >  /   \\  ,' |    _,-'/\r\n" + 
				"          /      /  \\        \\  \\    \\/  _; ,-'   ;\r\n" + 
				"         /       `.  \\       /   \\   /`<__,' \\   /|\r\n" + 
				"        ; (O)      > |       >   <   \\ \\`^.  /   :|\r\n" + 
				"       /         :\\  |_.--. <    ,`  / /  (  >   :|\r\n" + 
				"       >)        ;/,='   `.\\ \\   |  / / __/  \\   ;|\r\n" + 
				"       \\       ,' |)     ,'/ /   |  ) |/-.`. /   \\|\r\n" + 
				"        `.   ,'   | `--=='`  >   |  `./`-.\\ `-._  :\r\n" + 
				"          `.      / /        \\   \\   ;`.__/     `-.\\\r\n" + 
				"            `-._  \\ |_       :   < _;    <   SSt\r\n" + 
				"                ``'.:``-._____\\_,-'-..___,\\\r\n" + 
				"                     \\`.|`._\\  `--..__     `._\r\n" + 
				"                      `-'             `````````");
		System.out.println("Loading.......");
	}
}
