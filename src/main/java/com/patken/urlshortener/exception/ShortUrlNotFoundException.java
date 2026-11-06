package com.patken.urlshortener.exception;

public class ShortUrlNotFoundException extends RuntimeException {

    public ShortUrlNotFoundException(String code) {
        super("Aucune URL trouvée pour le code : " + code);
    }
}
