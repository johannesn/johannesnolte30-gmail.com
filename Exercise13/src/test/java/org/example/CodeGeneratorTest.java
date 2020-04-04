package org.example;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class CodeGeneratorTest {

    private static final String SCHEMA =
            "# Add a product\n" +
                    "# to the 'on-order' list\n" +
                    "M AddProduct\n" +
                    "F id           int\n" +
                    "F name         char[30]\n" +
                    "F order_code   int\n" +
                    "E";

    @Test
    void testGenerateCodeInC() {
        String cCode = new CodeGenerator("C").generateCode(SCHEMA);

        assertThat(cCode, is("/* Add a product */\n" +
                "/* to the 'on-order' list */\n" +
                "typedef struct {\n" +
                "\tint id;\n" +
                "\tchar name[30];\n" +
                "\tint order_code;\n" +
                "} AddProductMsg;\n"));
    }
}
