package ie.gmit.sw.runner;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.RequestDispatcher;
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
	public static String remoteHost = null;
	private static long jobNumber = 0;
	private static final long serialVersionUID = 1L;
      
	private BlockingQueue reqQueue;
	private Map<Long, Result> resMap;
	
	// we will create a new thread for each request and that thread will run to completion.
	// we will have one thread for talking to the RMI program, this will run infinitely.
	
	public void init() throws ServletException {
		ServletContext ctx = getServletContext();
		remoteHost = ctx.getInitParameter("RMI_SERVER"); //Reads the value from the <context-param> in web.xml
	
		initializeDataStructures();
		startConsumerThread();
	}

	private void initializeDataStructures() {
		reqQueue = new ArrayBlockingQueue<Task>(10);
		resMap = new ConcurrentHashMap<Long, Result>();
	}

	private void startConsumerThread() {
		TaskConsumer consumer = new TaskConsumer(reqQueue, resMap);
		Thread consumerThread = new Thread(consumer);
		consumerThread.start();
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
		
		// keyword (encrypt/decrypt)
		String keyWord = req.getParameter("frmKeyWord");
		
		// keylen (crack only)
		String maxKeyLenStr = req.getParameter("frmMaxKeyLen");
		int maxKeyLen;
		if (maxKeyLenStr != null) {
			maxKeyLen = Integer.parseInt(maxKeyLenStr);
		} else{
			maxKeyLen = -1;
		}
		
		// cipherText
		String inputText = req.getParameter("frmInputText");
		
		// task num
		String taskNumStr = req.getParameter("frmTaskNum");
		long taskNum = Integer.parseInt(taskNumStr);
		
		// get type
		String taskTypeStr = req.getParameter("frmTaskType");
		int taskTypeNum = Integer.parseInt(taskTypeStr);
	
		
		TaskType taskType;
		
		switch(taskTypeStr){
			case "0":
				taskType = TaskType.ENCRYPT;
				break;
			case "1":
				taskType = TaskType.DECRYPT;
				break;
			case "2":
				taskType = TaskType.CRACK;
				break;
			default:
				// undefined is an error
				taskType = TaskType.UNDEFINED;
				break;
		}
		
		System.out.println("Form Input Data:\t" + "maxKeyLength: " + maxKeyLen + "\tInputText: " + inputText + "\ttaskNumber: " + taskNum + "\tTask Type: " + taskType + "\tKeyWord: " + keyWord + "\n");
		
		if(taskType != TaskType.UNDEFINED){
			if (taskNum == -1){	// new request, add to queue
				enqueueTask(req, resp, maxKeyLen, inputText, taskType, keyWord);
			}else{						// old request, check result map
				checkTask(req, resp, maxKeyLen, inputText, taskNum, taskTypeNum, keyWord);
			}
		}else{
			// forward them to a index page
			RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
			rd.forward(req, resp);
		}

	}

	private void checkTask(HttpServletRequest req, HttpServletResponse resp, int maxKeyLen, String cipherText,
			long taskNum, int taskTypeNum, String keyWord) throws ServletException, IOException {
		System.out.println("Checking task: " + taskNum);
		//Check out-queue for finished job
		
		if(resMap.containsKey(taskNum)){
			taskReady(req, resp, taskNum);
		} else{
			taskNotReady(req, resp, maxKeyLen, cipherText, taskNum, taskTypeNum, keyWord);
		}
	}

	private void taskNotReady(HttpServletRequest req, HttpServletResponse resp, int maxKeyLen, String cipherText,
			long taskNum, int taskTypeNum, String keyWord) throws ServletException, IOException {
		System.out.println("Task #" + taskNum + " is on not on the map yet.");
		
		// forward them to a waiting page
		RequestDispatcher rd = req.getRequestDispatcher("task-wait.jsp?frmMaxKeyLen=" + maxKeyLen + "&frmInputText=" + cipherText +  "&frmTaskNum=" + taskNum + "&frmTaskType=" + taskTypeNum  + "&frmKeyWord=" + keyWord);
		rd.forward(req, resp);
	}

	private void taskReady(HttpServletRequest req, HttpServletResponse resp, long taskNum)
			throws ServletException, IOException {
		System.out.println("Task #" + taskNum + " is on map.");
		Result result = resMap.get(taskNum);
		System.out.println("Adding result data to new page. Data: " + result.toString());
		// forward them to a finished page
		RequestDispatcher rd = req.getRequestDispatcher("task-finish.jsp?keyWord=" + result.getKeyWord() + "&keyLen=" + result.getKeyLen() + "&resultText=" + result.getResultText() + "&taskNum=" + result.getTaskNum() + "&inputText=" + result.getInputText() + "&maxKeyLen=" + result.getMaxKeyLenGiven() + "&taskType=" + result.getTaskType());
		System.out.println("forwarding...");
		rd.forward(req, resp);
	}

	private void enqueueTask(HttpServletRequest req, HttpServletResponse resp, int maxKeyLen, String inputText, TaskType taskType, String keyWord)
			throws ServletException, IOException {
		jobNumber++;
		Task task;
		
		if (maxKeyLen != -1) {
			task = new Task(inputText, maxKeyLen, jobNumber, taskType);
		}else{
			task = new Task(inputText, keyWord, jobNumber, taskType);
		}
		
		System.out.println("keyWord: " + keyWord);
		System.out.println("Created new task: " + task.toString());
		
		//Add job to in-queue
		TaskProducer producer = new TaskProducer(reqQueue, task);
		new Thread(producer).start();
		
		// forward them to a waiting page
		RequestDispatcher rd = req.getRequestDispatcher("task-wait.jsp?frmMaxKeyLen=" + task.getMaxKeyLen() + "&frmCipherText=" + task.getInputText() +  "&frmTaskNum=" + task.getTaskNum());
		rd.forward(req, resp);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void customServletCodeExample(HttpServletRequest req, HttpServletResponse resp) throws IOException, NotBoundException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		out.print("<html>");	
		out.print("<head>");
		out.print("<title>Rmi Cipher Service</title>");
		
//		Bootstrap
		out.print("<!-- Bootstrap --> <!-- Latest compiled and minified CSS --> <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" integrity=\"sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7\" crossorigin=\"anonymous\"> <!-- Optional theme --> <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css\" integrity=\"sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r\" crossorigin=\"anonymous\"> <!-- Latest compiled and minified JavaScript --> <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js\" integrity=\"sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS\" crossorigin=\"anonymous\"></script>");
		
//		my css styles
		out.print("<link rel=\"stylesheet\" href=\"styles/styles.css\"> <link rel=\"stylesheet\" href=\"styles/sticky-footer.css\">");
		
		out.print("</head>");
		out.print("<body>");
		
//		navbar
		out.print("<!-- Fixed navbar --> <nav class=\"navbar navbar-default navbar-fixed-top\"> <div class=\"container\"> <!-- <div class=\"navbar-header\"> <span class=\"glyphicon glyphicon-lock\"></span> </div> --> <ul class=\"nav navbar-nav\"> <li><a href=\"index.jsp\">Home</a></li> <li><a href=\"about.jsp\">About</a></li> <li><a href=\"contact.jsp\">Contact</a></li> </ul> </div> </nav></p> &nbsp; </p> &nbsp; </p>");
		
		
		out.print("<div class=\"container\">");
		
			out.print("<div class=\"page-header\">");
				out.print("<h1>Task Complete <small></small></h1>");
				out.print("<p>Your task is now complete</p><br><br>");
			out.print("</div>");
			
//			out.print("<H1>Results:</H1>");	
			out.print("<div class=\"row\">");

				out.print("<div class=\"col-lg-6\" style=\"word-wrap: break-word;\">");
					out.print("<H3>Input data:</H3>");
					out.print("<p>");
//					out.print("<b>encrypted word:</b><br>" + cipherText + "<br><br>");
//					out.print("<b>key length:</b> " + keyLen + "<br><br>");
					out.print("</p>");
				out.print("</div>"); // element
				
				out.print("<div class=\"col-lg-6\" style=\"word-wrap: break-word;\">");
					out.print("<H3>Output data:</H3>");
					out.print("<p>");
//					out.print("<b>decrypted word:</b><br>" + decryptedWord + "<br><br>");
//					out.print("<b>key word:</b> " + keyWord + "<br>");
					out.print("</p>");
				out.print("</div>"); // element
			
			out.print("</div>"); // row
		out.print("</div>"); // container
		
//		footer
		out.print("<footer class=\"footer\"> <div class=\"container\"> <p class=\"text-muted\"> <a style=\"color: inherit\" target=\"_blank\" href=\"http://ronanconnolly.ie/\">Ronan Connolly ©</a> <span class=\"pull-right\"><a style=\"color: inherit\" target=\"_blank\" href=\"https://github.com/RonanC\">Github</a></span> </p> </div> </footer>");
		
		out.print("</body>");	
		out.print("</html>");	
	}
	
	
	private void jhServletCodeExample(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		
		int maxKeyLength = Integer.parseInt(req.getParameter("frmMaxKeyLength"));
		String cypherText = req.getParameter("frmCypherText");
		String taskNumber = req.getParameter("frmStatus");


		out.print("<html><head><title>Distributed Systems Assignment</title>");		
		out.print("</head>");		
		out.print("<body>");
		
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
	}
	

	
	
}
