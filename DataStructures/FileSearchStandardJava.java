import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * File Indexing, Search and Concordance
 * 
 * Write a program that given a directory on your computer, will scan all text
 * documents (txt, html, ...) in that directory (and all its subdirectories). It
 * should scan several hundred documents.
 * 
 * - First, create an index so that you can efficiently find all files
 * containing a given query string. <br/>
 * - Second, index the documents so that you can do searches for documents that
 * have that word, that word, but not that word. <br/>
 * - Finally, support concordance queries: given a word, find all occurrences
 * with their immediate contexts.
 * 
 * This would be the ACM ConsoleProgram.
 * 
 * @author Sneha Parihar
 */
public class FileSearchStandardJava {
	// List or Map should go here (instance variable):

	private Map<String, List<String>> dataMap = new HashMap<String, List<String>>();
	private Set <String> fileNameSet = new HashSet<String>();
	private Map<String, File> fileMap = new TreeMap<String, File>();

	/**
	 * Constructor. This is where execution starts.
	 */
	@SuppressWarnings("resource")
	public FileSearchStandardJava() {
		// ask the user where to start		
		 System.out.print("Enter directory to search (e.g. /home/ralph or C:\\): ");
		 Scanner sc = new Scanner(System.in); 
		 String searchDirectory = sc.nextLine();
		 
		iterateThroughDirectory(searchDirectory);

		System.out.println("Enter the text to search");
		Scanner textScanner = new Scanner(System.in);
		String queryText = textScanner.nextLine();
		findMatchingFileNames(queryText);
		findMatchingFiles(queryText);
		try {
			showConcordanceResults(queryText);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * In this method you iterate through all the files in a given directory.
	 * Here you should fill your Map for file search with data.
	 * 
	 * @param searchDirectory
	 */
	private void iterateThroughDirectory(String searchDirectory) {
		System.out.println("Searching directory: " + searchDirectory);
		
		File directory = new File(searchDirectory);
		File[] dirs = directory.listFiles();
		for (int i = 0; i < dirs.length; i++) {
			if (dirs[i].isFile()) {
				String fileName = dirs[i].getName();
				String fileExtension = fileName
						.substring(fileName.length() - 3);
				if (fileExtension.equals("txt") || fileExtension.equals("tml")) {
					
					System.out.println("Searching file "+ fileName);
					
					readFileContents(dirs[i]);
					fileMap.put(fileName, dirs[i]);
				}
			}
			else
				iterateThroughDirectory(dirs[i].getAbsolutePath());
		}
	}

	/**
	 * Read the content of the given file and fill the search Map with the
	 * respective data.
	 * 
	 * @param file
	 */
	private void readFileContents(File file) {
		try {
			List<String> dbList = new ArrayList<String>();
			BufferedReader br = new BufferedReader(new FileReader(file));
			while (true) {
				String words = br.readLine();
				if (words == null)
					break;				
				StringTokenizer st = new StringTokenizer(words);
				String token;
				while (st.hasMoreTokens()) {
					token = st.nextToken();
					if (!dbList.contains(token)) {
						dbList.add(token);
					}
				}
			}
			dataMap.put(file.getName(), dbList);
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/** This function gives all the files having filenames containing the query string
 	 *  @param query - the query string entered by the user
	 */
	private void findMatchingFileNames(String query) {
		System.out.println("File names containing the search text \"" + query + "\" are: ");
		for (String fileName : dataMap.keySet()) 
		{
			if(fileName.indexOf(query) >= 0){
				System.out.println(fileName);
				break;
			}
		}
		
	}

	/** This function gives all the files containing words which matches the query string
 	 *  @param query - the query string entered by the user
	 */
	private void findMatchingFiles(String query) {
		System.out.println("\nFiles containing the search text \"" + query + "\" are: ");
		for (Map.Entry<String, List<String>> entry : dataMap.entrySet()) {
			String fileName = entry.getKey();
			for (String value : entry.getValue()) {
				if (value.equals(query)) {
					System.out.println(fileName);
					fileNameSet.add(fileName);
					break;
				}
			}
		}
	}
	
	/** This function gives all the statements in the file containing the query string
 	 *  @param query - the query string entered by the user
	 */
	private void showConcordanceResults(String query) throws IOException {
		System.out.println("\nConcordance query results are:");
		for(String fileName : fileNameSet){
			System.out.println("Filename: " + fileName);
			File file = fileMap.get(fileName);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String newChar = "";
			int num = 0;
			String result = "";
			while (true) {
				String words = br.readLine();
				if (words == null){
					break;	
				}
				else {
					if(words.indexOf(query) >= 0){
						newChar = "\"" + query + "\"";
						String newWords = words.replace(query, newChar);
						result += newWords;
						result += "\n";
						num++;
					}
				}
			}
			System.out.println("Occurence in the file " + num);
			if(num > 0){
				System.out.println(result);
			}
			br.close();
		}
	}

	
	/**
	 * You do not need to change anything here.
	 * 
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		FileSearchStandardJava fsj = new FileSearchStandardJava();
	}

}
