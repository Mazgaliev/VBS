package org.L4;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;

public class DTERMS {


    private static final Model m = ModelFactory.createDefaultModel();

     public static final Property DESCRIPTION;
     public static final Property TITLE;

    public DTERMS() {

    }


    static {

        DESCRIPTION=m.createProperty("http://purl.org/dc/terms/description");
        TITLE = m.createProperty("http://purl.org/dc/terms/title");
    }
}
