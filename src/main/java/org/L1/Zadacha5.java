package org.L1;

import org.apache.jena.rdf.model.*;

import java.util.ArrayList;
import java.util.List;

public class Zadacha5 {

    public static void printInfoForOne(String resourceLink, Model model) {
        Resource resource = model.getResource(resourceLink);
        StmtIterator iterator = resource.listProperties();

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

    public static void listSimilarTo(String resourceLink, Model model) {
        Resource resource = model.getResource(resourceLink);
        StmtIterator iterator = resource.listProperties(HIFM.SIMILARTO);

        List<RDFNode> similarTo = new ArrayList<>();

        while (iterator.hasNext()) {
            Statement statement = iterator.nextStatement();
            RDFNode object = statement.getObject();

            similarTo.add(object);
        }

        System.out.println("Слични лекови на " + resource.getProperty(HIFM.GENERICNAME).getObject().toString() + " се: ");

        for (int i = 0; i < similarTo.size(); i++) {
            listNameOfResource(similarTo.get(i).asResource(), model);
        }
    }

    public static void listNameOfResource(Resource resourceLink, Model model) {
//        Resource resource = model.getProperty(resourceLink);


        System.out.println(model.listStatements(new SimpleSelector(resourceLink, HIFM.BRANDNAME, (RDFNode) null)).nextStatement().getObject().toString());

//        System.out.println(resource.getProperty(org.L1.HIFM.GENERICNAME).getObject().toString());
    }

    public static void listNames(Model model) {
        StmtIterator iterator = model.listStatements(new SimpleSelector(null, HIFM.GENERICNAME, (RDFNode) null));
        while (iterator.hasNext()) {
            Statement statement = iterator.nextStatement();
            Resource subject = statement.getSubject();
            Property predicate = statement.getPredicate();
            RDFNode object = statement.getObject();

            if (object instanceof Resource) {
                System.out.print(object.toString());
            } else {
                System.out.print(" \"" + object.toString() + "\"");
            }
            System.out.println(" .");
        }
    }

    public static void listDrugNamePriceSimilarToNamesPrices(String resourceLink, Model model) {
        Resource lek = model.getResource(resourceLink);
        System.out.println("The name of this medicine is " + lek.listProperties(HIFM.BRANDNAME).nextStatement().getObject().toString());
        System.out.println("Total price is: " + lek.listProperties(HIFM.PRICEWITHVAT).nextStatement().getObject().asLiteral().getString());
        List<RDFNode> similars = new ArrayList<>();
        StmtIterator iterator = lek.listProperties(HIFM.SIMILARTO);
        while (iterator.hasNext()) {
            Statement statement = iterator.nextStatement();
            String similarToResourceLink = statement.getObject().toString();
            printNameAndPrice(similarToResourceLink, model);
        }

    }

    public static void printNameAndPrice(String resourceLink, Model model) {
        Resource lek = model.getResource(resourceLink);
        System.out.println("The name of the similar medicine is " + lek.listProperties(HIFM.BRANDNAME).nextStatement().getObject().toString());
        System.out.println("Price with VAT is: " + lek.listProperties(HIFM.PRICEWITHVAT).nextStatement().getObject().asLiteral().getString());
    }

    public static void main(String[] args) {

        Model model = ModelFactory.createDefaultModel();
//        Nekoj si lek od hifm-dataset dokumentot
        String lek = "http://purl.org/net/hifm/data#37621";

        model.read("hifm-dataset.ttl", "TTL");

//PRVO baranje
        System.out.println("####################################################################");
        System.out.println("PRVO BARANJE");
        printInfoForOne(lek, model);

//        VTORO BARANJE
        System.out.println("####################################################################");
        System.out.println("VTORO BARANJE");
        listNames(model);


//        TRETO BARANJE
        System.out.println("####################################################################");
        System.out.println("TRETO BARANJE");

        listSimilarTo(lek, model);

//        CETVRTO BARANJE

        System.out.println("####################################################################");
        System.out.println("CETVRTO BARANJE");
        listDrugNamePriceSimilarToNamesPrices(lek, model);
    }
}
