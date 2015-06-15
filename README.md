# ReportZServer
Online Report  Generation Tool


The intent to start developing ReportZServer is to provide developers a tool to design and generate report. 
The aim is to include features provided by Jasper/BIRT . 
It will enable developer to design report online with no additional tool needed to create report unlike JASPER/BIRT. 
In It's final stages this application will expose the API to be called to generate pdf/csv report.

UI has been developed using bootstrap framework and jquery. To create pdf Apache PDFBox has been used

Instruction to Run
Deploy the RemoteZServer.was in any of the application server. Tested in Weblogic/tomcat. In case of weblogic go to following url
http://localhost:7001/ReportZServer/

To design sample pdf click to pdf design @ nav-bar
To test data Source connection click on Include DataSource link on accordion
