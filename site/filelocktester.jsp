<%@ page import="java.nio.*"%>
Attempting to get lock for d:\test.htm
<% 
	File file = new File("d:/test.htm");
	FileChannel channel = new RandomAccessFile(file, "rw").getChannel();
	FileLock lock = channel.lock();
%>
Locking successful
