package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeGenerator {

    private static final Pattern FIELD_PATTERN = Pattern.compile("F\\s+([_\\w]+)\\s+(\\w+)?(\\[(\\d+)])?");

    private LanguageGenerator languageGenerator;

    public CodeGenerator(String targetLanguage) {
        if ("C".equals(targetLanguage)) {
            this.languageGenerator = new CLanguageGenerator();
        }
        if ("Pascal".equals(targetLanguage)) {
            this.languageGenerator = new PascalLanguageGenerator();
        }
    }

    public String generateCode(String source) {
        String currentType = null;
        for (String line : source.split("\n")) {
            char command = line.charAt(0);
            switch (command) {
                case '#':
                    languageGenerator.addComment(line.substring(2));
                    break;
                case 'M':
                    currentType = line.substring(2);
                    languageGenerator.startType(currentType);
                    break;
                case 'F':
                    Matcher matcher = FIELD_PATTERN.matcher(line);
                    if (!matcher.find()) {
                        throw new IllegalArgumentException("Line '" + line + "' is not using correct syntax. Please use 'E <name> <type>");
                    }
                    String type = matcher.group(2);
                    String typeName = matcher.group(1);
                    String dimension = matcher.group(4);
                    languageGenerator.addField(type, typeName, dimension);
                    break;
                case 'E':
                    if (currentType == null) {
                        throw new IllegalArgumentException("No corresponding type defined using 'M <typename>'");
                    }
                    languageGenerator.endType(currentType);
                    currentType = null;
                    break;
                default:
                    throw new IllegalArgumentException("Line '" + line + "' is invalid.");
            }
        }

        return languageGenerator.getCode();
    }

}
