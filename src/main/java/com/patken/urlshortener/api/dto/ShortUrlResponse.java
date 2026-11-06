package com.patken.urlshortener.api.dto;

import java.time.Instant;

public record ShortUrlResponse(String code, String shortUrl, String originalUrl,
                               Instant createdAt, long clickCount) {
}
