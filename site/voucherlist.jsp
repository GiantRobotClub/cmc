<%@ page import="java.util.Vector, java.util.ListIterator,java.sql.*"%>

<%String name = session.getAttribute("loginname").toString();%>

<%


if ( ( (String)request.getAttribute("admin") )  .equals("yes")) {%>
Calculating....
<%
String sql = "SELECT edname as name, COUNT( * ) as cnt FROM cmcextradata where edname like \"voucher-%\" GROUP BY edname"; //TODO	
	Vector codes = new Vector(0);
	Vector quant = new Vector(0);
	Class.forName("com.mysql.jdbc.Driver");
	String url = "jdbc:mysql://"+webrunner.cardmaster.CardmasterDatabase.dbhost+"/"+webrunner.cardmaster.CardmasterDatabase.dbname+
						"?user="+webrunner.cardmaster.CardmasterDatabase.dbuser+"&password="+webrunner.cardmaster.CardmasterDatabase.dbpass;
	Connection con = DriverManager.getConnection(url);
	Statement stmt = con.createStatement();
	ResultSet rs;
	rs = stmt.executeQuery(sql);
	
			while (rs.next()) {
				codes.add(rs.getString("name"));
				quant.add(rs.getString("cnt"));
				
			
			
			}
	
	rs.close();
	/*

	webrunner.cardmaster.CardmasterUser[] users = webrunner.cardmaster.CardmasterData.loadUserData();
	for (int i=0;i<users.length;i++) {
		if (users[i] == null) continue;
	webrunner.cardmaster.CardmasterUser user = webrunner.cardmaster.CardmasterData.loadUser(users[i].name);
		
		if (user.ed == null) continue; 
		webrunner.cardmaster.ExtraData[] ed = user.ed;
String lastvoucher = "";
		for (int j=0;j<user.ed.length;j++) {
		
			webrunner.cardmaster.ExtraData extradata = ed[j];
			//if (lastvoucher.equals(extradata.name)) continue;
			if (extradata.name.startsWith("voucher-")) {
				boolean found = false;
		
				for (int k=0;k<codes.size();k++) {
					if (((String)(codes.get(k))).equals(extradata.name)) {
						quant.set(k,new Integer(((Integer)(quant.get(k))).intValue() + 1));
						found = true;
						lastvoucher = extradata.name;

					}
				


				}

				if (!found) {
					codes.add(extradata.name);
					quant.add(new Integer(1));
					lastvoucher = extradata.name;

				}
			}
		}
	}
*/

%>
<br>
Complete.  Here's the results:<br>
<%
	webrunner.cardmaster.CardmasterLogManager.WriteLog(name + " views voucherlist...");
	for (int k=0;k<codes.size();k++) {
%> <%=codes.get(k)%> : <%=quant.get(k)%> <br>


<%
} %>


See, that wasn't so hard, was it?



	<% } %>