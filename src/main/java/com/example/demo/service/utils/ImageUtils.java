package com.example.demo.service.utils;

import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImageUtils {

    public static byte[] convertImageToResolution(File file, String format, int width, int height) throws IOException {
        byte[] imageBytes;
        BufferedImage image = ImageIO.read(new FileInputStream(file));
        if (image != null && (image.getWidth() > width || image.getHeight() > height)) {
            image = getScaledImage(image, width, height);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(image, format, byteArrayOutputStream);
            imageBytes = byteArrayOutputStream.toByteArray();
        } else {
            imageBytes = FileUtils.readFileToByteArray(file);
        }

        return imageBytes;
    }

    private static BufferedImage getScaledImage(BufferedImage srcImage, int width, int height) {
        Dimension orgDimension = new Dimension(srcImage.getWidth(), srcImage.getHeight());
        Dimension boundaryDimension = new Dimension(width, height);
        Dimension newDimension = scaleDimension(orgDimension, boundaryDimension);
        int newWidth = newDimension.width;
        int newHeight = newDimension.height;

        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.setBackground(Color.WHITE);
        graphics2D.clearRect(0, 0, newWidth, newHeight);
        graphics2D.drawImage(srcImage, 0, 0, newWidth, newHeight, null);
        graphics2D.dispose();

        return resizedImage;
    }

    // orgDimension --> oryginalne wymiary obrazka
    // boundaryDimension -> idealne wymiary obrazka które chcemy uzyskać
    private static Dimension scaleDimension(Dimension orgDimension, Dimension boundaryDimension) {
        int originalWidth = orgDimension.width,
            originalHeight = orgDimension.height;

        //bondary = granica!!
        int boundaryWidth = boundaryDimension.width,
            boundaryHeight = boundaryDimension.height;

        int newWidth = originalWidth,
            newHeight = originalHeight;

        // Sprawdzamy czy musimy przeskalować szerokość obrazka
        if (originalWidth > boundaryWidth) {
            // Ustawiamy szerokość obrazka na tą którą chcemy docelowo
            newWidth = boundaryWidth;

            // Skalujemy wysokość obrazka zachowując proporcje (aby pasowało do nowej szerokości)
            newHeight = (newWidth * originalHeight) / originalWidth;
            // nowaSzerokość = nowaWysokość * orginalnaSzerokość / orginalnąWysokość
        }

        // Spradzamy czy musimy przeskalować wysokość obrazka
        //?orginalHeigth???
        if (newHeight > boundaryHeight) {
            // Ustawiamy wysokość obrazka na tą którą chcemy docelowo
            newHeight = boundaryHeight;

            // Skalujemy szerokość obrazka zachowując proporcje (aby pasowało do nowej wysokości)
            newWidth = (newHeight * originalWidth) / originalHeight;
        }

        return new Dimension(newWidth, newHeight);
    }
}
