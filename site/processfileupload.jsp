<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page import="org.apache.commons.fileupload.DiskFileUpload"%>
<%@ page import="org.apache.commons.fileupload.FileItem"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.io.File"%>
<% String name = session.getAttribute("loginname").toString(); %>

<% 	webrunner.cardmaster.CardmasterUser user = webrunner.cardmaster.CardmasterData.loadUser(name);
		
		boolean test = true;
		if (user.readExtraA("avataradmin") == null) test = false;
		if (test) if (user.readExtraA("avataradmin").equals("yes")) test = true; else test = false;
		
		if (!test) if (user.readExtraA("admin") == null) return;
		if (!test) if (!user.readExtraA("admin").equals("yes")) return; else test=true;
	if (test) {

//String folder = request.getParameter("foldername");
//	System.out.println(folder);
        //System.out.println("Content Type ="+request.getContentType());

        DiskFileUpload fu = new DiskFileUpload();
        // If file size exceeds, a FileUploadException will be thrown
        fu.setSizeMax(1000000);
		String folder= "";
        List fileItems = fu.parseRequest(request);
        Iterator itr = fileItems.iterator();

        while(itr.hasNext()) {
          FileItem fi = (FileItem)itr.next();

          //Check if not form field so as to only handle the file inputs
          //else condition handles the submit button input
          if(!fi.isFormField()) {
			  if (fi.getName() == null) continue;
			  if (fi.getName().length()== 0) continue;
           long size = fi.getSize();
            //System.out.println(fi.getOutputStream().toString());
            File fNew= new File(webrunner.cardmaster.CardmasterData.SITEFOLDER + folder, fi.getName());
			fi.write(fNew);
			%> <%=fi.getName()%> Uploaded to <%= fNew.getAbsolutePath()%>
			<br>
			Filesize:<%=size%><br>
			<%
				webrunner.cardmaster.CardmasterLogManager.WriteLog(name + " uploaded file " + fi.getName() + " ("+size+")");
            
          }
          else {
          
			if (fi.getFieldName().equals("foldername")) {
				folder = fi.getString();	
			}
          }
        }
%>
Upload Complete<br>
<a href="page.jsp?pageurl=admin.jsp">Return to Administration</a><% } else { %> You are not an admin or avatar admin, sorry. <% } %>