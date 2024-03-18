package azure.ai.vision.imageanalysis.samples;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ContentParser {
    public static void extractImage(String endpoint, String key) throws IOException {
        File newFile
                = new File("C:/sample code/azure-ai-vision-sdk-main/azure-ai-vision-sdk-main/samples/java/image-analysis/document1Pager.pdf");
        PDDocument pdfDocument = Loader.loadPDF(newFile);

        // PDFRenderer class to be Instantiated
        // i.e. creating it's object
        PDFRenderer pdfRenderer
                = new PDFRenderer(pdfDocument);

        // Rendering an image
        // from the PDF document
        // using BufferedImage class
        BufferedImage img = pdfRenderer.renderImage(0);
        // Writing the extracted
        // image to a new file
        ImageIO.write(
                img, "JPEG",
                new File("C:/sample code/azure-ai-vision-sdk-main/azure-ai-vision-sdk-main/samples/java/image-analysis/document1Pager.png"));
        System.out.println(
                "Image has been extracted successfully");

        // Closing the PDF document
        pdfDocument.close();
    }
}
