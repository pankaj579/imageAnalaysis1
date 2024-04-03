package ms.audibles.imageanalysis;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ContentParser {
    public static String extractPDF(String endpoint, String key, String filename) throws IOException {
        File newFile
                = new File("C:/sample code/ms-audibles/image-analysis/" + filename + ".pdf");
        PDDocument pdfDocument = Loader.loadPDF(newFile);

        // PDFRenderer class to be Instantiated
        // i.e. creating it's object
        PDFRenderer pdfRenderer
                = new PDFRenderer(pdfDocument);


        BufferedImage img = pdfRenderer.renderImage(0);

        String imageName = filename + ".png";
        ImageIO.write(
                img, "JPEG",
                new File("C:/sample code/ms-audibles/image-analysis/" + imageName));
        System.out.println(
                "Image has been extracted successfully");

        // Closing the PDF document
        pdfDocument.close();

        return ImageProcessor.analyzeImageFromFileNew(Secrets.getEndpoint(), Secrets.getKey(), imageName);
    }

    public static String extractImage(String endpoint, String key, String filename) throws IOException {
        return ImageProcessor.analyzeImageFromFileNew(Secrets.getEndpoint(), Secrets.getKey(), filename);
    }
}
