package com.patken.urlshortener.api;

import com.patken.urlshortener.api.dto.ShortUrlResponse;
import com.patken.urlshortener.api.dto.ShortenRequest;
import com.patken.urlshortener.config.AppProperties;
import com.patken.urlshortener.domain.ShortUrl;
import com.patken.urlshortener.service.UrlShortenerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UrlShortenerController {

    private final UrlShortenerService service;
    private final AppProperties properties;

    public UrlShortenerController(UrlShortenerService service, AppProperties properties) {
        this.service = service;
        this.properties = properties;
    }

    @PostMapping("/api/urls")
    @ResponseStatus(HttpStatus.CREATED)
    public ShortUrlResponse shorten(@Valid @RequestBody ShortenRequest request) {
        return toResponse(service.shorten(request.url()));
    }

    @GetMapping("/api/urls/{code}")
    public ShortUrlResponse stats(@PathVariable String code) {
        return toResponse(service.getStats(code));
    }

    @GetMapping("/{code}")
    public ResponseEntity<Void> redirect(@PathVariable String code) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(service.resolve(code)))
                .build();
    }

    private ShortUrlResponse toResponse(ShortUrl s) {
        return new ShortUrlResponse(s.getCode(), properties.baseUrl() + "/" + s.getCode(),
                s.getOriginalUrl(), s.getCreatedAt(), s.getClickCount());
    }
}
