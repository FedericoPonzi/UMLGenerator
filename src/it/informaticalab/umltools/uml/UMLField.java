package it.informaticalab.umltools.uml;

/**
 * Created by isaacisback on 29/05/16.
 */
public class UMLField implements UMLBase
{
        private String name;
        private String type;
        private String modifier;

        public UMLField(String modifier, String type, String name)
        {
                this.type = type;
                this.name = name;
        }

        @Override
        public String getName()
        {
                return name;
        }

        @Override
        public String getModifier()
        {
                return modifier;
        }

        @Override public String getType()
        {
                return type;
        }

        @Override public String toString()
        {
                return "UMLField: "+getModifier() + " " +getType() + " " + getName();
        }
}
