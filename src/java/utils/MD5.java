/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author yuh
 */
public class MD5 {
    
    public static String hashPassword(String password) {
        try {
            // Tạo MessageDigest cho MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Thực hiện mã hóa mật khẩu
            md.update(password.getBytes());

            // Lấy dữ liệu đã mã hóa
            byte[] bytes = md.digest();

            // Chuyển dữ liệu đã mã hóa sang dạng hex
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            // Trả về chuỗi mã hóa
            return sb.toString();
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }
}
