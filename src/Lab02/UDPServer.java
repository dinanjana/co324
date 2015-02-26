//package Lab02;

// UDPServer.java: A simple UDP server program.

//E/11/133
//Code is modified to calculate throughput and
//number of packets recieved that are not
//in order

import java.net.*;
import java.io.*;


public class UDPServer {
    private static DatagramSocket aSocket = null;
    final static int PORT = 1234;
    final static int BUFSIZE = 512;
    
    
    public static void main(String args[]) {
        int noOfPackets = 0; //added
        int notInOrder = 0 ;
        long startTime = System.currentTimeMillis();
        try {
            
            aSocket = new DatagramSocket(PORT);
			
            byte[] bufferRecieve = new byte[BUFSIZE];
            //byte[] msg = new byte[BUFSIZE];
            
            
            DatagramPacket recievePacket = new DatagramPacket(bufferRecieve, BUFSIZE);
                    
                for(int j = 1;j <= 1000;j++){
			
                    
			aSocket.receive(recievePacket);
	
                        aSocket.setSoTimeout(300);
                            
			System.out.println("Recive Packet : " + new String(recievePacket.getData()));
			
                        String msg;                                     //This block will identify
                        msg = new String(recievePacket.getData());      //which packets
                                                                        //are not in order
                        msg = msg.trim();
                        
                        msg = msg.split(":")[0];
                        
                        int index = Integer.parseInt(msg);
                                               
                        if ( j != index ){
                            
                            System.out.println(j + "th packet is not in order" + recievePacket.getData()[0] );
                            notInOrder ++;
                        
                        }
			DatagramPacket replyPacket
                        = new DatagramPacket(recievePacket.getData(), recievePacket.getLength(), recievePacket.getAddress(), recievePacket.getPort());

                        aSocket.send(replyPacket);
                        
                        noOfPackets++;
                        
                        
                }
        } catch (SocketException e) {
            
            System.out.println("Socket: " + e.getMessage());
            
        } catch (IOException e) {
            
            System.out.println("IO: " + e.getMessage());
            
            double thruput;
            thruput = throughputcalculator( noOfPackets , startTime);
            
            System.out.println("Number of packets recieved :" + noOfPackets);
            
            System.out.println("Throughput :" + thruput);
            
            System.out.println("Number of packets not in order :" + notInOrder);
            
            
            
        } finally {
            
            if (aSocket != null) {
                
                aSocket.close();
                
                
                
            }
        }
    }
    public static double throughputcalculator( int pckts,long startTime){
    
        long currTime = System.currentTimeMillis();
    
        return  ((double)pckts/(currTime - startTime - 300));
    }
}
