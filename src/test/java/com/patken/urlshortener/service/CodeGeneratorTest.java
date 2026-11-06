package com.patken.urlshortener.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CodeGeneratorTest {

    @Test
    void generatesSevenAlphanumericChars() {
        String code = new CodeGenerator().generate();
        assertThat(code).hasSize(7).matches("[a-zA-Z0-9]+");
    }
}
