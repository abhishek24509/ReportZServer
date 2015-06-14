package main.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class GenPDF{

public static void drawTable(PDPage page, PDPageContentStream contentStream,
	    float y, float margin, String[][] content) throws IOException {
	    final int rows = content.length;
	    final int cols = content[0].length;
	    final float rowHeight = 20f;
	    final float tableWidth = page.findMediaBox().getWidth()-    (2*margin);
	    final float tableHeight = rowHeight * rows;
	    final float colWidth = tableWidth/(float)cols;
	    final float cellMargin=5f;

	    float nexty = y ;
	    for (int i = 0; i <= rows; i++) {
	        contentStream.drawLine(margin,nexty,margin+tableWidth,nexty);
	        nexty-= rowHeight;
	    }

	    //draw the columns
	    float nextx = margin;
	    for (int i = 0; i <= cols; i++) {
	        contentStream.drawLine(nextx,y,nextx,y-tableHeight);
	        nextx += colWidth;
	    }

	    //now add the text
	    contentStream.setFont(PDType1Font.HELVETICA_BOLD,12);

	    float textx = margin+cellMargin;
	    float texty = y-15;
	    for(int i = 0; i < content.length; i++){
	        for(int j = 0 ; j < content[i].length; j++){
	            String text = content[i][j];
	            contentStream.beginText();

	            contentStream.moveTextPositionByAmount(textx,texty);

	            contentStream.drawString(text);
	            contentStream.endText();
	            textx += colWidth;
	        }
	        texty-=rowHeight;
	        textx = margin+cellMargin;
	    }
	}

	public static void main(String[] args) throws IOException, COSVisitorException {
	    PDDocument doc = new PDDocument();
	    PDPage page = new PDPage();
	    doc.addPage( page );
	    PDPageContentStream contentStream = new PDPageContentStream(doc, page);
	    PDFont font = PDType1Font.HELVETICA_BOLD;
	    String[][] content = {
	        {"Name"," Time "},
	        {"HTC","01:25"},
	        {"Samsung Tab2","05:30"}
	    } ;
	    contentStream.beginText();
	    contentStream.setFont( font, 12 );
	    contentStream.moveTextPositionByAmount( 100, 700 );
	    contentStream.drawString("Heading");
	    contentStream.moveTextPositionByAmount( 200, 800 );
	    contentStream.drawString("Hello");
	    contentStream.endText();
	    drawTable(page, contentStream, 700, 100, content);
        
	    contentStream.close();
	    doc.save("h:\\xyz.pdf" );
	    System.out.println(encodePDF("h:\\xyz.pdf"));
	} 
	
	public static String encodePDF(String path){	        

	        File file = new File(path);
	        if (!file.exists())
	        {
	            System.out.println(path+" could not be found ");
	            return null;
	        }

	        FileInputStream fin = null;
	        try {
	            fin = new FileInputStream(file);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }

	        byte fileContent[] = new byte[(int) file.length()];

	        try {
	            fin.read(fileContent);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
          try {
			fin.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          byte[] encoded = Base64.encodeBase64(fileContent);
		return  new String(encoded);
	}
	
}