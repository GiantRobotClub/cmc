ECHO COPY code\cardmasterclient\classes "C:\Program Files\Apache Group\Tomcat 4.1\webapps\ROOT"
MKDIR site\WEB-INF\classes\
MKDIR site\WEB-INF\classes\webrunner
MKDIR site\WEB-INF\classes\webrunner\cardmaster\
ECHO COPY code\cardmasterserver\classes\webrunner\cardmaster\ "C:\Program Files\Apache Group\Tomcat 4.1\webapps\ROOT\WEB-INF\classes\webrunner\cardmaster\"
ECHO COPY code\cardmasterclient\classes site\
COPY code\cardmasterserver\classes\webrunner\cardmaster\ site\WEB-INF\classes\webrunner\cardmaster\
pause