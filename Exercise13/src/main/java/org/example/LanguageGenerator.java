package org.example;

public interface LanguageGenerator {

    void startType(String name);

    void endType(String type);

    void addField(String type, String typeName, String dimension);

    void addComment(String comment);

    String getCode();
}
