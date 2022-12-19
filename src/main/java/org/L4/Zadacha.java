package org.L4;

import org.apache.jena.rdf.model.*;
import org.apache.jena.rdf.model.impl.PropertyImpl;
import org.apache.jena.riot.RDFParser;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Zadacha {

    public static void printAll(StmtIterator iterator) {

        while (iterator.hasNext()) {
            Statement statement = iterator.nextStatement();
            Resource subject = statement.getSubject();
            Property predicate = statement.getPredicate();
            RDFNode object = statement.getObject();

            System.out.print(subject.toString());
            System.out.print(" " + predicate.toString() + " ");

            if (object instanceof Resource) {
                System.out.print(object.toString());
            } else {
                System.out.print(" \"" + object.toString() + "\"");
            }
            System.out.println(" .");
        }
    }

    public static List<Model> makeModels(StmtIterator iterator) {
        List<Model> models = new ArrayList<>();
        while (iterator.hasNext()) {
            Statement statement = iterator.nextStatement();
            Resource subject = statement.getSubject();
            Property predicate = statement.getPredicate();
            RDFNode object = statement.getObject();

            Model model = ModelFactory.createDefaultModel();
            RDFParser.source(object.toString())
                    .httpAccept("application/ld+json")
                    .parse(model.getGraph());

            models.add(model);
        }

        return models;
    }

    static String url = "http://dbpedia.org/resource/Silk_Sonic";


    public static void main(String[] args) throws UnsupportedEncodingException {

        Model model = ModelFactory.createDefaultModel();

        RDFParser.source(url)
                .httpAccept("application/ld+json")
                .parse(model.getGraph());

//        model.write(System.out,"TTL");


        StmtIterator iterator =
                model.listStatements(new SimpleSelector(null, new PropertyImpl("http://dbpedia.org/ontology/bandMember"), (RDFNode) null));
        List<Model> models = makeModels(iterator);


        for (Model m : models) {

            StmtIterator iter = m.listStatements(new SimpleSelector(null, RDFS.label, (RDFNode) null));

            System.out.println("=================");
            printAll(iter);
            System.out.println("=================");


        }


    }

}
