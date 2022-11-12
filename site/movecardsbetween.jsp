<script type="text/javascript">
function changeValue(deck, number, amt, max) {
if (max == null) max = 100;
	var newvalue =  ( parseInt(document.getElementById("input-" + deck + "-" + number).value)+amt);
	if (newvalue < 0) newvalue = 0;
	if (newvalue > max) newvalue = max;
	document.getElementById("input-" + deck + "-" + number).value = newvalue;
	document.getElementById(deck + "-" + number).innerHTML = newvalue;
}
</script>
<noscript>This isn't going to work without Javascript, I'm afraid.</noscript>
<% 


int deck1num = Integer.parseInt(request.getParameter("d1"));
int deck2num = Integer.parseInt(request.getParameter("d2"));
webrunner.cardmaster.CardmasterServerCard[] cards = webrunner.cardmaster.CardmasterData.loadCardData();

String name = session.getAttribute("loginname").toString();
webrunner.cardmaster.CardmasterLibrary deck1 = new webrunner.cardmaster.CardmasterLibrary(deck1num);
webrunner.cardmaster.CardmasterLibrary deck2 = new webrunner.cardmaster.CardmasterLibrary(deck2num);

webrunner.cardmaster.CardmasterUser user = webrunner.cardmaster.CardmasterData.loadUser(name);
int error = 0;
if (!user.hasDeck(deck1num) || !user.hasDeck(deck2num)) error = 1;
if (deck1num == deck2num) error = 2;
 
if (error == 0) {
	int nextcard = -1;
	int quantity = 1;
%>
<form action="page.jsp?pageurl=movecardsbetweenaction.jsp" method="POST">
<input type="hidden" name="d1" value="<%=deck1num%>" />
<input type="hidden" name="d2" value="<%=deck2num%>" />
<input type="submit" value="Move Cards!" />
<br />
<br />
<div style="overflow:auto;width:250;height:600;float:left;">

<%
	
	for (int c = 0; c<deck1.numcards; c++) {
		int i = c;
		if (c == deck1.numcards-1) nextcard = -1;
		else {nextcard = c+1; }
	
		if (nextcard != -1 && (deck1.getCard(c) == deck1.getCard(nextcard))) {
			quantity++;
			
			
		}
		else {
			request.setAttribute("theCard",cards[deck1.getCard(i)]);%>

<DIV class=cardbox>
<jsp:include page="singlecard.jsp"/>
<DIV class=carddata>Quantity: <%=quantity%><br \>
<div style="font: 15pt Arial">
<a href="javascript:changeValue(1,<%=deck1.getCard(i)%>,-1,<%=quantity%>);">[-]</a><span id="1-<%=deck1.getCard(i)%>">0</span><a href="javascript:changeValue(1,<%=deck1.getCard(i)%>,1,<%=quantity%>);">[+]</a>

<input type="hidden" id="input-1-<%=deck1.getCard(i)%>" name="card-1-<%=deck1.getCard(i)%>" value="0" />
</div>
Rarity: <%=101-cards[deck1.getCard(i)].printed%>
</div></div>
<script type="text/javascript">
	document.getElementById("input-1-<%=deck1.getCard(i)%>").value = "0";
	document.getElementById("1-<%=deck1.getCard(i)%>").innerHTML = "0";


	</script>



			<%quantity = 1;
		}
}

%>

</div>

<div style="overflow:auto;width:250;height:600;">
<div>

<%
		 nextcard = -1;
	 quantity = 1;
	
	for (int c = 0; c<deck2.numcards; c++) {
		int i = c;
		if (c == deck2.numcards-1) nextcard = -1;
		else {nextcard = c+1; }
	
		if (nextcard != -1 && (deck2.getCard(c) == deck2.getCard(nextcard))) {
			quantity++;
			
			
		}
		else {
			request.setAttribute("theCard",cards[deck2.getCard(i)]);%>

<DIV class="cardbox">
<jsp:include page="singlecard.jsp"/>
<DIV class=carddata>Quantity: <%=quantity%><br \>
<div style="font: 15pt Arial">
<a href="javascript:changeValue(2,<%=deck2.getCard(i)%>,-1,<%=quantity%>);">[-]</a><span id="2-<%=deck2.getCard(i)%>">0</span><a href="javascript:changeValue(2,<%=deck2.getCard(i)%>,1,<%=quantity%>);">[+]</a> 

<input type="hidden" id="input-2-<%=deck2.getCard(i)%>" name="card-2-<%=deck2.getCard(i)%>" value="0" />
</div>
Rarity: <%=101-cards[deck2.getCard(i)].printed%><br>
</div></div>
<script type="text/javascript">
	document.getElementById("input-2-<%=deck2.getCard(i)%>").value = "0";
	document.getElementById("1-<%=deck2.getCard(i)%>").innerHTML = "0";


	</script>
			<%quantity = 1;
		}
}

%>

</div>
</div>

<br \>

</form>

<%


}

else {
%>
Error code <%=error%>
<%


}
%>