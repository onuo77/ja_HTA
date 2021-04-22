package day2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ByteStreamApp2 {

	public static void main(String[] args) throws IOException{
		
		URL url = new URL("https://t1.daumcdn.net/movie/fdba6f6c880f3ded79806297e35ee6316d06741d");
		
		InputStream in = url.openStream();
		FileOutputStream out = new FileOutputStream("src/day2/블랙위도우.jpg");
		
		BufferedInputStream bis = new BufferedInputStream(in);
		BufferedOutputStream bos = new BufferedOutputStream(out);
		
		int value = 0;
		while((value = bis.read()) != -1) {
			bos.write(value);
		}
		
		bos.close();
	}
}
