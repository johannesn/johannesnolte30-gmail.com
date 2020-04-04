package org.example;

public class CLanguageGenerator implements LanguageGenerator {

    private StringBuilder builder;

    public CLanguageGenerator() {
        builder = new StringBuilder();
    }

    @Override
    public void startType() {
        builder.append("typedef struct {\n");
    }

    @Override
    public void endType(String typeName) {
        builder.append("} ").append(typeName).append("Msg;\n");
    }

    @Override
    public void addField(String type, String typeName, String dimension) {
        builder.append("\t").append(type).append(" ").append(typeName);
        if (dimension != null) {
            builder.append("[").append(dimension).append("]");
        }
        builder.append(";\n");
    }

    @Override
    public void addComment(String comment) {
        builder.append("/* ").append(comment.substring(2)).append(" */\n");
    }

    @Override
    public String getCode() {
        return builder.toString();
    }

}