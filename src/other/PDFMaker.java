package other;

/*import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
*/
import models.Alumno;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by maicol on 23/04/15.
 */
public class PDFMaker {

    /*public static final float[][] LEFT =
            {{36, 806, 36, 670, 108, 670, 108, 596, 36, 596, 36, 36},
                    {299, 806, 299, 484, 336, 484, 336, 410, 299, 410, 299, 36}};
    public static final float[][] RIGHT =
            {{296, 806, 296, 484, 259, 484, 259, 410, 296, 410, 296, 36},
                    {559, 806, 559, 246, 487, 246, 487, 172, 559, 172, 559, 36}};


    public PDFMaker(String filename, Alumno alumno) throws FileNotFoundException, DocumentException, IOException {
        Document document = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(filename));
        document.open();
        PdfContentByte pdfContentByte = pdfWriter.getDirectContent();
        Paragraph paragraph = new Paragraph(new Paragraph("Perfil de alumno"));
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setSpacingBefore(16);
        document.add(paragraph);

        BaseFont baseFont = BaseFont.createFont(BaseFont.COURIER_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
        pdfContentByte.setFontAndSize(baseFont, 12);
        pdfContentByte.beginText();
        pdfContentByte.setTextMatrix(50, 590);
        pdfContentByte.showText("Asdasd");
        pdfContentByte.setTextMatrix(50, 560);
        pdfContentByte.showText("lklhjkh");
        document.close();
    }
    */
}
