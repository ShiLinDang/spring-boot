package com.baidu.Util;

import cn.hutool.core.util.ImageUtil;
import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

public class QrCodeUtil {
    private static final int BLACK = -16777216;
    private static final int WHITE = -1;

    public QrCodeUtil() {
    }

    public static byte[] generatePng(String content, int width, int height) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        generate(content, width, height, "png", out);
        return out.toByteArray();
    }

    public static File generate(String content, int width, int height, File targetFile) {
        BufferedImage image = generate(content, width, height);
        ImageUtil.write(image, targetFile);
        return targetFile;
    }

    public static void generate(String content, int width, int height, String imageType, OutputStream out) {
        BufferedImage image = generate(content, width, height);
        ImageUtil.write(image, imageType, out);
    }

    public static BufferedImage generate(String content, int width, int height) {
        BitMatrix bitMatrix = encode(content, width, height);
        return toImage(bitMatrix);
    }

    public static BitMatrix encode(String content, int width, int height) {
        return encode(content, BarcodeFormat.QR_CODE, width, height);
    }

    public static BitMatrix encode(String content, BarcodeFormat format, int width, int height) {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        HashMap<EncodeHintType, Object> hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(content, format, width, height, hints);
            return bitMatrix;
        } catch (WriterException var7) {
            throw new QrCodeException(var7);
        }
    }

    public static String decode(InputStream qrCodeInputstream) {
        return decode((Image)ImageUtil.read(qrCodeInputstream));
    }

    public static String decode(File qrCodeFile) {
        return decode((Image)ImageUtil.read(qrCodeFile));
    }

    public static String decode(Image image) {
        MultiFormatReader formatReader = new MultiFormatReader();
        LuminanceSource source = new BufferedImageLuminanceSource(ImageUtil.toBufferedImage(image));
        Binarizer binarizer = new HybridBinarizer(source);
        BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
        HashMap<DecodeHintType, Object> hints = new HashMap();
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");

        Result result;
        try {
            result = formatReader.decode(binaryBitmap, hints);
        } catch (NotFoundException var8) {
            throw new QrCodeException(var8);
        }

        return result.getText();
    }

    public static BufferedImage toImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, 1);

        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                image.setRGB(x, y, matrix.get(x, y)?-16777216:-1);
            }
        }

        return image;
    }
}
