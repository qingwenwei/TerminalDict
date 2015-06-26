import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;


public class WordbookManager {
	private String wordbook = "wordbook.xml";
	private String wordList = "wordList.txt";
	private String rootLocation = "/Users/qingwenwei/experimental_area/";
	
	public void readConfigFile(){
		
	}
	
	public void insert(long offset, String content) throws IOException {
		File file = new File(rootLocation + wordbook);
		File fileTemp = new File(rootLocation + wordbook + "~");
		RandomAccessFile r = new RandomAccessFile(file, "rw");
		RandomAccessFile rtemp = new RandomAccessFile(fileTemp, "rw");
		long fileSize = r.length();
		FileChannel sourceChannel = r.getChannel();
		FileChannel targetChannel = rtemp.getChannel();
		sourceChannel.transferTo(offset, (fileSize - offset), targetChannel);
		sourceChannel.truncate(offset);
		r.seek(offset);
		r.write(content.getBytes("UTF-8"));
		long newOffset = r.getFilePointer();
		targetChannel.position(0L);
		sourceChannel.transferFrom(targetChannel, newOffset, (fileSize - offset));
		sourceChannel.close();
		targetChannel.close();
		
		//delete the temp file
	}
	
	public boolean hasWord(String word){
		try {
			String currentLine;	 
			BufferedReader br = new BufferedReader(new FileReader(rootLocation + wordList));	
			while ((currentLine = br.readLine()) != null) {	
				if(currentLine.contains(word) && currentLine.contains("<word>")){
					return true;
				}
			}
			
		} catch(IOException e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void createWordList(){
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
		/*
		 * insert text test
		 */
		WordbookManager wb = new WordbookManager();
//		wb.insert(11, "<item>你好</item>\n");
		
		
		/*
		 * check if an item exists
		 */
//		wb.createWordList();
		
		
		System.out.println(wb.hasWord("yoyo"));
	}
}
