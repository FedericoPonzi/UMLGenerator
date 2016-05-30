package it.informaticalab.umltools.uml;

import java.util.HashMap;

/**
 * Created by isaacisback on 27/05/16.
 */
public class UMLMethod implements UMLBase
{
        private final String name;
        private final String modifier;
        private final String returnType;
        private final HashMap<String, String> args;

        public UMLMethod(String modifier, String returnType, String name, HashMap<String, String> args)
        {
                this.name = name;
                this.modifier= modifier;
                this.returnType = returnType;
                this.args = args;
        }

        @Override public String getName()
        {
                return name;
        }

        public String getType()
        {
                return returnType;
        }

        @Override public String getModifier()
        {
                return modifier;
        }


        public HashMap<String, String> getArgs()
        {
                return args;
        }
        @Override
        public String toString()
        {
                return "UMLMethod: "+getModifier() + " " +getType() + " " + getName();
        }
}
