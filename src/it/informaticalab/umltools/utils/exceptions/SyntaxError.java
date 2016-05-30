package it.informaticalab.umltools.utils.exceptions;

import it.informaticalab.umltools.lexer.Token;

/**
 * Created by isaacisback on 27/05/16.
 */
public class SyntaxError extends Exception
{
        public SyntaxError(String message)
        {
                super(message);
        }
        public SyntaxError(String expected, Token tok ){
                super("Expected: " + expected + ", got: " + tok.getValue() + " (" + tok.getType() + ") instead.");
        }
}
