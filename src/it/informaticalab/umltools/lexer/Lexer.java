package it.informaticalab.umltools.lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Federico Ponzi on 12/05/16.
 */
public class Lexer
{
        Pattern tokenPatterns;
        public Lexer()
        {
                StringBuffer tokenPatternsBuffer = new StringBuffer();
                for (Token.Type tokenType : Token.Type.values())
                        tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
                tokenPatterns = Pattern.compile(new String(tokenPatternsBuffer.substring(1)));
        }
        public List<Token> tokenize(String line)
        {
                ArrayList<Token> tokens = new ArrayList<Token>();

                // Begin matching tokens
                Matcher matcher = tokenPatterns.matcher(line);
                while (matcher.find()) {
                        for (Token.Type tk: Token.Type.values()) {
                                if (matcher.group(Token.Type.WHITESPACE.name()) != null)
                                        continue;
                                else if (matcher.group(tk.name()) != null) {
                                        tokens.add(new Token(tk, matcher.group(tk.name())));
                                        break;
                                }
                        }
                }
                return tokens;

        }
        public static void main(String[] args)
        {
                String line = "public class Lexer {\n"
                + "public static void Main ( String[] args ) { } }\n";
                Lexer l = new Lexer();
                for(Token t : l.tokenize(line))
                {
                        System.out.println(t.getValue());
                        System.out.println(t.getType());
                }
        }
}
