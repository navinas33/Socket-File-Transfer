package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class Receiver {
	public static void main(String[] args) {
		Receiver nioClient = new Receiver();
		SocketChannel socketChannel = nioClient.createChannel();
		nioClient.sendFile(socketChannel);

	}

	/**
	 * Establishes a socket channel connection
	 *
	 * @return
	 */
	public SocketChannel createChannel() {

		SocketChannel socketChannel = null;
		try {
			socketChannel = SocketChannel.open();
			String addr = "10.51.1.171";
			SocketAddress socketAddress = new InetSocketAddress(addr, 1500);
			//SocketAddress socketAddress = new InetSocketAddress("192.168.182.1", 1500);
			socketChannel.connect(socketAddress);
			System.out.println("Connected..Now sending the file");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return socketChannel;
	}

public void sendFile(SocketChannel socketChannel) {
	
	RandomAccessFile aFile = null;
	try {
	aFile = new RandomAccessFile("C:\\downloads\\newFile.mp4", "rw");
	ByteBuffer buffer = ByteBuffer.allocate(1024);
	FileChannel fileChannel = aFile.getChannel();
	while (socketChannel.read(buffer) > 0) {
	buffer.flip();
	fileChannel.write(buffer);
	buffer.clear();
	}
	Thread.sleep(1000);
	fileChannel.close();
	System.out.println("End of file reached..Closing channel");
	socketChannel.close();

	} catch (FileNotFoundException e) {
	e.printStackTrace();
	}
	
	
//RandomAccessFile aFile = null;
//try {
//File file = new File("G:\\Kanna Kaattu Podhum-Rekka.2016.1080p.HD-Rip.AvC[www.HdEncoders.com].mp4");
//aFile = new RandomAccessFile(file, "r");
//FileChannel inChannel = aFile.getChannel();
//ByteBuffer buffer = ByteBuffer.allocate(1024);
//while (inChannel.read(buffer) > 0) {
//buffer.flip();
//socketChannel.write(buffer);
//buffer.clear();
//}
//Thread.sleep(1000);
//System.out.println("End of file reached..");
//socketChannel.close();
//aFile.close();
//} catch (FileNotFoundException e) {
//e.printStackTrace();
//}
	
	
catch (IOException e) {
e.printStackTrace();
} catch (InterruptedException e) {
e.printStackTrace();
}

}

}