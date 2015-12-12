package ie.gmit.sw.runner;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ie.gmit.sw.cipher.VigenereBreaker;

/**
 * Servlet implementation class CrackerHandler
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/CrackerHandler" })
public class CrackerHandler extends HttpServlet {
	private String remoteHost = null;
	private static long jobNumber = 0;
	
	private static final long serialVersionUID = 1L;
       
	public void init() throws ServletException {
		ServletContext ctx = getServletContext();
		remoteHost = ctx.getInitParameter("RMI_SERVER"); //Reads the value from the <context-param> in web.xml
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrackerHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		notReady(req, resp);
		
		try {
			ready(req, resp);
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void ready(HttpServletRequest req, HttpServletResponse resp) throws IOException, NotBoundException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		// get data
		int keyLen = Integer.parseInt(req.getParameter("frmMaxKeyLength"));
		String cipherText = req.getParameter("frmCypherText");
		VigenereBreaker vb = (VigenereBreaker) Naming.lookup("rmi://localhost:1099/cipher-service");
		// lookup stuff
//		String deWordTest = "JNORDDBENCAWUINQMZWTVAIVWINV";
//		int keyLenTest = 3;
		
		// test stuff
//		String testWord = "ANTIDISESTABLISHMENTARIANISM";
//		String testKey = "jav";
//		String decryptedWord = vb.decrypt(encryptedWord, testKey.length());
		
		//System.out.println("Encrypting word: " + testWord + " with the key: " + testKey);
		//String encryptedWord = vb.encrypt(testWord, testKey);

		
		
		System.out.println("Decrypting word: " + cipherText + " with a key length of: " + keyLen);
		String data[] = vb.decrypt(cipherText, keyLen);
		System.out.println("Decryption complete.");
		String decryptedWord = data[0];
		String keyWord = data[1];
		

//		String result = vb.decrypt(deWord, 3);
		System.out.print("encrypted word is: " + cipherText);
		System.out.print("\tdecrypted word is: " + decryptedWord);
		System.out.println("\tkey word is: " + keyWord);
		
		
		out.print("<html>");	
		out.print("<head>");
		out.print("<title>Rmi Cipher Service</title>");
		
//		Bootstrap
		out.print("<!-- Bootstrap --> <!-- Latest compiled and minified CSS --> <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" integrity=\"sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7\" crossorigin=\"anonymous\"> <!-- Optional theme --> <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css\" integrity=\"sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r\" crossorigin=\"anonymous\"> <!-- Latest compiled and minified JavaScript --> <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js\" integrity=\"sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS\" crossorigin=\"anonymous\"></script>");
		out.print("</head>");
		out.print("<body>");

		out.print("<div class=\"container\">");
		
			out.print("<div class=\"page-header\">");
				out.print("<h1>Task Complete <small>yahoo</small></h1>");
				out.print("<p>Your task is now complete</p><br><br>");
			out.print("</div>");
			
//			out.print("<H1>Results:</H1>");	
			out.print("<div class=\"row\">");

				out.print("<div class=\"col-lg-6\">");
					out.print("<H3>Input data:</H3>");
					out.print("<p>");
					out.print("decryption word: " + cipherText + "<br>");
					out.print("key length: " + keyLen + "<br><br>");
					out.print("</p>");
				out.print("</div>"); // element
				
				out.print("<div class=\"col-lg-6\">");
					out.print("<H3>Output data:</H3>");
					out.print("<p>");
					out.print("decrypted word: " + decryptedWord + "<br>");
					out.print("key word: " + keyWord + "<br>");
					out.print("</p>");
				out.print("</div>"); // element
			
			out.print("</div>"); // row
		out.print("</div>"); // container
		
		out.print("</body>");	
		out.print("</html>");	
	}
	
	private void notReady(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		
		int maxKeyLength = Integer.parseInt(req.getParameter("frmMaxKeyLength"));
		String cypherText = req.getParameter("frmCypherText");
		String taskNumber = req.getParameter("frmStatus");


		out.print("<html><head><title>Distributed Systems Assignment</title>");		
		out.print("</head>");		
		out.print("<body>");
		
		if (taskNumber == null){
			taskNumber = new String("T" + jobNumber);
			jobNumber++;
			//Add job to in-queue
		}else{
			//Check out-queue for finished job
		}
		
		
		
		out.print("<H1>Processing request for Job#: " + taskNumber + "</H1>");
		out.print("<div id=\"r\"></div>");
		
		
		out.print("RMI Server is located at " + remoteHost);
		out.print("<P>Maximum Key Length: " + maxKeyLength);		
		out.print("<P>CypherText: " + cypherText);
		out.print("<P>This servlet should only be responsible for handling client request and returning responses. Everything else should be handled by different objects.");
		out.print("<P>Note that any variables declared inside this doGet() method are thread safe. Anything defined at a class level is shared between HTTP requests.");				


		out.print("<P> Next Steps:");	
		out.print("<OL>");
		out.print("<LI>Generate a big random number to use a a job number, or just increment a static long variable declared at a class level, e.g. jobNumber");	
		out.print("<LI>Create some type of a message request object from the maxKeyLength, cypherText and jobNumber.");	
		out.print("<LI>Add the message request object to a LinkedList or BlockingQueue (the IN-queue)");			
		out.print("<LI>Return the jobNumber to the client web browser with a wait interval using <meta http-equiv=\"refresh\" content=\"10\">. The content=\"10\" will wait for 10s.");	
		out.print("<LI>Have some process check the LinkedList or BlockingQueue for message requests ");	
		out.print("<LI>Poll a message request from the front of the queue and make an RMI call to the Vigenere Cypher Service");			
		out.print("<LI>Get the result and add to a Map (the OUT-queue) using the jobNumber and the key and the result as a value");	
		out.print("<LI>Return the cyphertext to the client next time a request for the jobNumber is received and delete the key / value pair from the Map.");	
		out.print("</OL>");	
		
		out.print("<form name=\"frmCracker\">");
		out.print("<input name=\"frmMaxKeyLength\" type=\"hidden\" value=\"" + maxKeyLength + "\">");
		out.print("<input name=\"frmCypherText\" type=\"hidden\" value=\"" + cypherText + "\">");
		out.print("<input name=\"frmStatus\" type=\"hidden\" value=\"" + taskNumber + "\">");
		out.print("</form>");								
		out.print("</body>");	
		out.print("</html>");	
		
		out.print("<script>");
		out.print("var wait=setTimeout(\"document.frmCracker.submit();\", 10000);");
		out.print("</script>");
				
		/*-----------------------------------------------------------------------     
		 *  Next Steps: just in case you removed the above....
		 *-----------------------------------------------------------------------
		 * 1) Generate a big random number to use a a job number, or just increment a static long variable declared at a class level, e.g. jobNumber
		 * 2) Create some type of a "message request" object from the maxKeyLength, cypherText and jobNumber.
		 * 3) Add the "message request" object to a LinkedList or BlockingQueue (the IN-queue)
		 * 4) Return the jobNumber to the client web browser with a wait interval using <meta http-equiv="refresh" content="10">. The content="10" will wait for 10s.
		 * 4) Have some process check the LinkedList or BlockingQueue for "message requests" 
		 * 5) Poll a "message request" from the front of the queue and make an RMI call to the Vigenere Cypher Service
		 * 6) Get the result and add to a Map (the OUT-queue) using the jobNumber and the key and the result as a value
		 * 7) Return the cyphertext to the client next time a request for the jobNumber is received and delete the key / value pair from the Map.
		 */
		
		//You can use this method to implement the functionality of an RMI client
		
	}
	
//	protected void doCipher() throws MalformedURLException, RemoteException, NotBoundException{
//		VigenereBreaker vb = (VigenereBreaker) Naming.lookup("rmi://localhost:1099/cipher-service");
//		
//		String[] data = vb.decrypt("JNORDDBENCAWUINQMZWTVAIVWINV", 3);
//		String decryptedWord = data[0];
//		String keyWord = data[1];
//		
//		System.out.println("result is: " + decryptedWord);
//	}

}
