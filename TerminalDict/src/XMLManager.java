import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;


public class XMLManager {
	
	public void foo() throws IOException{
		File file = new File("/Users/qingwenwei/experimental_area/wordbook_test.xml");
		RandomAccessFile raFile = new RandomAccessFile(file,"rw");
		
		String newLine = "new line";
		
		raFile.seek(file.length()-12);
		raFile.writeBytes(System.getProperty("line.separator"));
		raFile.writeBytes(newLine);
		raFile.writeBytes(System.getProperty("line.separator"));
		raFile.writeBytes("</wordbook>");
		raFile.close();
		
	}
	
	public static void main(String args[]) throws IOException{
//		new XMLManager().foo();
		
		
		
	}
}
