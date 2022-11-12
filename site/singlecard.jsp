
<%@ page import="java.util.StringTokenizer" %>

<%

webrunner.cardmaster.CardmasterServerCard card = (webrunner.cardmaster.CardmasterServerCard)(request.getAttribute("theCard"));

%>



<% 
boolean extend = card.picture.startsWith("extended_");
String ex = "";
if (extend) ex = "ex";
StringTokenizer tokenizer = new StringTokenizer(card.cardtext, "$");

StringTokenizer tokenizer2 = new StringTokenizer(card.cardtext, "$");

String cardtext = ""; 
while (tokenizer.hasMoreTokens()) cardtext = cardtext + tokenizer.nextToken() + "<br>";



if (extend) {if (tokenizer2.hasMoreTokens()) cardtext = tokenizer2.nextToken() ; }
			cardtext = cardtext.replaceAll("\\(D\\)","<img src=images/dmana.gif>");
			cardtext = cardtext.replaceAll("\\(L\\)","<img src=images/lmana.gif>");
			cardtext = cardtext.replaceAll("\\(G\\)","<img src=images/gmana.gif>");
			cardtext = cardtext.replaceAll("\\(>\\)","<img src=images/abilitypointer.gif>");
			cardtext = cardtext.replaceAll("\\(Z\\)","<img src=images/abilitydizzy.gif>");
			
String cardback = "cardback_";
if (card.background != 0) cardback = cardback + card.background + "_";
cardback = cardback + "l_" + card.colorcode + ".jpg";

%>
<%String cardtype = "";
String unique = "";
if (card.unique) unique = "unique ";
if (card.typecode.equals("m")) cardtype = "Call " + unique + card.mtype;
else if (card.typecode.equals("e")) {
	String effectword = "Effect";
	if (card.mtype != null) {
		effectword = "Effect";
		if (!card.mtype.equals("none")) {
			effectword = card.mtype;
		}
	}
	cardtype = "Create " + unique + " "+ effectword;
}
else if (card.typecode.startsWith("d")) {
	String effectword = "Modifier";
	if (card.mtype != null) {
		effectword = "Modifier";
		if (!card.mtype.equals("none")) {
			effectword = card.mtype;
		}
	}
	String dtype = "entity";
	if (card.typecode.endsWith("m")) dtype = "monster";
if (card.typecode.endsWith("e")) dtype = "effect";

	cardtype = "Attach " + unique + " "+ effectword + " to " + dtype;
}
else if (card.typecode.equals("l")) cardtype = "Move to Location";
else if (card.typecode.equals("s")) cardtype = "Cast Spell";
%>
<%String color = "black";
  if (card.colorcode ==2) color = "orange";
  else if (card.colorcode ==4) color = "black";
  else if (card.colorcode ==8) color = "#444444";
  else if (card.colorcode ==2+4) color = "#00bcbc";
  else if (card.colorcode ==2+8) color = "#dddddd";
  else if (card.colorcode ==4+8) color = "black";
  else if (card.colorcode ==2+4+8) color =  "black";
%>
<DIV class=singlecard style="background-image:url(images/<%=cardback%>); color:<%=color%>">
<DIV class=price>
<%if (card.Dcost > 0) { %><img src="images/dmana.gif"><%=card.Dcost%><%}%>
<%if (card.Lcost > 0) { %><img src="images/lmana.gif"><%=card.Lcost%><%}%>
<%if (card.Gcost > 0) { %><img src="images/gmana.gif"><%=card.Gcost%><%}%>
<%if (card.Gcost == 0 && card.Lcost == 0 && card.Dcost == 0) { %>Zero<%}%>
</DIV>
<% if (card.typecode.equals("m") || card.typecode.equals("e")) {%><DIV class=sac>
<%if (card.Dsac > 0) { %><img src="images/dmana.gif"><%=card.Dsac%><%}%>
<%if (card.Lsac > 0) { %><img src="images/lmana.gif"><%=card.Lsac%><%}%>
<%if (card.Gsac > 0) { %><img src="images/gmana.gif"><%=card.Gsac%><%}%>
<%if (card.Gsac == 0 && card.Lsac == 0 && card.Dsac == 0) { %>Zero<%}%>
</DIV>
<%}%>
<DIV class=name><%=card.name%></DIV>

<IMG class=cardimage<%=ex%> src="cardpics/<%=card.picture%>.gif">

<DIV class=cardtype<%=ex%>>
<%=cardtype%>
</DIV>
<DIV class=cardtext<%=ex%>>
<%=cardtext%>
</DIV>
<DIV class=stats>
<% if (card.typecode.equals("m")) { %>
<%=card.attack%>/<%=card.lifepoints%>
<% } else if (card.typecode.equals("e")) { %>
<%if(card.attack >=0) {%> <%=card.attack%><%}%>
<%if(card.lifepoints >=0) {%> <%=card.lifepoints%><%}%>

<% } %>
</DIV>
<div class=speed>
<% if (card.typecode.equals("s") || card.ability) { %>
<img src="images/speed_<%=card.speed%>.gif">
<% }%>
</div>
<div class=exp>
<img src="images/exp_<%=card.expansioncode%>.gif" width=18 height=18>
</div>

</DIV>
