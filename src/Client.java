import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket socket = null;
		int packetsize=1024;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("zz.mp3");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		double nosofpackets=Math.ceil(((int) (new File("abc.mp3")).length())/packetsize);
		for(double i=0;i<nosofpackets+1;i++)
		{
		InputStream is = null;
		try {
			is = socket.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] mybytearray = new byte[packetsize];
		try {
			int bytesRead = is.read(mybytearray, 0,mybytearray.length );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Packet:"+(i+1));
		try {
			bos.write(mybytearray, 0,mybytearray.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
