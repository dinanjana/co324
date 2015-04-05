package upload;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

enum MyHandler implements HttpHandler {
	GET {
                @Override
		public void handle(HttpExchange t) throws IOException {
	        String p = t.getRequestURI().getPath();
			Path path = Paths.get(ROOT, p);
	        
			try (InputStream in = Files.newInputStream(path);
			        OutputStream out = t.getResponseBody() ){
				//If we can determine the file's MIME type, set content-type
		        String type =  Files.probeContentType(path); 
				if (type!=null) {
					List l = new ArrayList();
					l.add(type);
					t.getRequestHeaders().put("Content-Type", l);
				}				
								 
		        t.sendResponseHeaders(200, Files.size(path));
                        copy(in, out);
			} catch (IOException e) {
		        t.sendResponseHeaders(400, -1);
				e.printStackTrace();
			}
	    }			
	},
	PUT {		
                @Override
		public void handle(HttpExchange t) throws IOException {
//	       TODO: complete this method
                    String p = t.getRequestURI().getPath();
                    
                    Path path = Paths.get(ROOT, p);
                    
                    try{
                    InputStream in = t.getRequestBody();
                    
                    OutputStream out;
                    out = Files.newOutputStream(path);
                    t.sendResponseHeaders(200, Files.size(path));                  
                    copy(in,out);
                    
                    }catch(IOException e){
                    t.sendResponseHeaders(400, -1);
                    
                    }
                    
                    
                    
                    
		}	
	};
	
	static final String ROOT ="C:\\Users\\Dinanajana\\Documents\\NetBeansProjects\\Client_Server\\src\\upload\\";
	
	static void copy(InputStream in, OutputStream out) throws IOException {
		int len; 
		byte[] buf = new byte[256];
		while (	(len = in.read(buf, 0, buf.length)) > 0)
			out.write(buf, 0, len);
	}
}