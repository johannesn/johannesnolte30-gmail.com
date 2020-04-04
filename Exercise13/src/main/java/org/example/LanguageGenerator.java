package org.example;

public interface LanguageGenerator {
    void startType();

    void endType(String currentType);

    void addField(String type, String typeName, String dimension);

    void addComment(String line);

    String getCode();
}
