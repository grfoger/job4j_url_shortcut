package ru.job4j.shortcut;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PassGen {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pass = encoder.encode("somepass");
        System.out.println(pass);
        /**
         $2a$10$bhw6h89qPDFXkbPbl.245.kTREVNez8y45X2Mph14aefK90sUg1nu
         */
    }
}
