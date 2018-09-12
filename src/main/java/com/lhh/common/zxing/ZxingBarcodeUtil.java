package com.lhh.common.zxing;


import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;

/**
 * @author gaozzsoft
 * @blog http://gaozzsoft.iteye.com
 */
public class ZxingBarcodeUtil {

    public static int DF_Width = 200;
    public static int DF_Height = 50;

    private static int CODE_WIDTH = 3 + // start guard
            (7 * 6) + // left bars
            5 + // middle guard
            (7 * 6) + // right bars
            3; // end guard

    /**
     * 编码
     *
     * @param contents
     * @param width
     * @param height
     * @param imgPath
     */
    public static void encode(String contents, int width, int height, String imgPath) {
        int codeWidth = Math.max(CODE_WIDTH, width);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                    BarcodeFormat.EAN_13, codeWidth, height, null);

            MatrixToImageWriter
                    .writeToFile(bitMatrix, "png", new File(imgPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param contents
     * @param width
     * @param height
     * @param outputStream
     */
    public static void creatBarcode(String contents, int width, int height, String imageType, OutputStream outputStream) throws Exception {
        int codeWidth = Math.max(CODE_WIDTH, width);
        try {
            Map<EncodeHintType, String> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                    BarcodeFormat.CODE_39, codeWidth, height, hints);
            MatrixToImageWriter.writeToStream(bitMatrix, imageType, outputStream);
        } catch (Exception e) {
            throw e;
        } finally {
            outputStream.close();
        }
    }

    @Test
    public void test1() throws Exception {
        String imgPath = "e:/zxing_EAN13.png";
        // 益达无糖口香糖的条形码
        String contents = "1555445522666";
        int width = 200, height = 50;
        ZxingBarcodeUtil.encode(contents, width, height, imgPath);

        System.out.println("Michael ,you have finished zxing EAN13 encode.");

        String ss = decode(imgPath);
        System.out.println(ss);

    }

    /**
     * @param imgPath
     * @return String
     */
    public static String decode(String imgPath) throws Exception {
        BufferedImage image = null;
        Result result = null;
        try {
            image = ImageIO.read(new File(imgPath));
            if (image == null) {
                System.out.println("the decode image may be not exit.");
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            Map hints = new HashMap<>();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");

            result = new MultiFormatReader().decode(bitmap, hints);
            return result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void test() throws Exception {

//        File file = new File("e:/zxing_EAN13.PNG");
//        FileOutputStream fo = new FileOutputStream(file);

        OutputStream os = FilePathUtil.getFOStream("e://", "", "EAN13.PNG");
        // 益达无糖口香糖的条形码
        String contents = "0000130000049";

        int width = 105, height = 50;
        ZxingBarcodeUtil.creatBarcode(contents, width, height, "PNG", os);
        os.close();
        System.out.println("Michael ,you have finished zxing EAN13 encode.");
    }
}