package dev.cavefish.minipost.domain.tags;

import org.springframework.stereotype.Service;

@Service
public class TagNameNormalizer {

    public String normalize(String tagName) {
        return tagName.toLowerCase();
    }

}
