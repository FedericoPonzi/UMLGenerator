package it.informaticalab.umltools.lexer;

import java.util.ArrayList;

/**
 * Created by Federico Ponzi on 12/05/16.
 */

public class Token
{
        public static final ArrayList<Type> modifiers = new ArrayList<>();
        private final String value;
        private final Type type;
        static
        {
                modifiers.add(Type.PRIVATE);
                modifiers.add(Type.PUBLIC);
                modifiers.add(Type.PROTECTED);
        }
        public String getValue(){
                return value;
        }
        public Token (  Type type, String value)
        {
                this.value = value;
                this.type = type;
        }

        public Type getType()
        {
                return type;
        }

        public enum Type
        {
                PUBLIC("public"),
                PRIVATE("private"),
                PROTECTED("protected"),
                STATIC("static"),
                CLASS("class"),
                INTERFACE("interface"),
                ABSTRACT("abstract"),
                //VOID("void"),
                GRAPHOP("\\{"),
                GRAPHCL("\\}"),
                PARENOP("\\("),
                PARENCL("\\)"),
                SQUAROP("\\["),
                SQUARCL("\\]"),
                DQUOTE("\""),
                DOT("\\."),
                WHITESPACE("[ \t\f\r\n]+"),
                STRING("\".*\""),
                LITERAL("[a-zA-Z1-9_]+"),
                EQUAL("\\="),
                GREATER("\\>"),
                COMMA(","),
                PVIRG(";"),
                ERROR("(.)");

                String pattern;

                Type(String pattern)
                {
                        this.pattern = pattern;
                }

                public String getPattern()
                {
                        return pattern;
                }

        }

}
