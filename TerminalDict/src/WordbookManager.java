import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.Properties;


public class WordbookManager {
	private String config = "config.properties";
	private String wordbook = "wordbook.xml";
	private String wordList = "wordlist.txt";
	private String rootLocation = "";
	private long insertOffset = 11;	
	
	public WordbookManager(){
		rootLocation = getRootDir();
	}
	
	public void readConfig(){
		Properties prop = new Properties();
		InputStream input = null;
	 
		try {
	 
			input = new FileInputStream("config.properties");
	 
			// load a properties file
			prop.load(input);
	 
			// get the property value and print it out
			System.out.println(prop.getProperty("database"));
			System.out.println(prop.getProperty("dbuser"));
			System.out.println(prop.getProperty("dbpassword"));
	 
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	

	
	public String getRootDir(){
		File currentJavaJarFile = new File(WordbookManager.class.getProtectionDomain().getCodeSource().getLocation().getPath());   
		String currentJavaJarFilePath = currentJavaJarFile.getAbsolutePath();
		String currentRootDirectoryPath = currentJavaJarFilePath.replace(currentJavaJarFile.getName(), "");
		System.out.println("root dir: "+currentRootDirectoryPath);
		return currentRootDirectoryPath;
	}
	
	
	
	public void addWord(Word word) throws IOException {
		//refresh the word list
		syncWordList();
				
		//check if this word exists
		if(hasWord(word.getWord())){
			System.out.println("[Exists] The word <" + word.getWord() + "> already exists in the wordbook.");
			return;
		}
		
		String content = word.toXMLElement();
		File file = new File(rootLocation + wordbook);
		File fileTemp = new File(rootLocation + wordbook + "~");
		RandomAccessFile r = new RandomAccessFile(file, "rw");
		RandomAccessFile rtemp = new RandomAccessFile(fileTemp, "rw");
		long fileSize = r.length();
		FileChannel sourceChannel = r.getChannel();
		FileChannel targetChannel = rtemp.getChannel();
		sourceChannel.transferTo(insertOffset, (fileSize - insertOffset), targetChannel);
		sourceChannel.truncate(insertOffset);
		r.seek(insertOffset);
		r.write(content.getBytes("UTF-8"));
		long newOffset = r.getFilePointer();
		targetChannel.position(0L);
		sourceChannel.transferFrom(targetChannel, newOffset, (fileSize - insertOffset));
		sourceChannel.close();
		targetChannel.close();
		
		//refresh the word list
		syncWordList();
		System.out.println("[Saved] The word <" + word.getWord() + "> has been saved in the wordbook.");
	}
	
	public boolean hasWord(String word){
		try {
			String currentLine;	 
			BufferedReader br = new BufferedReader(new FileReader(rootLocation + wordList));	
			while ((currentLine = br.readLine()) != null) {	
				if(currentLine.contains(word)){
					return true;
				}
			}
			
		} catch(IOException e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	private void syncWordList(){
		try {
			File file = new File(rootLocation + wordList);
			if (!file.exists()) {
				file.createNewFile();
			}
			
			String currentLine;	 
			BufferedReader br = new BufferedReader(new FileReader(rootLocation + wordbook));	
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			while ((currentLine = br.readLine()) != null) {	
				if(currentLine.contains("</word>")){
					String word = currentLine.replace("<word>", "").replace("</word>", "");
					bw.write(word+"\n");
				}
			}
			
			bw.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	public static void main(String args[]) throws Exception{
		WordbookManager wb = new WordbookManager();
	}
}
