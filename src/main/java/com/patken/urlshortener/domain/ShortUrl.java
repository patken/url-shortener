package com.patken.urlshortener.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "short_url")
public class ShortUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 16)
    private String code;

    @Column(name = "original_url", nullable = false, length = 2048)
    private String originalUrl;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "click_count", nullable = false)
    private long clickCount;

    protected ShortUrl() {
    }

    public ShortUrl(String code, String originalUrl) {
        this.code = code;
        this.originalUrl = originalUrl;
        this.createdAt = Instant.now();
    }

    public void registerClick() {
        this.clickCount++;
    }

    public Long getId() { return id; }
    public String getCode() { return code; }
    public String getOriginalUrl() { return originalUrl; }
    public Instant getCreatedAt() { return createdAt; }
    public long getClickCount() { return clickCount; }
}
