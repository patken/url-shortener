package com.patken.urlshortener.service;

import com.patken.urlshortener.domain.ShortUrl;
import com.patken.urlshortener.exception.ShortUrlNotFoundException;
import com.patken.urlshortener.repository.ShortUrlRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UrlShortenerService {

    private static final int MAX_ATTEMPTS = 5;

    private final ShortUrlRepository repository;
    private final CodeGenerator codeGenerator;

    public UrlShortenerService(ShortUrlRepository repository, CodeGenerator codeGenerator) {
        this.repository = repository;
        this.codeGenerator = codeGenerator;
    }

    @Transactional
    public ShortUrl shorten(String originalUrl) {
        for (int i = 0; i < MAX_ATTEMPTS; i++) {
            String code = codeGenerator.generate();
            if (!repository.existsByCode(code)) {
                return repository.save(new ShortUrl(code, originalUrl));
            }
        }
        throw new IllegalStateException("Impossible de générer un code unique");
    }

    @Transactional
    public String resolve(String code) {
        ShortUrl shortUrl = repository.findByCode(code)
                .orElseThrow(() -> new ShortUrlNotFoundException(code));
        shortUrl.registerClick();
        return shortUrl.getOriginalUrl();
    }

    @Transactional(readOnly = true)
    public ShortUrl getStats(String code) {
        return repository.findByCode(code)
                .orElseThrow(() -> new ShortUrlNotFoundException(code));
    }
}
