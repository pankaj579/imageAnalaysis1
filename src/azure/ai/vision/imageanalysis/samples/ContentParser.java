package azure.ai.vision.imageanalysis.samples;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ContentParser {
    public static void extractPDF(String endpoint, String key, String filename) throws IOException {
        File newFile
                = new File("C:/sample code/azure-ai-vision-sdk-main/azure-ai-vision-sdk-main/samples/java/image-analysis/" + filename + ".pdf");
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
        String imageName = filename + ".png";
        ImageIO.write(
                img, "JPEG",
                new File("C:/sample code/azure-ai-vision-sdk-main/azure-ai-vision-sdk-main/samples/java/image-analysis/" + imageName));
        System.out.println(
                "Image has been extracted successfully");

        // Closing the PDF document
        pdfDocument.close();

        Samples.analyzeImageFromFileNew(Secrets.getEndpoint(), Secrets.getKey(), imageName);
    }

    public static void extractImage(String endpoint, String key, String filename) throws IOException {
        Samples.analyzeImageFromFileNew(Secrets.getEndpoint(), Secrets.getKey(), filename);
    }
}
