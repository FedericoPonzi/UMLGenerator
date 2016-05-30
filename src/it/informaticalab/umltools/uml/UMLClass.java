package it.informaticalab.umltools.uml;

import java.util.ArrayList;

/**
 * Created by isaacisback on 27/05/16.
 */
public class UMLClass implements UMLBase
{
        private String name;
        private UMLPackage umlPackage;
        private String modifier = "protected"; //Default java modifier
        private ArrayList<UMLField> fields = new ArrayList<>();
        private ArrayList<UMLMethod> methods = new ArrayList<>();

        private String type; //Class, Interface, Abstract

        @Override
        public String getType()
        {
                return null;
        }

        public UMLClass()
        {

        }
        public UMLClass(UMLPackage umlPackage, String name)
        {
                this.umlPackage = umlPackage;
                this.name = name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public UMLPackage getUmlPackage()
        {
                return umlPackage;
        }

        public void setUmlPackage(UMLPackage umlPackage)
        {
                this.umlPackage = umlPackage;
        }

        public void setModifier(String modifier)
        {
                this.modifier = modifier;
        }

        public String getFullName(){
                return this.name;
        }
        public String getName(){
                return name;
        }

        @Override public String getModifier()
        {
                return "";
        }

        public String getPackage(){
                return name.substring(0, name.lastIndexOf('.'));
        }
        public void addMethod(UMLMethod um)
        {
                this.methods.add(um);

        }
        public void addField(UMLField f)
        {
                this.fields.add(f);
        }

        @Override public String toString()
        {
                String toRet = "UMLCLass: "+getModifier() + " " +getType() + " " + getName() + "\n";
                for(UMLMethod m : methods)
                {
                        toRet += m.toString() + "\n";
                }
                for (UMLField f : fields)
                {
                        toRet += f.toString() + "\n";
                }
                return toRet;
        }
}
