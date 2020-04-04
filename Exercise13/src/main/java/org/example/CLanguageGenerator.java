package org.example;

public class CLanguageGenerator implements LanguageGenerator {

    private StringBuilder builder;

    public CLanguageGenerator() {
        builder = new StringBuilder();
    }

    @Override
    public void startType(String name) {
        builder.append("typedef struct {\n");
    }

    @Override
    public void endType(String type) {
        builder.append("} ").append(type).append("Msg;\n");
    }

    @Override
    public void addField(String type, String typeName, String dimension) {
        builder.append("  ").append(type).append(" ").append(typeName);
        if (dimension != null) {
            builder.append("[").append(dimension).append("]");
        }
        builder.append(";\n");
    }

    @Override
    public void addComment(String comment) {
        builder.append("/* ").append(comment).append(" */\n");
    }

    @Override
    public String getCode() {
        return builder.toString();
    }

}