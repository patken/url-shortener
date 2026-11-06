CREATE TABLE short_url (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    code         VARCHAR(16)   NOT NULL UNIQUE,
    original_url VARCHAR(2048) NOT NULL,
    created_at   TIMESTAMP     NOT NULL,
    click_count  BIGINT        NOT NULL DEFAULT 0
);
