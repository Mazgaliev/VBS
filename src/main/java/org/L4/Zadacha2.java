package org.L4;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.rdf.model.impl.PropertyImpl;
import org.apache.jena.riot.RDFParser;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

import java.util.ArrayList;
import java.util.List;

public class Zadacha2 {

    static List<Model> getModels(StmtIterator iterator) {

        List<Model> models = new ArrayList<>();
        while (iterator.hasNext()) {
            Statement statement = iterator.nextStatement();
            Resource subject = statement.getSubject();
            Property predicate = statement.getPredicate();
            RDFNode object = statement.getObject();

            Model m = ModelFactory.createDefaultModel();
            RDFParser.source(object.toString())
                    .httpAccept("application/ld+json")
                    .parse(m.getGraph());

            models.add(m);
        }

        return models;
    }

    public static void main(String[] args) {
        Model model = ModelFactory.createDefaultModel();
        String lek = "http://purl.org/net/hifm/data#987832";

        model.read("hifm-dataset-bio2rdf.ttl", "TTL");


        Resource product = model.getResource(lek);

        StmtIterator iter = product.listProperties(RDFS.seeAlso);
        //Zemame modeli
        List<Model> lekovi = getModels(iter);

        //Pechatime title i desc
        for (Model l : lekovi) {

            System.out.println("===========================");
            Zadacha.printAll(l.listStatements(new SimpleSelector(null,DTERMS.TITLE,(RDFNode) null)));
            Zadacha.printAll(l.listStatements(new SimpleSelector(null,DTERMS.DESCRIPTION,(RDFNode) null)));

            System.out.println("===========================");
        }



    }
}
