package main.webapp;

import java.io.File;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
 
@Path("/pdf")
public class PDFService {
 
	private static final String FILE_PATH = "h:\\xyz.pdf";
 
	@POST
	@Path("/get")
	@Produces("application/pdf")
	public Response getFile(@FormParam("json") String pjson) {
        
		System.out.println("JSON Received is : "+pjson);
		File file = new File(FILE_PATH);
     
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
				"attachment; filename=xyz.pdf");
		return response.build();
 
	}
 
}