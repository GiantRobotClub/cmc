<%@ page import ="java.sql.*" buffer="256kb" %>
<% String users = "A,AJDJ,Adam,Akira sanada,Amanda,Archon,Azrael,BN,BagpipePaul,Blade,Blank,Bob,Bosh,Brandon,Charon,Chris,ClickOutMets,DARKNESS,DEATH,DEMON,DOC,DXcalibur,Daedalus,Daniel,Dark,DarkMaster,Darkhan,Dave,David,DreamWalker,Dreamer,Eggman,Enigma,FeaR,Fighter,Fluffy,Greg,Helix,Hello,Ian,J,JYu,Jake,Janus,Jay294,Jeff,Jen,Jester,Jim,Joe,Josh,Kai,Kite,Krono,Langrisser,Lightstorm,Lucas,Lucifer,MIKE,MNC,Mark,Matt,Matthew,Michael,Nefshid,Neo,NobleKnight,Norman,Nosferatu,Pain,Plague,PlagueSquirrel,Quickfire,Rae,Ralph,Ranma,RastaMahata,Rath,Ricardo,SABIN,SCOTT,Sam,Shaun,Skie,Spike,Steve,Strike,Talen,Thanatos,Toad,Travis,Tyrael,Vlight,Wraith,ZERO,Zack,"; %>
<%
	Class.forName("com.mysql.jdbc.Driver");
	String url = "jdbc:mysql://"+webrunner.cardmaster.CardmasterDatabase.dbhost+"/"+webrunner.cardmaster.CardmasterDatabase.dbname+
						"?user="+webrunner.cardmaster.CardmasterDatabase.dbuser+"&password="+webrunner.cardmaster.CardmasterDatabase.dbpass;
	Connection con = DriverManager.getConnection(url);
	Statement stmt = con.createStatement();
	ResultSet rs;

	%>
<% java.util.StringTokenizer tokenizer = new java.util.StringTokenizer(users,",");
while (tokenizer.hasMoreTokens()) {
String name = tokenizer.nextToken();
webrunner.cardmaster.CardmasterUser user = webrunner.cardmaster.CardmasterData.loadUser(name);
if (user == null) continue;
name = user.name;
	%><BR><%=name%> <%
	String deckLoad = "SELECT * from "+webrunner.cardmaster.CardmasterDatabase.dbname+".cmcdecks AS t1"+
							  " where t1.owner = \""+name+"\" and " +
							" binary t1.owner != \""+name+"\";";
			rs = stmt.executeQuery(deckLoad);
	
			while (rs.next()) {

%>
	Deleting <%=rs.getString("owner")%>'s <%=rs.getInt("deckno")%>,
	<%webrunner.cardmaster.CardmasterData.userpatch(name,"rmdx",rs.getInt("deckno"),"x");%>
<%


			}
			rs.close();





 } 

 stmt.close();
 con.close();

 %>