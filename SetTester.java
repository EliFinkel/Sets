
/*
 * Student information for assignment:
 *
 * Number of slip days used: 0
 * Student 1
 *  UTEID: gaa2292
 *  email address: gaa2292@utexas.edu
 *  Grader name: Gracelynn Ray
 *
 * Student 2
 *  UTEID: egf525
 *  email address: eligfinkel@utexas.edu
 */

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JFileChooser;

/*
 * CS 314 Students, put your results to the experiments and answers to questions
 * here: CS314 Students, why is it unwise to implement all three of the
 * intersection, union, and difference methods in the AbstractSet class:
       
  It would be unwise because implementing a set that returns an ISet<E> means we need 
  to call one of the other two type ISet<E> methods in abstractSet. This is unefficient.

* Sorted Set
        Name          Size      Total Words       Increase from Prev      Unique     Inc prev     Actual time      Inc prev row
        pg1.txt       124kb         23353               -                  4522         -            0.070609          -
        pg2.txt       273kb         46088              22735               11708        7186         0.042786292       -.02783371
        pg3.txt       44kb          7423               -38665              2488         -9220        0.018292208       -0.024494084
        pg4.txt       285kb         50573              43150               9919         7431         0.043246541       0.024954333
* UnSorted Set
        Name          Size      Total Words       Increase from Prev      Unique     Inc prev     Actual time      Inc prev row
        pg1.txt       124kb         23353               -                  4522         -            0.094523792          -
        pg2.txt       273kb         46088              22735               11708        7186         0.393122666       .298598874
        pg3.txt       44kb          7423               -38665              2488         -9220        0.067191708       -0.325930958
        pg4.txt       285kb         50573              43150               9919         7431         0.288637583       0.221445875

* Java HashSet
        Name          Size      Total Words       Increase from Prev      Unique     Inc prev     Actual time      Inc prev row
        pg1.txt       124kb         23353               -                  4522         -            0.010754167          -
        pg2.txt       273kb         46088              22735               11708        7186         0.027183958       0.016429791
        pg3.txt       44kb          7423               -38665              2488         -9220        0.00811475        -0.019069208
        pg4.txt       285kb         50573              43150               9919         7431         0.039503625       0.031388875

* Java TreeSet
        Name          Size      Total Words       Increase from Prev      Unique     Inc prev     Actual time      Inc prev row
        pg1.txt       124kb         23353               -                  4522         -            0.013395125          -
        pg2.txt       273kb         46088              22735               11708        7186         0.035450458       0.022055333
        pg3.txt       44kb          7423               -38665              2488         -9220        0.012791959      -0.022658499
        pg4.txt       285kb         50573              43150               9919         7431         0.037296542       0.024504583

* Process Text Big O
  Sorted -> O(N^2)
  Unsorted -> O(N^2)
  HashSet -> O(N)
  TreeSet -> O(NLogN)

* Big O of Add Methods
  Sorted -> O(N)
  Unsorted -> O(N)
  HashSet -> O(1)
  TreeSet -> O(LogN)

* What are the differences between HashSet and TreeSet when printing out the contents of the Set?
  A HashSet prints elements in no specific order, 
  while a TreeSet prints elements in a sorted order. 

*/

public class SetTester {
    public static void main(String[] args) {
        ISet<String> s1 = new UnsortedSet<>();
        s1.add("A");
        s1.add("C");
        s1.add("A");
        s1.add("B");

        

        // CS314 Students. Uncomment this section when ready to
        // run your experiments
        // try {
        //         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        // } catch (Exception e) {
        //         System.out.println("Unable to change look and feel");
        // }
        // Scanner sc = new Scanner(System.in);
        // String response = "";
        // do {
        //         largeTest();
        //         System.out.print("Another file? Enter y to do another file: ");
        //         response = sc.next();
        // } while (response != null && response.length() > 0
        //                 && response.substring(0, 1).equalsIgnoreCase("y"));
    }

    // print out results of test
    private static <E> void showTestResults(boolean actualResult, boolean expectedResult,
        int testNumber, ISet<E> set1, ISet<E> set2, String testDescription) {
        if (actualResult == expectedResult) {
            System.out.println("Passed test " + testNumber);
        } else {
            System.out.print("Failed test ");
            System.out.println(testNumber + ": " + testDescription);
            System.out.println("Expected result: " + expectedResult);
            System.out.println("Actual result  : " + actualResult);
            System.out.println("Set 1: " + set1);
            if (set2 != null) {
                System.out.println("Set 2: " + set2);
            }
        }
    }

    /*
    * Method asks user for file and compares run times to add words from file
    * to various sets, including CS314 UnsortedSet and SortedSet and Java's
    * TreeSet and HashSet.
    */
    private static void largeTest() {
        System.out.println();
        System.out.println("Opening Window to select file. "
                        + "You may have to minimize other windows.");
        String text = convertFileToString();
        Scanner keyboard = new Scanner(System.in);
        System.out.println();
        System.out.println("***** CS314 SortedSet: *****");
        processTextCS314Sets(new SortedSet<String>(), text, keyboard);
        System.out.println("****** CS314 UnsortedSet: *****");
        processTextCS314Sets(new UnsortedSet<String>(), text, keyboard);
        System.out.println("***** Java HashSet ******");
        processTextJavaSets(new HashSet<String>(), text, keyboard);
        System.out.println("***** Java TreeSet ******");
        processTextJavaSets(new TreeSet<String>(), text, keyboard);
    }

    /*
    * pre: set != null, text != null Method to add all words in text to the
    * given set. Words are delimited by white space. This version for CS314
    * sets.
    */
    private static void processTextCS314Sets(ISet<String> set, String text, Scanner keyboard) {
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while (sc.hasNext()) {
            totalWords++;
            set.add(sc.next());
        }
        s.stop();
        showResultsAndWords(set, s, totalWords, set.size(), keyboard);
    }

    /*
    * pre: set != null, text != null Method to add all words in text to the
    * given set. Words are delimited by white space. This version for Java
    * Sets.
    */
    private static void processTextJavaSets(Set<String> set, String text,
                                                        Scanner keyboard) {
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while (sc.hasNext()) {
            totalWords++;
            set.add(sc.next());
        }
        s.stop();
        sc.close();

        showResultsAndWords(set, s, totalWords, set.size(), keyboard);
    }

    /*
    * Show results of add words to given set.
    */
    private static <E> void showResultsAndWords(Iterable<E> set, Stopwatch s, int totalWords,
                                                                int setSize, Scanner keyboard) {

        System.out.println("Time to add the elements in the text to this set: " + s.toString());
        System.out.println("Total number of words in text including duplicates: " + totalWords);
        System.out.println("Number of distinct words in this text " + setSize);

        System.out.print("Enter y to see the contents of this set: ");
        String response = keyboard.next();

        if (response != null && response.length() > 0
                                && response.substring(0, 1).equalsIgnoreCase("y")) {
            for (Object o : set) {
                System.out.println(o);
            }
        }
        System.out.println();
    }

    /*
    * Ask user to pick a file via a file choosing window and convert that file
    * to a String. Since we are evaluating the file with many sets convert to
    * string once instead of reading through file multiple times.
    */
    private static String convertFileToString() {
        // create a GUI window to pick the text to evaluate
        JFileChooser chooser = new JFileChooser(".");
        StringBuilder text = new StringBuilder();
        int retval = chooser.showOpenDialog(null);

        chooser.grabFocus();

        // read in the file
        if (retval == JFileChooser.APPROVE_OPTION) {
            File source = chooser.getSelectedFile();
            Scanner s = null;
            try {
                s = new Scanner(new FileReader(source));

                while (s.hasNextLine()) {
                text.append(s.nextLine());
                text.append(" ");
                }
                s.close();
            } catch (IOException e) {
                System.out.println("An error occured while trying to read from the file: " + e);
            } finally {
                if (s != null) {
                    s.close();
                }
            }
        }

        return text.toString();
    }

}