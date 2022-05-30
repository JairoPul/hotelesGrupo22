/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo22.hoteling.jaas;

/**
 *
 * @author Alvaro
 */
public class SecurityUtils {
    public static String stripTags(String s){
        return s.replace("&", "$amp;")
                .replace("<","&lt;")
                .replace(">", "&gt;")
                .replace("'", "&#039;");
    }
}
