package http;
import java.net.ProtocolException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Request {
	public enum Method { HEAD, GET, PUT, POST, DELETE };
	
	static final Pattern 
		request = Pattern.compile("(\\w+) (\\S+) HTTP/\\d", Pattern.CASE_INSENSITIVE),
		sep = Pattern.compile("\\s*:\\s*");

	final Method method;
	final String url;
	
	public Request(Scanner in) throws ProtocolException {
		String req1 = in.nextLine();
		Matcher matcher = request.matcher(req1);
		
                if (!matcher.find())
			throw new ProtocolException("Illegal request");
                
                url = matcher.group(2);
            switch (matcher.group(1).toLowerCase()) {
                case "head":
                    method = Method.HEAD;
                    break;
                case "get":
                    method = Method.GET;
                    break;
                case "put":
                    method = Method.PUT;
                    break;
                case "post":
                    method = Method.POST;
                    break;
                case "delete":
                    method = Method.DELETE;
                    break;
                default:
                    method = null;
                    break;
            }
		
		
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(method).append('\t').append(url);
		
		/*for (Entry<String,String> e: headers.entrySet())
			buf.append('\n').append(e);*/
		
		return buf.toString();
	}
}
