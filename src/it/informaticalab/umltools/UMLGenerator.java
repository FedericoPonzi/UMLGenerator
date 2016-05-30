package it.informaticalab.umltools;

/**
 * Created by Federico Ponzi on 12/05/16.
 */
public class UMLGenerator
{
        private final String outputFile;
        private final String basePath;

        public UMLGenerator(String basePath, String outputFile)
        {
                this.basePath = basePath;
                this.outputFile = outputFile;
        }
        public void run()
        {
                System.out.println("Basepath: " + basePath);
                System.out.println("outputFile : " + outputFile);
        }
        public static void main(String[] args)
        {
                new UMLGenerator("basepath", "outputfile").run();
        }
}
