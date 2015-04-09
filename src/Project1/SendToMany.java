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
import java.net.UnknownHostException;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dinanajana
 */
public class SendToMany extends Thread {
    
static MulticastSocket aSocket = null;
static String groupAddress = "203.0.113.0";
final static int PORT = 4466;
BlockingQueue<byte[]> queue;


public SendToMany(BlockingQueue<byte[]> queue){

    this.queue = queue;
}

public void run(){

   
        try{
        
            byte [] buf = new byte[1024];
            buf = queue.take();
            InetAddress group = InetAddress.getByName(groupAddress);
            
            DatagramPacket packet;
            
            packet = new DatagramPacket(buf, buf.length, group, PORT);
            
            aSocket.send(packet);
        
        } catch (InterruptedException ex) {
            Logger.getLogger(SendToMany.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(SendToMany.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
        Logger.getLogger(SendToMany.class.getName()).log(Level.SEVERE, null, ex);
    } finally{
            
            aSocket.close();
        }
    
    
    


}
    
}
