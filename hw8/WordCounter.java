import java.util.Scanner;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class WordCounter{
	
	private static String fileName;

    public static WordCountMap load(String fileName, ArrayList<String> sWords){
		String word;
		WordCountMap<String> wordTree = new WordCountMap<String>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.err.println(e);
            System.exit(1);
        }
        
        while (scanner.hasNextLine()) {
			word = scanner.next().toLowerCase().replaceAll("[\\W]", "");
			if(!sWords.contains(word)){
				wordTree.incrementCount(word);
			}
        }
		return(wordTree);
		
    }
        
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("No File");
            System.exit(1);
        }
		ArrayList<String> stops = new ArrayList<String>(100);
		try {
            Scanner stopscan = new Scanner(new File("StopWords.txt"));
			System.out.println(stopscan.delimiter());
			int j = 0;
			while(stopscan.hasNextLine()){
				stops.add(stopscan.nextLine().trim());
				j++;
			}
        } catch (FileNotFoundException e) {
            System.err.println(e);
            System.exit(1);
        }
		fileName = args[0];
		ArrayList<WordCount> listy = load(fileName, stops).getWordCountsByWord();
		WordCloudMaker tada = new WordCloudMaker();
		System.out.println(tada.getWordCloudHTML("sherlock", listy));
		
		
        
        
    }
    
}
