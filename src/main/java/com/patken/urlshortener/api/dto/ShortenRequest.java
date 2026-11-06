package com.patken.urlshortener.api.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record ShortenRequest(@NotBlank @URL String url) {
}
