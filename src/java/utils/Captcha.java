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

    public static BufferedImage generateCaptchaImage(String text) {
        // Tạo hình ảnh BufferedImage cho CAPTCHA
        int width = 200;
        int height = 50;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        // Thiết lập màu nền
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // Thiết lập font và màu văn bản
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.setColor(Color.BLACK);

        // Vẽ văn bản CAPTCHA lên hình ảnh
        g.drawString(text, 20, 40);

        return image;
    }

    public static Color getRandomColor() {
        // Tạo màu ngẫu nhiên
        Random random = new Random();
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}
