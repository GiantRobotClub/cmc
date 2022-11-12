
<%
String name =  (String)(session.getAttribute("loginname"));

String profile = " ";

String quote = " ";
String image =" ";
String location =" ";

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
Profile reset.
