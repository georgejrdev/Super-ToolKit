package com.georgejrdev.auxiliar.processing.interfaces;


public interface Translator {
    void translate(String content, String langDest);    
    void translate(String content, String langDest, String langSrc);    
}
