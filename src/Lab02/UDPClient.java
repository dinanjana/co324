//package Lab02;

// UDPClient.java: A simple UDP client program.

import java.net.*;
import java.io.*;

public class UDPClient {

    static DatagramSocket aSocket = null;
    final static String data = "Hello there!";
    final static String hostAddress = "127.0.0.1";
    final static int serverPort = 1234;

    public static void main(String args[]) {

        try {

            DatagramSocket aSocket = new DatagramSocket();
            
            InetAddress aHost = InetAddress.getByName(hostAddress);
            
            for(int i = 1 ; i <= 500 ; i++){
                
                
                
                byte[] dataArray = (i + ":" + data).getBytes();

                DatagramPacket requestPacket = new DatagramPacket(dataArray, dataArray.length, aHost, serverPort);

                aSocket.send(requestPacket);

                byte[] buffer = new byte[512];

                DatagramPacket recievePacket = new DatagramPacket(buffer, buffer.length);

                aSocket.receive(recievePacket);

                System.out.println("Reply:" + new String(recievePacket.getData()));
            }
        } catch (SocketException e) {

            System.out.println("Socket: " + e.getMessage());

        } catch (IOException e) {

            System.out.println("IO: " + e.getMessage());

        } finally {

            if (aSocket != null) {

                aSocket.close();

            }
        }
    }
}
