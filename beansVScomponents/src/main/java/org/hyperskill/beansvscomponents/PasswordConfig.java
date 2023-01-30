package org.hyperskill.beansvscomponents;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PasswordConfig {
    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMERIC = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*_=+-/";

    @Bean
    public PasswordAlphabet allCharacters() {
        return new PasswordAlphabet(ALPHA + NUMERIC + SPECIAL_CHARS);
    }

    static class PasswordAlphabet {
        private final String characters;

        public PasswordAlphabet(String characters) {
            this.characters = characters;
        }

        public String getCharacters() {
            return characters;
        }
    }
}



