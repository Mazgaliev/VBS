package org.L1;

import org.apache.jena.base.Sys;
import org.apache.jena.graph.Triple;
import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.VCARD;

import java.io.InputStream;
import java.util.Iterator;

public class Zadacha3_4 {
    public static void printProperties(Resource resource) {
        StmtIterator iterator = resource.listProperties();
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


    public static void main(String[] args) {
        String inputFileName = "C:\\Users\\Mite\\Desktop\\IntelliJ\\VBS\\VBS_HW\\Project\\mazgaliev.ttl";
        Model model = ModelFactory.createDefaultModel();
        InputStream inputStream;


        model.read("mazgaliev.ttl");
//        model.write(System.out, "TTL");
//        Do ovde e 3 zadacha

        Resource mazgaliev = model.getResource("https://github.com/Mazgaliev");


        var name = mazgaliev.getProperty(VCARD.NAME);
        var surname = mazgaliev.getProperty(VCARD.NICKNAME);
        var address = mazgaliev.getProperty(VCARD.EMAIL);

        System.out.println(name.getObject().toString());
        System.out.println(surname.getObject().toString());
        System.out.println(address.getObject().toString());
    }
}
