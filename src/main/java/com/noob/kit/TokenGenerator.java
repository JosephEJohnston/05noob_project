package com.noob.kit;

import org.springframework.stereotype.Component;

@Component
public interface TokenGenerator {
    String generate(String[] strings);
}
