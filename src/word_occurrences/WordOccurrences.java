
package word_occurrences;

import java.io.*;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map;


public class WordOccurrences {

    // Maps words -> filename -> sets of their Positions in the file.
    private final TreeMap<String, TreeMap<String, TreeSet<FilePosition>>> occMap;

    /**
     * Create a new object that stores word occurrence information of
     * the contents of all files at or below the given document or directory
     * in the file hierarchy.
     *
     * @param docOrRootDir the single file to be read, or the root directory
     *                     containing the (sub)files to be read
     * @throws IOException if there is a problem accessing the given files
     */
    public WordOccurrences(File docOrRootDir) throws IOException {
        occMap = new TreeMap<>();
        buildMap(docOrRootDir);
    }

    /**
     * Helper method that adds the occurrence information to the occurrence
     * map for the given file if it is a text document, or for all subfiles
     * below if it is a directory.  This method should not access the occMap
     * map directly, but rather, it should make use of the addMapEntry method
     * to manage the occMap
     *
     * @param docOrDir the single file to be read, or the root directory
     *                 containing the (sub)files to be read
     * @throws IOException if there is a problem accessing the given files
     */
 private void buildMap(File docOrDir) throws IOException {
        
           if (docOrDir.isDirectory()) {
               
                      File[] dirFiles = docOrDir.listFiles();
               
                      if (dirFiles != null) {
                   
                                for (File child : dirFiles) {
                                            buildMap(child);
                                 }
                      }
               
           }  else if (docOrDir.isFile()) {
               
                      BufferedReader reader = new BufferedReader(new FileReader (docOrDir)) ;
             
                      int lineNumber = 1;
                      int columnNumber = 1;
                      int columnIterator = 0;
                      int ch;
                      String word = "";
             
                      do {
                 
                                 ch = reader.read();
                 
                                 if (!Syntax.isInWord((char)ch)) {
                     
                                            columnIterator++;
                     
                                            if (Syntax.isNewLine((char)ch)) {
                                                        lineNumber++;
                                                        columnNumber = 1;
                                                        columnIterator = 0;
                                            }
                     
                                            if (word.length() > 0) {
                                                        String path = docOrDir.getAbsolutePath();
                         
                                                       addMapEntry(word, path, new FilePosition(lineNumber, columnNumber));
                         
                                                        word = "";
                                            }
                                 
                                 } else {
                                     
                                            columnIterator++;
                     
                                            if (word.equals("")) {
                                                        columnNumber = columnIterator;
                                            }
                     
                                            word = word + (char)ch;
                                }
                 
                      } while (ch != -1);
               
           } 

 }


    /**
     * Helper method for buildMap, which records the occurrence of the
     * given word, in the given file, at the given position in occMap.
     * Note that because occMap is a two-level map.... i.e., it is a map
     * whose values are also maps, new maps may need to be added as values
     * to the occMap whenever a new word is encountered while the input
     * files are being read
     *
     * @param word the word whose occurrence should be recorded
     * @param filePath the path of the file in which the current word
     *                 occurrence is located
     * @param pos the position of the word in the given file
     */
    private void addMapEntry(String word, String filePath, FilePosition pos) {
            word = word.toLowerCase();

           TreeMap<String, TreeSet<FilePosition>> fileToPositions = occMap.get(word);
           if (fileToPositions == null) {
               fileToPositions = new TreeMap();
               occMap.put(word, fileToPositions);
           }
           
           TreeSet<FilePosition> positions = fileToPositions.get(filePath);
           if(positions == null) {
               positions = new TreeSet();
               fileToPositions.put(filePath, positions);
           }
           
           positions.add(pos);
          
//           TreeMap<String, TreeSet<FilePosition>> fileToPositions = new TreeMap();
//           occMap.put(word, fileToPositions);
//        
//           
//           TreeSet<FilePosition> positions = new TreeSet();
//           fileToPositions.put(filePath, positions);
//         
//           
//           positions.add(pos);
//           
    }


    /**
     * @return the number of distinct words found in the files
     */
    public int distinctWords() {
        
        int distinctWords = 0;
        
        for (Map.Entry<String, TreeMap<String,TreeSet<FilePosition>>> entry : occMap.entrySet()) {
            distinctWords++;
        }

        return distinctWords;
    }

    /**
     * @return the number of total word occurrences across all files
     */
    public int totalOccurrences() {

        int totalOccurrences = 0;
        
        for (Map.Entry<String, TreeMap<String, TreeSet<FilePosition>>> entry : occMap.entrySet()) {
            
            for (TreeSet<FilePosition> pos : entry.getValue().values()){
                totalOccurrences += pos.size();
            }
            
        }

        
        return totalOccurrences;
    }

    /**
     * Finds the total number of occurrences of a given word across
     * all files.  If the word is not found among the occurrences,
     * simply return 0.
     *
     * @param word whose occurrences we are counting
     * @return the number of occurrences
     */
    public int totalOccurrencesOfWord(String word) {
        
        word = word.toLowerCase();

        int totalOccurrencesOfWord = 0;
        
        for (Map.Entry<String, TreeMap<String, TreeSet<FilePosition>>> entry : occMap.entrySet()) {
            
              
            if (word.equals(entry.getKey())) {
                 for (TreeSet<FilePosition> pos : entry.getValue().values()){

                
                  totalOccurrencesOfWord += pos.size();
                }
                
            }
            
        }
        
//        for (Map.Entry<String, TreeMap<String,TreeSet<FilePosition>>> entry : occMap.entrySet()) {
//            if (word.equals(entry.getKey())) {
//                totalOccurrencesOfWord++;
//            }
//        }
        
        return totalOccurrencesOfWord;
    }

    /**
     * Finds the total number of occurrences of a given word in the given file.
     * If the file is not found in Occurrences, or if the word does not occur
     * in the file, simply return 0.
     *
     * @param word whose occurrences we are counting
     * @param filepath of the file
     * @return the number of occurrences
     */
    public int totalOccurrencesOfWordInFile(String word, String filepath) {
        
        
        
//         word = word.toLowerCase();

            // If the word exists in the map
    if (occMap.containsKey(word.toLowerCase())) {
        // If the specified filepath exists for the word
        if (occMap.get(word.toLowerCase()).containsKey(filepath)) {
            // Return the number of occurrences of the word in the specified file
            return occMap.get(word.toLowerCase()).get(filepath).size();
        }
    }
    return 0;  // Return 0 if word or filepath not found
//            word = word.toLowerCase();
//
//        int totalOccurrencesOfWordInFile = 0;
//        
//         for (Map.Entry<String, TreeMap<String, TreeSet<FilePosition>>> entry : occMap.entrySet()) {
//            
//         if (occMap.containsKey(word)) {
//
//             if (occMap.get(word).containsKey(filepath)) {
//
//                totalOccurrencesOfWordInFile = occMap.get(word).get(filepath).size();
//        }
//    }
//              
////            if (word.equals(entry.getKey())) {
////                if (occMap.get(word).containsKey(filepath)) {
////                    for (TreeSet<FilePosition> pos : entry.getValue().values()){
////                        totalOccurrencesOfWordInFile += pos.size();
////                 }
////             }
////            }
//            
//        }
//        // TODO: Implement me!!!
//        return totalOccurrencesOfWordInFile;

    }

    /**
     *
     * @return a string representation of the contents of the occurrence map
     */
public String toString() {
    StringBuilder sb = new StringBuilder();

    for (Map.Entry<String, TreeMap<String, TreeSet<FilePosition>>> entry : occMap.entrySet()) {
        int totalOccurrences = 0;

        for (TreeSet<FilePosition> positions : entry.getValue().values()) {
            totalOccurrences += positions.size();
        }

        sb.append("\"").append(entry.getKey()).append("\" has ").append(totalOccurrences).append(" occurrence(s):\n");

        for (Map.Entry<String, TreeSet<FilePosition>> fileEntry : entry.getValue().entrySet()) {
            for (FilePosition pos : fileEntry.getValue()) {
            sb.append("   ( file: \"").append(fileEntry.getKey()).append("\"; ").append(pos).append(" )\n");
            }
        }
    }

    return sb.toString();
    
  }

}





