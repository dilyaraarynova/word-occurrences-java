import word_occurrences.WordOccurrences;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BasicTester {

    public static void main(String[] args) throws IOException {
        test1();
        test2();
        test3();
    }

    public static void test1() throws IOException {

        System.out.println("Running test1");

        // You may have to change the file path to get it to work on your computer
        WordOccurrences occ = new WordOccurrences(new File("/Users/dilyaraarynova/Desktop/PL/Assignment2/tests/wisdom.txt"));
        System.out.println(occ);
        System.out.println("distinct words: " + occ.distinctWords());
        System.out.println("total occurrences: " + occ.totalOccurrences());
        
        System.out.println("total occurrences of woman in wisdom.txt: "
                + occ.totalOccurrencesOfWordInFile("woman", "/Users/dilyaraarynova/Desktop/PL/Assignment2/tests/wisdom.txt"));
      
        System.out.println();
    }

    public static void test2() throws IOException {

        System.out.println("------------------");
        System.out.println("Running test2");

        // You may have to change the file path to get it to work on your computer
        WordOccurrences occ = new WordOccurrences(new File("tests"));

        System.out.println("distinct words: " + occ.distinctWords());
        System.out.println("total occurrences: " + occ.totalOccurrences());
        System.out.println("total occurrences of love: " + occ.totalOccurrencesOfWord("love"));
        System.out.println("total occurrences of ob-la-di: " + occ.totalOccurrencesOfWord("ob-la-di"));
        System.out.println("total occurrences of detest: " + occ.totalOccurrencesOfWord("detest"));

        System.out.println("total occurrences of that's in Money.txt: "
                + occ.totalOccurrencesOfWordInFile("that's", "/Users/dilyaraarynova/Desktop/PL/Assignment2/tests/Music/Beatles/Money.txt"));

        System.out.println("total occurrences of ob-la-di in Money.txt: "
                + occ.totalOccurrencesOfWordInFile("ob-la-di", "/Users/dilyaraarynova/Desktop/PL/Assignment2/tests/Music/Beatles/Money.txt"));

        System.out.println("total occurrences of the in Funny.txt: "
                + occ.totalOccurrencesOfWordInFile("the", "/Users/dilyaraarynova/Desktop/PL/Assignment2/tests/Music/Beatles/Funny.txt"));
    
        System.out.println("total occurrences of love in Love Me Do.txt: "
                + occ.totalOccurrencesOfWordInFile("love", "/Users/dilyaraarynova/Desktop/PL/Assignment2/tests/Music/Beatles/Love Me Do.txt"));
      

//        System.out.print(occ);
    }
    
       public static void test3() throws IOException {

        System.out.println("------------------");
        System.out.println("Running test3");

        // You may have to change the file path to get it to work on your computer
        WordOccurrences occ = new WordOccurrences(new File("TaylorSwift"));

        System.out.println("distinct words: " + occ.distinctWords());
        System.out.println("total occurrences: " + occ.totalOccurrences());
        System.out.println("total occurrences of love: " + occ.totalOccurrencesOfWord("love"));
        System.out.println("total occurrences of Kanye West: " + occ.totalOccurrencesOfWord("Kanye West"));

        
            System.out.println("total occurrences of willow in willow.txt: "
                + occ.totalOccurrencesOfWordInFile("willow", "/Users/dilyaraarynova/Desktop/PL/Assignment2/TaylorSwift/willow"));

        System.out.println("\ntotal occurrences of love in champagne problems: "
                + occ.totalOccurrencesOfWordInFile("love", "/Users/dilyaraarynova/Desktop/PL/Assignment2/TaylorSwift/champagne problems"));
        System.out.println("total occurrences of love in willow: "
                + occ.totalOccurrencesOfWordInFile("love", "/Users/dilyaraarynova/Desktop/PL/Assignment2/TaylorSwift/willow"));
        System.out.println("total occurrences of love in gold rush: "
                + occ.totalOccurrencesOfWordInFile("love", "/Users/dilyaraarynova/Desktop/PL/Assignment2/TaylorSwift/gold rush"));
        System.out.println("total occurrences of love in 'tis the damn season: "
                + occ.totalOccurrencesOfWordInFile("love", "/Users/dilyaraarynova/Desktop/PL/Assignment2/TaylorSwift/'tis the damn season"));

        System.out.println("\ntotal occurrences of bride in champagne problems: "
                + occ.totalOccurrencesOfWordInFile("bride", "/Users/dilyaraarynova/Desktop/PL/Assignment2/TaylorSwift/champagne problems"));

        System.out.println("\ntotal occurrences of Kanye West in 'tis the damn season: "
                + occ.totalOccurrencesOfWordInFile("Kanye West", "/Users/dilyaraarynova/Desktop/PL/Assignment2/TaylorSwift/'tis the damn season"));

//        System.out.print(occ);
    }

}
