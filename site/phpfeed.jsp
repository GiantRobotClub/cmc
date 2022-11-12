<%@ page import="java.io.*, java.net.*" %>

 <%
if (webrunner.cardmaster.CardmasterData.host.equals( "cmc.mrx.ca" ) ) {
URL url = new URL("http://cmcforums.mrx.ca:8000/" + request.getParameter("url"));
URLConnection urlConnection = url.openConnection();
	    DataInputStream dis;
	    String inputLine;

	    dis = new DataInputStream(urlConnection.getInputStream());
            while ((inputLine = dis.readLine()) != null) {
                %><%=inputLine%><%
            }
	    dis.close();
	}

	else {
	
		%>
<jsp:include page="news.htm"/>
<%
	
	
	}

%>
