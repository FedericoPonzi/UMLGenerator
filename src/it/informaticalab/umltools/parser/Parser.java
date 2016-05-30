package it.informaticalab.umltools.parser;

import it.informaticalab.umltools.lexer.Lexer;
import it.informaticalab.umltools.lexer.Token;
import it.informaticalab.umltools.uml.UMLClass;
import it.informaticalab.umltools.uml.UMLField;
import it.informaticalab.umltools.uml.UMLMethod;
import it.informaticalab.umltools.uml.UMLPackage;
import it.informaticalab.umltools.utils.exceptions.SyntaxError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Federico Ponzi on 14/05/16.
 *
 */

public class Parser
{
        private int index = 0;
        private List<UMLClass> classes = new ArrayList<>();
        private Token tok;
        private List<Token> tokens;
        private UMLPackage umlPackage;
        private UMLClass umlClass;

        public Parser(List<Token> tokens){
                this.tokens = tokens;
                tok = tokens.get(0);
                System.out.println("Tokens: " + Integer.toString(tokens.size()));
                while(index < tokens.size())
                {
                        try
                        {
                                classes.add(parseClass());
                        }
                        catch(SyntaxError e)
                        {
                                e.printStackTrace();
                                System.err.print("Syntax Error : " + e.getMessage());
                                System.exit(-1);
                        }
                }
        }

        private UMLClass parseClass() throws SyntaxError
        {
                System.out.println("Start class parsing.");
                UMLClass toRet = new UMLClass();
                System.out.println(tok.getValue());

                if(Token.modifiers.contains(tok.getType()))
                {
                        toRet.setModifier(tok.getValue());
                        next();
                }

                expect(Token.Type.CLASS);
                next();
                System.out.println(tok.getValue());

                expect(Token.Type.LITERAL);
                next();
                System.out.println(tok.getValue());

                expect(Token.Type.GRAPHOP);
                next();

                while (tok.getType() != Token.Type.GRAPHCL)
                {
                        System.out.println("Start Methods && fields parsing.");
                        parse(toRet);
                }
                next();
                return toRet;
        }

        private void expect(Token.Type expect) throws SyntaxError
        {
                if(tok.getType() != expect)
                {
                        throw new SyntaxError(expect.getPattern(), tok);
                }
        }
        public void parse(UMLClass uc) throws SyntaxError
        {
                String modifier = "";
                String name;
                String type;

                if(Token.modifiers.contains(tok.getType()))
                {
                        modifier = tok.getValue();
                        next();
                }

                expect(Token.Type.LITERAL);
                System.out.println(tok.getValue());

                type = tok.getValue();
                next();
                expect(Token.Type.LITERAL);
                name = tok.getValue();
                System.out.println(tok.getValue());
                next();
                System.out.println(tok.getValue());

                if(tok.getType() == Token.Type.EQUAL || tok.getType() == Token.Type.PVIRG)
                {
                        System.out.println("Field found!");
                        //It's a field
                        UMLField field = new UMLField(modifier, type, name);
                        uc.addField(field);
                }
                else if(tok.getType() == Token.Type.PARENOP)
                {
                        System.out.println("Method found!");
                        if(modifier.equals(""))
                        {
                                throw new SyntaxError("Method " + name + " needs a modifier.");
                        }
                        HashMap<String, String> args = parseMethodArguments();
                        skipBalancedParenthesis();
                        UMLMethod um = new UMLMethod(modifier, type, name, args);
                        uc.addMethod(um);
                }
                else
                {
                        throw new SyntaxError("'=' or '('", tok);
                }

        }
        private HashMap<String, String> parseMethodArguments() throws SyntaxError
        {
                next();
                HashMap<String, String> toRet = new HashMap<>();
                String type;
                String name;
                while(tok.getType() != Token.Type.PARENCL)
                {
                        //TODO: Types can have dots. There should be another method to parse Types.
                        expect(Token.Type.LITERAL);
                        type = tok.getValue();
                        next();
                        expect(Token.Type.LITERAL);
                        name = tok.getValue();
                        next();
                        toRet.put(name, type);
                        if(tok.getType() == Token.Type.COMMA){
                                next();
                        }
                }

                next();

                return toRet;
        }


        private void skipBalancedParenthesis() throws SyntaxError
        {
                expect(Token.Type.GRAPHOP);
                next();
                /*This method will skip parenthesis if they're balanced*/
                while(tok.getType() != Token.Type.GRAPHCL)
                {
                        if (tok.getType() == Token.Type.GRAPHOP)
                        {
                                skipBalancedParenthesis();
                        }
                }
                next();

        }

        private Token next(){
                index++;
                if(index < tokens.size())
                {
                        tok = tokens.get(index);
                }
                else{
                        tok = null;
                }
                return tok;
        }

        private Token peek()
        {
                return tokens.get(index+1);
        }
        public static void main(String[] args)
        {
                String line = "public class Lexer {\n"
                        + "public void Main ( String args ) { } }\n";
                Lexer l = new Lexer();
                List<Token> tokens = l.tokenize(line);
                for(Token t : tokens)
                {
                        System.out.println(t.getValue());
                        System.out.println(t.getType());
                }
                Parser p = new Parser(tokens);
                p.classes.stream().forEach(System.out::println);
                for (UMLClass c : p.classes){
                        System.out.println(c);
                }
        }
}
