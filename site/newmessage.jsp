<FORM METHOD=POST ACTION="page.jsp?pageurl=sendmessage.jsp">
From: <%=session.getAttribute("loginname")%><br>
To: <INPUT TYPE=TEXT NAME=toname size=50><br>
Subject: <INPUT TYPE=TEXT NAME=subject size=130><br>
<INPUT TYPE=HIDDEN NAME=attachcard value=0>
<INPUT TYPE=HIDDEN NAME=replyto value=-1>

Message:<br>
<TEXTAREA name=message rows=20 cols=60>Insert your message here</TEXTAREA>
<INPUT TYPE=SUBMIT value="Send Message">
</FORM>



