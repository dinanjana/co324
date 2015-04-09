package votes;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class VotingServer extends HttpServlet {

  static int allNoOfVotes = 0;
  int currentNoofVotesforSelectedTeam = 0;
  static Hashtable noOfInstances = new Hashtable();

  List<String> playersList = new ArrayList<String>();


  public void init() throws ServletException {
            
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse res) 
                               throws ServletException, IOException {

    res.setContentType("text/html");

    PrintWriter out = res.getWriter();

    String team = req.getParameter("team");

    currentNoofVotesforSelectedTeam++;

    allNoOfVotes++;

    noOfInstances.put(this, this);

    playersList.add(req.getParameter("name"));

    out.println("<html>");
    out.println("<head>");
    out.println("<title>Team " + team + "</title>");
    out.println("</head>");
    out.println("<body>");

    out.println("<h1>Number of people who have voted : "+ allNoOfVotes + "</h1>");

    out.println("<h2>There are currently " + noOfInstances.size() + " instances.<h2>");//Number of instances made from this class.
    
    out.println("<h2>Number of votes for "+ team + " team : "+ currentNoofVotesforSelectedTeam + "</h2>"); 

    out.println("<h5>People voted for team " + team + " </h5>");

      for (String s : playersList) {

        out.println(s);
        out.println("<br/>");

      }

    out.println("</body>");
    out.println("</html>");


  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
      
    doGet(req, res);
    
      //implement doPost method to handle post requests.    
  }
}