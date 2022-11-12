<jsp:useBean id="getdeck" class="cmcbeans.GetArticleBean" scope="page"/>
<jsp:setProperty name="getdeck" property="*"/>
<jsp:setProperty name="getdeck" property="name" value="webrunner" />
<HTML>
<BODY>
<%= getarticle.getArticle()  %>
</BODY>
</HTML>
