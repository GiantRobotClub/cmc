Cardmaster Conflict Administration panel
<% String name = session.getAttribute("loginname").toString(); %>
<% boolean admin = false;
boolean avataradmin = false;
boolean implementer = false;

	webrunner.cardmaster.CardmasterUser user = webrunner.cardmaster.CardmasterData.loadUser(name);
		
		
		if (user.readExtraA("avataradmin") != null) 
		if (user.readExtraA("avataradmin").equals("yes")) avataradmin = true;
		
		 if (user.readExtraA("admin") != null)
		 if (user.readExtraA("admin").equals("yes")) admin = true;
		 if (user.readExtraA("implementer") != null)
		 if (user.readExtraA("implementer").equals("yes")) implementer = true;
%>
<br><br><u>Avatar Administration Section</u><br>
<% if (avataradmin) { %>
<a href="page.jsp?pageurl=avatarupload.htm">Upload Avatar Part Graphic File</a><br>
<br>
<a href="page.jsp?pageurl=parteditor.jsp">Edit avatar part data.</a><br><br>
Give Avatar Part:
<FORM METHOD=POST ACTION="page.jsp?pageurl=giveavatarpart.jsp">
<INPUT TYPE=TEXT name="to" value="<%=name%>">
<INPUT TYPE=SUBMIT value="Select Part">
</FORM>
<% } else { %> For Avatar Admin Eyes Only <% } %>

<br><br><u>Implementer Section</u><br>
<% if (implementer) { %>


Donation Gift: <br>
<FORM METHOD=POST ACTION="page.jsp?pageurl=donationgift.jsp">
Donation Amount: <INPUT TYPE=TEXT name="amount" value="5"><br>
Name: <INPUT TYPE=TEXT name="toname" value="<%=name%>"><br>

<INPUT TYPE=SUBMIT value="Choose Card">
</FORM>

<br><br>

<a href="page.jsp?pageurl=voucherlist.jsp">View All Voucher Codes (and count)</a>

<% } else { %>
For Webrunner's eyes only
<% } %>

<br><br><u>General Administration Section</u><br>
<% if (admin) { %>
<BR><BR>

<a href="page.jsp?pageurl=cardpicupload.htm">Upload Cardpic</a><br>
<a href="page.jsp?pageurl=cscupload.htm">Upload CSCs</a><br>
<a href="getcsc.jsp?file=cards">Get cards.csc</a><br>
<a href="getcsc.jsp?file=abilities">Get abilities.csc</a><br>
<a href="getcsc.jsp?file=abilities_gigas">Get abilities_gigas.csc</a><br>

<br><br>
Full User Data:
<FORM METHOD=POST ACTION="page.jsp?pageurl=fulluserdata.jsp">
Name: <INPUT TYPE=TEXT name="name" value="<%=name%>"><br>
<INPUT TYPE=SUBMIT value="View Data"></FORM>
<br><br>

Extra Data Set:<br>
<FORM METHOD=POST ACTION="page.jsp?pageurl=edPatch.jsp">
Name: <INPUT TYPE=TEXT name=name><br>
Dataname: <INPUT TYPE=TEXT name=text><br>
Dataa: <INPuT TYPE=TEXT name=dataa><br>
Datab: <INPUT TYPE=TEXT name=datab><br>
<INPUT TYPE=SUBMIT value="Set Extra Data">
</FORM>
<br>
EXTRA DATA FIELD NAMES:<br>
Set A and B to what's in the brackets<br>
admin (yes)<br>
avataradmin (yes)<br>
donor (yes)<br>
banned (yes)<br> 
tourneywins (number of wins)
<br><br>

Manual User Patch (Careful with this. Make sure there's values in everything even if they're not needed)<br>
Note: Removing the deck doesnt delete it.  Adding the deck will remove it from it's current owner.
<br><br>
<FORM METHOD=POST ACTION="page.jsp?pageurl=userPatch.jsp">
 <SELECT NAME="code">
 <OPTION VALUE="pass">Set Password (text)</OPTION>
 <OPTION VALUE="addp">Add Points (num)</OPTION>
 <OPTION VALUE="remp">Remove Points (num)</OPTION>
 <OPTION VALUE="stpo">Set Points (num)</OPTION>
 <OPTION VALUE="setp">Set Primary Deck (num)</OPTION>
 <OPTION VALUE="addd">Add Deck Number (num) </OPTION>
 <OPTION VALUE="remd">Remove Non-Existing Deck (num)</OPTION>
 <OPTION VALUE="rmdx">Remove Existing Deck (num)</OPTION>
 <OPTION VALUE="addw">Add a Win</OPTION>
 <OPTION VALUE="addl">Add a Loss</OPTION>
 <OPTION VALUE="setw">Set Wins (number)</OPTION>
 <OPTION VALUE="setl">Set Losses (number) </OPTION>
 <OPTION VALUE="verf">Verify Account</OPTION>
 <OPTION VALUE="logn">Set Last Login to Now</OPTION>
 <OPTION VALUE="encp">Force Binary Password</OPTION>
 <OPTION VALUE="adav">Add Avatar Part Number (num)</OPTION>
 <OPTION VALUE="seav">Set Avatar Part (text) (num)</OPTION>

 </SELECT><br>
 Name: <INPUT TYPE=TEXT name=name value="name"><br>
 Text: <INPUT TYPE=TEXT name=text value="0"><br>
 Numb: <INPUT TYPE=TEXT name=num value="0"><br>
<INPUT TYPE=SUBMIT value="Patch!"><Br><br>
</FORM>
<br><br>
Give Card to a Deck<br>
<FORM METHOD=POST ACTION="page.jsp?pageurl=GiveCard.jsp">
Deck Number: <INPUT TYPE=TEXT name=to><br>
Card ID: <INPuT TYPE=TEXT name=card><br>
<INPUT TYPE=SUBMIT value="Give Card">
</FORM>
<br><br>

Give A Card to a User (creates new deck)<br>
<FORM METHOD=POST ACTION="page.jsp?pageurl=CreateDeckFromCard.jsp">
Card Number: <INPUT TYPE=TEXT name=card><br>
Card Quantity: <INPuT TYPE=TEXT name=no><br>
Deck Name: <INPuT TYPE=TEXT name=deckname><br>
Player name: <INPuT TYPE=TEXT name=toname><br>
<INPUT TYPE=SUBMIT value="Give Card">
</FORM>
<br><br>



Take Card from a Deck<br>
<FORM METHOD=POST ACTION="page.jsp?pageurl=TakeCard.jsp">
Deck Number: <INPUT TYPE=TEXT name=from><br>
Card ID: <INPuT TYPE=TEXT name=card><br>
<INPUT TYPE=SUBMIT value="Take Card">
</FORM>
<br><br>
Give new Prebuilt Deck<br>
<FORM METHOD=POST ACTION="page.jsp?pageurl=giveDeck.jsp">
Deck Number: <INPUT TYPE=TEXT name=d><br>
Name: <INPuT TYPE=TEXT name=n><br>
<INPUT TYPE=SUBMIT value="Give Deck">
</FORM>

<br><br>
Copy old data user (for users who haven't been properly brought over to the new database).
<FORM METHOD=POST ACTION="page.jsp?pageurl=copyOldUserToNew.jsp">
Old name: <INPUT TYPE=TEXT name=oldname><br>
new name: <INPUT TYPE=TEXT name=newname><br>
<INPUT TYPE=SUBMIT value="Copy user (and possibly rename)">
</FORM>
<br><Br>
<a href="page.jsp?pageurl=listlogs.jsp">View logs</a>
<br><br>

<a href="page.jsp?pageurl=userlist.jsp">View Userlist</a>
<% } else { %> For Admin Eyes Only <% } %>