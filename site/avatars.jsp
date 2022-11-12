Avatar Management<br>
<% String name = session.getAttribute("loginname").toString(); %>

<%webrunner.cardmaster.CardmasterUser user = webrunner.cardmaster.CardmasterData.loadUser(name);%>


<%if (user.readExtraA("avatar_basehead")== null) { 
	webrunner.cardmaster.CardmasterData.GiveBasicAvatars(name);
	
	
	%>
Building your default Avatar collection.  Please refresh this page until it loads properly.


<% } else { %>
Your current avatar: <br><img src="avatars/<%=name%>.png"><br>
If it's not current to the following data, or is a broken image, click "rebuild avatar file" at the bottom<br>
If you just made a change and it's not here yet, wait about 5 seconds, and refresh the page.<br>

<%webrunner.cardmaster.CardmasterAvatar avatar = new webrunner.cardmaster.CardmasterAvatar();
avatar.loadParts(name);
%><br>
<I>Background:</I> <%=avatar.background.partname%>  (<a href="page.jsp?pageurl=choosepart.jsp&type=background">Change</a>)  (<a href="page.jsp?pageurl=partstore.jsp&type=background">Store</a>)<br>
<br>
<I>Head:</I> <%=avatar.basehead.partname%>  (<a href="page.jsp?pageurl=choosepart.jsp&type=basehead">Change</a>)  (<a href="page.jsp?pageurl=partstore.jsp&type=basehead">Store</a>)<br>
<I>Body:</I> <%=avatar.basebody.partname%>  (<a href="page.jsp?pageurl=choosepart.jsp&type=basebody">Change</a>)  (<a href="page.jsp?pageurl=partstore.jsp&type=basebody">Store</a>)<br>
<I>Legs:</I> <%=avatar.baselegs.partname%>  (<a href="page.jsp?pageurl=choosepart.jsp&type=baselegs">Change</a>)  (<a href="page.jsp?pageurl=partstore.jsp&type=baselegs">Store</a>)<br>
<br>
<I>Shoes:</I> <%=avatar.shoes.partname%>  (<a href="page.jsp?pageurl=choosepart.jsp&type=shoes">Change</a>)  (<a href="page.jsp?pageurl=partstore.jsp&type=shoes">Store</a>)<br>
<I>Pants:</I> <%=avatar.pants.partname%>  (<a href="page.jsp?pageurl=choosepart.jsp&type=pants">Change</a>)  (<a href="page.jsp?pageurl=partstore.jsp&type=pants">Store</a>)<br>
<br>
<I>Shirt: </I><%=avatar.shirt.partname%>  (<a href="page.jsp?pageurl=choosepart.jsp&type=shirt">Change</a>)  (<a href="page.jsp?pageurl=partstore.jsp&type=shirt">Store</a>)<br>
<I>Jacket:</I> <%=avatar.jacket.partname%>  (<a href="page.jsp?pageurl=choosepart.jsp&type=jacket">Change</a>)  (<a href="page.jsp?pageurl=partstore.jsp&type=jacket">Store</a>)<br>
<I>Hands:</I> <%=avatar.hands.partname%>  (<a href="page.jsp?pageurl=choosepart.jsp&type=hands">Change</a>)  (<a href="page.jsp?pageurl=partstore.jsp&type=hands">Store</a>)<br>
<br>
<I>Hair:</I> <%=avatar.hair.partname%>  (<a href="page.jsp?pageurl=choosepart.jsp&type=hair">Change</a>)  (<a href="page.jsp?pageurl=partstore.jsp&type=hair">Store</a>)<br>
<I>Hat:</I> <%=avatar.hat.partname%>  (<a href="page.jsp?pageurl=choosepart.jsp&type=hat">Change</a>)  (<a href="page.jsp?pageurl=partstore.jsp&type=hat">Store</a>)<br>
<br>
<I>Front:</I> <%=avatar.front.partname%>  (<a href="page.jsp?pageurl=choosepart.jsp&type=front">Change</a>)  (<a href="page.jsp?pageurl=partstore.jsp&type=front">Store</a>)<br>
<I>Back:</I> <%=avatar.back.partname%>  (<a href="page.jsp?pageurl=choosepart.jsp&type=back">Change</a>)  (<a href="page.jsp?pageurl=partstore.jsp&type=back">Store</a>)<br>
<br>
<a href="page.jsp?pageurl=partstore.jsp&type=all">View Store of All Part Types</a>

<br><br>

<a href="page.jsp?pageurl=rebuildavatar.jsp">Rebuild Avatar File!</a> 

<% } %>