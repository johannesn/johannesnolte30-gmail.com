package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeGenerator {

    private static final Pattern FIELD_PATTERN = Pattern.compile("F\\s+([_\\w]+)\\s+(\\w+)?(\\[(\\d+)])?");

    private String targetLanguage;

    public CodeGenerator(String targetLanguage) {
        this.targetLanguage = targetLanguage;
    }

    public String generateCode(String source) {
        StringBuilder generatedCode = new StringBuilder();

        String currentType = null;
        for (String line : source.split("\n")) {
            char command = line.charAt(0);
            switch (command) {
                case '#':
                    addComment(generatedCode, line);
                    break;
                case 'M':
                    currentType = line.substring(2);
                    startType(generatedCode);
                    break;
                case 'F':
                    Matcher matcher = FIELD_PATTERN.matcher(line);
                    if (!matcher.find()) {
                        throw new IllegalArgumentException("Line '" + line + "' is not using correct syntax. Please use 'E <name> <type>");
                    }
                    String type = matcher.group(2);
                    String typeName = matcher.group(1);
                    addField(generatedCode, matcher, type, typeName);
                    break;
                case 'E':
                    if (currentType == null) {
                        throw new IllegalArgumentException("No corresponding type defined using 'M <typename>'");
                    }
                    endType(generatedCode, currentType);
                    currentType = null;
                    break;
                default:
                    throw new IllegalArgumentException("Line '" + line + "' is invalid.");
            }
            generatedCode.append("\n");
        }

        return generatedCode.toString();
    }

    private void startType(StringBuilder generatedCode) {
        generatedCode.append("typedef struct {");
    }

    private void endType(StringBuilder generatedCode, String currentType) {
        generatedCode.append("} ").append(currentType).append("Msg;");
    }

    private void addField(StringBuilder generatedCode, Matcher matcher, String type, String typeName) {
        generatedCode.append("\t").append(type).append(" ").append(typeName);
        if (matcher.group(4) != null) {
            generatedCode.append("[").append(matcher.group(4)).append("]");
        }
        generatedCode.append(";");
    }

    private void addComment(StringBuilder generatedCode, String line) {
        generatedCode.append("/* ").append(line.substring(2)).append(" */");
    }

}
