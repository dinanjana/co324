/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Project1;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
//import sun.rmi.runtime.Log;

/**
 *
 * @author Dinanajana
 */
public class MultiCastClient {
    
static MulticastSocket aSocket = null;
static String groupAddress = "203.0.113.0";
final static int PORT = 4466;
BlockingQueue<byte[]> queue;  
    
public BlockingQueue<byte[]>  getQueue(){

    return queue;
}

public void run(){
    byte [] buf = new byte[1024];

    try {
        aSocket = new MulticastSocket(PORT);
        InetAddress group = InetAddress.getByName(groupAddress);
        aSocket.joinGroup(group);
        
        
            
            try{
                while(!Thread.interrupted()){
            
                DatagramPacket pckt = new DatagramPacket(buf,buf.length);
            
                aSocket.receive(pckt);
            
                queue.add(pckt.getData());
                }
            }
            catch(SocketException e){
                
                aSocket.leaveGroup(group);
                aSocket.close();
                
            
            }
        
        
        
        
    }catch (IOException ex) {
        
        Logger.getLogger(MultiCastClient.class.getName()).log(Level.SEVERE, null, ex);
        System.out.print("Multiclassclient");
    }finally{
    
    
        aSocket.close();
    
    }

}

}

