import java.util.Scanner;

import org.apache.jena.query.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.rdf.model.Model;

public class Main {
    public static void main(String[] args) {

        System.out.println("Введите класс:");
        Scanner sc = new Scanner(System.in);
        String Class = sc.nextLine();

        sparqLTest(Class);

    }


    static void sparqLTest(String word) {

        FileManager.get().addLocatorClassLoader(Main.class.getClassLoader());
        Model model = FileManager.get().loadModel("C:\\Users\\olegf\\Desktop\\ont.owl");
        String queryString =
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" +
                "SELECT ?subject ?object" +
                "WHERE { ?subject rdfs:subClassOf  <http://www.semanticweb.org/олег/ontologies/2022/4/untitled-ontology-5#" + word + ">" +
                "}";
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        try {
            ResultSet results = qexec.execSelect();
            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                System.out.println(soln);
            }

        }finally {
            qexec.close();
        }
        repeat();
    }
    static void repeat(){
        System.out.println("Введите класс или закончите программу, написав (end):");
        Scanner sc = new Scanner(System.in);
        String Class = sc.nextLine();
        if(Class.equals("end"))
        {
            System.out.println("До скорого!");
        }
        else{
            sparqLTest(Class);
        }
    }

}