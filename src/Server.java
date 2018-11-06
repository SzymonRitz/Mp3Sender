import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		ServerSocket server = null;
		Socket client = null;
		try {
			server = new ServerSocket(0);
		} catch (IOException e) {
			System.out.println("Could not listen");

		}
		// TODO Auto-generated method stub
		File myFile = new File("abc.mp3");
		{
			Socket sock = null;
			try {
				sock = server.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int packetsize = 1024;
			double nosofpackets = Math.ceil(((int) myFile.length()) / packetsize);
			BufferedInputStream bis = null;
			try {
				bis = new BufferedInputStream(new FileInputStream(myFile));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (double i = 0; i < nosofpackets + 1; i++) {
				byte[] mybytearray = new byte[packetsize];
				try {
					bis.read(mybytearray, 0, mybytearray.length);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Packet:" + (i + 1));
				OutputStream os = null;
				try {
					os = sock.getOutputStream();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					os.write(mybytearray, 0, mybytearray.length);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					os.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}
