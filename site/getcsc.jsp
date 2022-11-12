<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page import="org.apache.commons.fileupload.DiskFileUpload"%>
<%@ page import="org.apache.commons.fileupload.FileItem"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.io.FileReader"%>
<% String name = session.getAttribute("loginname").toString(); %>
<pre>
<% 	webrunner.cardmaster.CardmasterUser user = webrunner.cardmaster.CardmasterData.loadUser(name);
		String filename = request.getParameter("file");
		boolean test = true;
		
		 if (user.readExtraA("admin") == null) test = false;
		if (!test) if (!user.readExtraA("admin").equals("yes")) test = false; else test=true;
	if (test) {


BufferedReader input = new BufferedReader(new FileReader(webrunner.cardmaster.CardmasterData.DIRECTORY + "/" + filename + ".csc"));
String line = "";
while ((line = input.readLine()) != null) {
   out.println(line);
}
//output.flush();
input.close();
}

%>
</pre>