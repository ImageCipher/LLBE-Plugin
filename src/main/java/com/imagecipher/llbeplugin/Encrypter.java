package com.imagecipher.llbeplugin;

import com.imagecipher.icsdk.IcEncrypter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Encrypter extends IcEncrypter {

    private int row;
    private int col;

    @Override
    public void encrypt(String s) {
        for (char c : s.toCharArray()) {
            encryptByte(c);
        }

        // Resetting values of cursor coordinates
        col = 0;
        row = 0;
    }

    @Override
    public void close() {
        try {
            File file = new File(fileName);
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void encryptByte(char character) {
        for (int i = 0; i < 8; ++i) {
            encryptBitCharacter((character >> i) & 0b1);
        }
    }

    private void encryptBitCharacter(int c) {
        if (col < image.getWidth() - 1) {
            int mask = 0b11111111;
            int rgb = image.getRGB(col, row);
            int r = (rgb >> 16) & mask;
            int g = (rgb >> 8) & mask;

            int b = image.getRGB(col + 1, row) & mask;
            b -= c;

            Color color = new Color(r, g, b);
            image.setRGB(col, row, color.getRGB());

            col += 2;
        } else {
            row++;
            col = 0;
        }
    }
}
