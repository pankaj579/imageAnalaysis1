//
// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See https://aka.ms/csspeech/license for the full license information.
//
package ms.audibles.imageanalysis;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.EnumSet;

import ms.audibles.imageanalysis.imageResponse.ImageProcessResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.azure.ai.vision.common.*;
import com.azure.ai.vision.imageanalysis.*;

public class ImageProcessor {


    public static String analyzeImageFromFileNew(String endpoint, String key, String imageFileName) {
        String audibleResult = "";
        try (
                VisionServiceOptions serviceOptions = new VisionServiceOptions(new URL(endpoint), key);

                VisionSource imageSource = VisionSource.fromFile(imageFileName);

                ImageAnalysisOptions analysisOptions = new ImageAnalysisOptions()) {

            // Mandatory. You must set one or more features to analyze. Here we use the full set of features.
            // Note that 'Caption' and 'DenseCaptions' are only supported in Azure GPU regions (East US, France Central, Korea Central,
            // North Europe, Southeast Asia, West Europe, West US). Remove 'Caption' and 'DenseCaptions' from the list below if your
            // Computer Vision key is not from one of those regions.
            analysisOptions.setFeatures(EnumSet.of(
                    ImageAnalysisFeature.CAPTION,
                    ImageAnalysisFeature.DENSE_CAPTIONS,
                    ImageAnalysisFeature.OBJECTS,
                    ImageAnalysisFeature.PEOPLE,
                    ImageAnalysisFeature.TEXT,
                    ImageAnalysisFeature.TAGS));

            // Optional, and only relevant when you select ImageAnalysisFeature.CropSuggestions.
            // Define one or more aspect ratios for the desired cropping. Each aspect ratio needs to be in the range [0.75, 1.8].
            // If you do not set this, the service will return one crop suggestion with the aspect ratio it sees fit.
            //analysisOptions.setCroppingAspectRatios(Arrays.asList(0.9, 1.33));

            try (
                    ImageAnalyzer analyzer = new ImageAnalyzer(serviceOptions, imageSource, analysisOptions)) {
                System.out.println(" Please wait for image analysis results...\n");


                try(
                        ImageAnalysisResult result = analyzer.analyze()) {

                    if (result.getReason() == ImageAnalysisResultReason.ANALYZED) {

                        ImageAnalysisResultDetails resultDetails = ImageAnalysisResultDetails.fromResult(result);
                        System.out.println(" Result details:");
                        System.out.println("   JSON result = " + resultDetails.getJsonResult());
                        System.out.println("   ************************************************ " );
                        ObjectMapper mapper = new ObjectMapper();
                        ImageProcessResult imageResult = mapper.readValue(resultDetails.getJsonResult(), ImageProcessResult.class);
                        audibleResult = "  Image Description = " + imageResult.getcResult().getText() + ", Text Detail: " + imageResult.getrResult().getContent();
                        System.out.println(audibleResult);
                        System.out.println("   ************************************************ " );
                    } else {
                        ImageAnalysisErrorDetails errorDetails = ImageAnalysisErrorDetails.fromResult(result);
                        System.out.println(" Analysis failed.");
                        System.out.println("   Error reason: " + errorDetails.getReason());
                        System.out.println("   Error code: " + errorDetails.getErrorCode());
                        System.out.println("   Error message: " + errorDetails.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return audibleResult;
    }

    /**
     * Helper method to return a ByteBuffer containing content
     * read from file on disk.
     * 
     * @param path The full-path file name of the file to read.
     */
    private static ByteBuffer fileToByteBuffer(String path) throws java.io.FileNotFoundException, java.io.IOException {
        InputStream inputStream = new FileInputStream(path);
        byte[] bytes = inputStream.readAllBytes();
        inputStream.close();
        return ByteBuffer.wrap(bytes);
    }
}