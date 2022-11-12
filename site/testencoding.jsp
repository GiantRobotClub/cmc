<% String defaultEncoding = new java.io.InputStreamReader(
              new java.io.ByteArrayInputStream(new byte[0])).getEncoding(); 

 String encoding2 = System.getProperty("file.encoding");%>

 <%=defaultEncoding %>
 <%=encoding2 %>