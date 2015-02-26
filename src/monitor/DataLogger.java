package monitor;



import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
//import Lab04.Reading;


public class DataLogger extends Thread {
	static final int PORT = 23456;
        final Socket socket ;
        //static DataLogger Lock;
        static final Object lock = new Object();
        final static ArrayList<String> data_log = new ArrayList<>() ;
    public DataLogger(Socket s){

        this.socket = s;


    }	
	public static void main(String[] args) throws IOException {
		try(ServerSocket ss =  new ServerSocket(PORT)){
                    
                    while(true){    
                        Socket socket = ss.accept();
                        new DataLogger(socket).start();
                    }
			
		}
	}
        @Override
        public void run(){
        
            try (DataInputStream sin = new DataInputStream(
		socket.getInputStream()); ) {
		//This should run in a separate thread
		while (true){ 
                    //synchronized(Lock){
                    
                    Reading r = new Reading(sin);
                    System.out.println(r);
                    //synchronized(lock){
                    data_log.add(r.toString());
                    //}
                    
                    
		}					
            }catch (IOException e1) {
            } 
        
        }
}
