package org.example;

import java.util.HashMap;
import java.util.Map;

public class PascalLanguageGenerator implements LanguageGenerator {

    private static final Map<String, String> typeMap = new HashMap<>();
    static {
        typeMap.put("char", "char");
        typeMap.put("int", "LongInt");
    }

    private StringBuilder builder;

    public PascalLanguageGenerator() {
        this.builder = new StringBuilder();
    }

    @Override
    public void startType(String name) {
        builder.append(name).append("Msg = packed record\n");
    }

    @Override
    public void endType(String type) {
        builder.append("end;\n");
    }

    @Override
    public void addField(String type, String typeName, String dimension) {
        builder.append("  ").append(typeName).append(": ");
        if (dimension != null) {
            builder.append("array[0..").append(Integer.parseInt(dimension) - 1).append("] of ");
        }
        builder.append(transposeType(type)).append(";\n");
    }

    @Override
    public void addComment(String comment) {
        builder.append("{ ").append(comment).append(" }\n");
    }

    @Override
    public String getCode() {
        return builder.toString();
    }

    private String transposeType(String type) {
        return typeMap.get(type);
    }
}
