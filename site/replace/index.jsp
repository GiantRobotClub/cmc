<%
response.setStatus(response.SC_MOVED_PERMANENTLY);
        String redirectURL = "http://cmc.mrx.ca/";
        response.sendRedirect(redirectURL);
%>