package org.L1;

import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.VCARD;
import org.apache.jena.vocabulary.VCARD4;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Zadacha1_2 {


    public static void printAll(StmtIterator iterator) {
        System.out.println("Printing with model.listStatements(): .");
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

    public static void main(String[] args) throws FileNotFoundException {
        String personUri = "https://github.com/Mazgaliev";
        String name = "Mite";
        String surname = "Mazgaliev";
        Model model = ModelFactory.createDefaultModel();

//        Eden nacin za dodavanje props
        Resource mazgaliev = model.createResource(personUri).
                addProperty(VCARD.NAME, name)
                .addProperty(VCARD.NICKNAME, surname);

//

        mazgaliev.addProperty(VCARD.ADR, "The end of the world");
        mazgaliev.addProperty(VCARD.BDAY, "25.10.2000");
        mazgaliev.addProperty(VCARD.EMAIL, "mite_mazgaliev@live.com");
        mazgaliev.addProperty(VCARD4.hasGender, "MALE");
        mazgaliev.addProperty(VCARD.AGENT, "007");
        mazgaliev.addProperty(VCARD.Region, "Jugo Istok");
        mazgaliev.addProperty(FOAF.interest, "Gym");
        mazgaliev.addProperty(FOAF.phone, "075-277-544");
        StmtIterator iterator = mazgaliev.listProperties();

        printAll(iterator);

        System.out.println("Print as RDF/XML: .");
        model.write(System.out);

        System.out.println("Print as N-TRIPLES: .");
        model.write(System.out, "N-TRIPLES");

        System.out.println("Print as RDF/XML-ABBREV(Pretty): .");
        model.write(System.out);

        System.out.println("Print as Turtle");
        model.write(System.out, "TTL");

        OutputStream op = new FileOutputStream("mazgaliev.ttl");
        OutputStream opxml = new FileOutputStream("mazgaliev.xml");
        OutputStream opn3ples = new FileOutputStream("mazgaliev.nt");

        RDFDataMgr.write(op, model, Lang.TURTLE);
        RDFDataMgr.write(opxml, model, Lang.RDFXML);
        RDFDataMgr.write(opn3ples, model, Lang.NTRIPLES);

    }
}
