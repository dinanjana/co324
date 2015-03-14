//package fancontrol;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class FanController extends Thread {

	public static final int PORT = 5321;

	final Socket socket;
	
	public FanController(Socket s) {
		this.socket = s;
	}
        enum FanState{
        
        OFF{
            FanState eval(String cmd){
            
                switch (cmd) {
                    case "inc":
                        return MED;
                    case "dec":
                        return OFF;
                }
                return OFF;
            }
        }
        ,MED{
            FanState eval (String cmd){
                switch (cmd) {
                    case "inc":
                        return HI;
                    case "dec":
                        return OFF;
                }
                
                return MED;
            }
        }
        ,HI{
            FanState eval (String cmd){
                switch (cmd) {
                    case "inc":
                        return HI;
                    case "dec":
                        return MED;
                }
                return HI;
            
            } 
        
        };
    abstract FanState eval(String cmd);
    }

	public void run()  {
		try (Scanner sin = new Scanner(socket.getInputStream() ); 
				PrintStream sout = new PrintStream (socket.getOutputStream() ); ) {
			FanState fs = FanState.OFF;
			while (!interrupted() && sin.hasNext()) {
				fs = fs.eval( sin.nextLine() );
				sout.println(fs);
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static void main(String[] args) throws IOException {
		try(ServerSocket ss =  new ServerSocket(PORT) ) {
			while (true)
				new FanController(
						ss.accept()).start();
		} 	
	}
}
