Voucher Codes:<br>

Redemption Code:<br>
Redemption codes are available through various CMC promotions.  They are 15 upper-case letters and will be clearly marked.  Each of these codes is unique and can only be redeemed once system wide.<br>
<FORM METHOD=POST ACTION="page.jsp?pageurl=getredemptioncode.jsp">
<INPUT TYPE=TEXT name="vcode" value="AAAAAAAAAAAAAAA" length=15>
<INPUT TYPE=SUBMIT value="Submit Code">
</FORM>

<br><br>

If you find a CMC Voucher Code somewhere, (They start with VCH) you can enter it in here to get the redeemable bonus cards from that voucher, once.  After that you can't use it any more.<br>
<FORM METHOD=POST ACTION="page.jsp?pageurl=getcode.jsp">
<INPUT TYPE=TEXT name="vcode">
<INPUT TYPE=SUBMIT value="Submit Voucher Code">
</FORM>
<%String name = session.getAttribute("loginname").toString();%>
Please do not give out Voucher Codes to other players.  The whole point is to keep people looking=)<br><br>
You have already used the following Voucher Codes:<br>
<% webrunner.cardmaster.CardmasterUser user = webrunner.cardmaster.CardmasterData.loadUser(name); %>
<% for (int i=0;i<user.ed.length;i++) {
webrunner.cardmaster.ExtraData ed = user.ed[i];
	if (ed.name.startsWith("voucher-")) {
		String code = ed.name.substring(8,ed.name.length());
		%> <%=code%> <br><%

	}

} %>