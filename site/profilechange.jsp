
<%
String name =  (String)(session.getAttribute("loginname"));

String profile = request.getParameter("proftext").replace(':','^').replace('%',' ');

String quote = request.getParameter("quote").replace(':','^').replace('%',' ');
String image = request.getParameter("image").replace(':','^').replace('%',' ');
String location = request.getParameter("location").replace(':','^').replace('%',' ');

if (quote.equals("")) quote = " ";
if (image.equals("")) image = " ";
if (location.equals("")) location = " ";
if (profile.equals("")) profile = " ";

String text = "location";
String dataa = location;
String datab = "xxx";
webrunner.cardmaster.CardmasterData.userpatch(name,"extr",0,text + "%" + dataa + "%" + datab + "%");

text="profile";
dataa = quote;
datab = profile;
webrunner.cardmaster.CardmasterData.userpatch(name,"extr",0,text + "%" + dataa + "%" + datab + "%");

text="image";
dataa = image;
datab = "xxx";
webrunner.cardmaster.CardmasterData.userpatch(name,"extr",0,text + "%" + dataa + "%" + datab + "%");


%>
Profile Set.
