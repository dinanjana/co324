package http;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Response {
	public enum Code {
		OK(200, "OK"),
		FORBIDDEN(403,"Forbidden"),	
		NOTFOUND(404, "Not Found");
		
		int code;
		String message;

		Code(int code, String message) {
			this.code = code;
			this.message = message;
		}
		
		@Override
		public String toString() {
			return Integer.toString(code) + " " + message;
		}
	}
	
	final static String version = "HTTP/1.0";
	final static String sep = "\r\n\r\n";
			
	final Code code;
	final Map<String,String> headers = new HashMap<String,String>();
	final StringBuffer body;
	
	public Response(Code code,  String body) {
		this.code = code;
		this.body = new StringBuffer(body);
	}
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(version).append(' ').append(code).
                        append("\nContent-Type:txt/html").append("\nContent-length:"+body.length());
		
		for (Entry<String,String> e: headers.entrySet())
			buf.append('\n').append(e);
		
		buf.append(sep).append(body);
		return buf.toString();
	}
}
