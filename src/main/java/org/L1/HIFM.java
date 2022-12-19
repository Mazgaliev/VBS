package org.L1;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class HIFM {

    private static final Model m = ModelFactory.createDefaultModel();

    public static final Property SIMILARTO;
    private static final Property DOSAGEFORM;

    public static final Property GENERICNAME;
    public static final Property PRICEWITHVAT;
    public static final Property PRICEWITHOUTVAT;
    public static final Property BRANDNAME;

    public HIFM() {

    }

    static {
        BRANDNAME = m.createProperty("http://wifo5-04.informatik.uni-mannheim.de/drugbank/resource/drugbank/brandName");
        SIMILARTO = m.createProperty("http://purl.org/net/hifm/ontology#similarTo");
        GENERICNAME = m.createProperty("http://wifo5-04.informatik.uni-mannheim.de/drugbank/resource/drugbank/genericName");
        DOSAGEFORM = m.createProperty("http://purl.org/net/hifm/ontology#dosageForm ");
        PRICEWITHOUTVAT = m.createProperty("http://purl.org/net/hifm/ontology#refPriceNoVAT");
        PRICEWITHVAT = m.createProperty("http://purl.org/net/hifm/ontology#refPriceWithVAT");
    }
}
