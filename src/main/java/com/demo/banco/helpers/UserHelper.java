package com.demo.banco.helpers;

public interface UserHelper {

    static String generateUsername(String fullName) {
        String username = String.valueOf(fullName.charAt(0));
        return username.concat(fullName.substring(fullName.lastIndexOf(" ") + 1)).toLowerCase();
    }

}
