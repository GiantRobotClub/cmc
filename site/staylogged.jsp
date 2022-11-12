<%
session.setAttribute("Stayloggedin","yes");
%>
<script language="javascript">
var sURL = unescape(window.location.pathname);
self.blur();
function refresh() {
self.blur();
window.location.href = sURL;
self.blur();
}
setTimeout( "refresh()", 600*1000 );
</script>