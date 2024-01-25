/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author yuh
 */
public class Captcha {
    
    private static final long serialVersionUID = 1L;
    
    public static String generateCaptchaText() {
        int length = 6;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder captchaText = new StringBuilder();
        for (int i = 0; i < length; i++) {
            captchaText.append(characters.charAt(random.nextInt(characters.length())));
        }
        return captchaText.toString();
    }

//    public static BufferedImage generateCaptchaImage(String text) {
//        // Tạo hình ảnh BufferedImage cho CAPTCHA
//        int width = 200;
//        int height = 50;
//        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        Graphics g = image.getGraphics();
//
//        // Thiết lập màu nền
//        g.setColor(Color.WHITE);
//        g.fillRect(0, 0, width, height);
//
//        // Thiết lập font và màu văn bản
//        g.setFont(new Font("Arial", Font.BOLD, 30));
//        g.setColor(Color.BLACK);
//
//        // Vẽ văn bản CAPTCHA lên hình ảnh
//        g.drawString(text, 20, 40);
//
//        return image;
//    }
    
    public static BufferedImage generateCaptchaImage(String text) {
    // Tạo hình ảnh BufferedImage cho CAPTCHA
    int width = 200;
    int height = 50;
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics g = image.getGraphics();

    // Thiết lập màu nền
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, width, height);

    Random random = new Random();

    // Thiết lập font và màu văn bản
    Font font = new Font("Arial", Font.BOLD, 30);
    g.setFont(font);

    // Vẽ văn bản CAPTCHA lên hình ảnh với màu ngẫu nhiên cho từng ký tự
    for (int i = 0; i < text.length(); i++) {
        g.setColor(getRandomColor());
        int x = 20 + i * 30; // Adjust the spacing
        int y = 40;
        g.drawString(String.valueOf(text.charAt(i)), x, y);
    }

    // Add some random lines to make it harder for bots
    for (int i = 0; i < 5; i++) {
        g.setColor(getRandomColor());
        int x1 = random.nextInt(width);
        int y1 = random.nextInt(height);
        int x2 = random.nextInt(width);
        int y2 = random.nextInt(height);
        g.drawLine(x1, y1, x2, y2);
    }

    return image;
}


    public static Color getRandomColor() {
        // Tạo màu ngẫu nhiên
        Random random = new Random();
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}
