/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucla.cs.scai.canali.core.index.utils2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.jena.ontology.AllDifferent;
import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.ontology.ProfileRegistry;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.shared.PropertyNotFoundException;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.vocabulary.RDFS;

/**
 *
 * @author Rita
 */
public class TIAIndex {

    String path;
    OntModel TIAModel = ModelFactory.createOntologyModel(ProfileRegistry.OWL_DL_LANG);
    
    /**
     * 
     * @param sourceDir Source files path
     * @param outputDir Output files path
     * @throws java.io.FileNotFoundException
     */
    public TIAIndex(String dir) throws FileNotFoundException {
        System.out.println("SOURCE DIR: " + dir);
        this.path = dir;
        
        TIAModel.read(new FileReader(path + "/TIAInstancesAdjusted.owl"), "RDF/XML");
        System.out.println("TIAInstancesAdjusted.owl loaded successfully...");
    }
    
    /**
     * 
     * @return 
     */
    public HashSet<String> createClassParentsFile(){
        System.out.println("Creating class parents file...");
        HashSet<String> classes = new HashSet<>();
        
        try {
            System.out.println("Saving class hierarchy in: \n" + path + "/supportFiles/class_parents");
            
            PrintWriter out = new PrintWriter(new FileOutputStream(path + "/supportFiles/class_parents", false), true);
            
            StmtIterator stmts = TIAModel.listStatements(null, RDFS.subClassOf, (RDFNode) null);
            while (stmts.hasNext()){
                Statement stmt = stmts.next();
                out.println(stmt.getSubject() + "\t" + stmt.getObject());
                classes.add(stmt.getSubject().toString());
            }
            out.close();        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TIAIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
        return classes;
    }
    
    /**
     * 
     * @param classes
     * @throws UnsupportedEncodingException 
     */
    public void createClassLabelsFile(HashSet<String> classes) throws UnsupportedEncodingException{
        System.out.println("Creating class labels file...");
        try {
            System.out.println("Saving class labels in: \n" + path + "/supportFiles/class_labels");
            
            PrintWriter out = new PrintWriter(new FileOutputStream(path + "/supportFiles/class_labels", false), true);
            OntClass cls;
            
            ExtendedIterator<OntClass> ontClasses = TIAModel.listClasses();
            while (ontClasses.hasNext()){
                cls = ontClasses.next();
                if(classes.contains(cls.getURI()))
                {
                	out.println(cls.getURI() + "\t"+ URLDecoder.decode(StringEscapeUtils.unescapeJava(cls.getLabel("en")), "UTF-8"));
                }
            }
            out.close();        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TIAIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     * @return 
     * @throws java.io.IOException
     */
    public HashSet<String> createPropertyLabelsFile() throws IOException{
    System.out.println("Creating property labels file...");
    HashSet<String> properties = new HashSet<>();
    
        try {
            System.out.println("Saving property labels in: \n" + path + "/supportFiles/property_labels");
            
            PrintWriter out = new PrintWriter(new FileOutputStream(path + "/supportFiles/property_labels", false), true);
            OntProperty prop;
            
            ExtendedIterator<OntProperty> props = TIAModel.listAllOntProperties(); //modified from CANaLI
            while (props.hasNext()){
                prop = props.next();
                if (prop.getURI().startsWith("http://www.w3.org/2003/01/geo/wgs84_pos#")
                		||prop.getURI().startsWith("http://www.geonames.org/ontology#")
                		||prop.getURI().startsWith("http://www.semanticweb.org/seven/ontologies/2016/6/Tourism_in_Apulia#")
                		||prop.getURI().startsWith("http://xmlns.com/foaf/0.1/"))
                {
                    out.println(prop.getURI() + "\t" + StringEscapeUtils.unescapeJava(prop.getLabel("en")));
                    properties.add(prop.getURI());
                }
            }
            
//            BufferedReader in = new BufferedReader(new FileReader(sourcePath + "core-i18n/en/infobox_property_definitions_en.ttl"));
//            Pattern pattern = Pattern.compile("(\\s*)<(.*)> <http://www.w3.org/2000/01/rdf-schema#label> \"(.*)\"");
//            
//            String l = in.readLine();
//            //int n = 0;
//            while (l != null){
//                Matcher match = pattern.matcher(l);
//                if (match.find()){
//                    String aUri = match.group(2);
//                    String label = StringEscapeUtils.unescapeJava(match.group(3));
//                    if (aUri.startsWith("http://dbpedia.org/property")) {
//                        out.println(aUri + "\t" + label);
//                        properties.add(aUri);
//                    }
//                }
//                l = in.readLine();
//                //n++;
//            }
//            out.close();          
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TIAIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
        return properties;
    }
    
    /**
     * 
     * @return 
     */
    public HashSet<String> createEntityLabelsFile(){
    System.out.println("Creating entity labels file...");
    HashSet<String> entities = new HashSet<>();
    
        try {
            System.out.println("Saving entity labels in: \n" + path + "/supportFiles/entity_labels");
            
            PrintWriter out = new PrintWriter(new FileOutputStream(path + "/supportFiles/entity_labels", false), true);
            
            ExtendedIterator<Individual> individuals = TIAModel.listIndividuals();
            OntProperty name = TIAModel.getOntProperty("http://www.semanticweb.org/seven/ontologies/2016/6/Tourism_in_Apulia#name") ;
            String eUri = null,eLabel = null;
            Statement stmname;
            Individual individual;
            
            while (individuals.hasNext()){
                individual = individuals.next();
                
                try{
                	stmname = individual.getRequiredProperty(name);
                	eUri = stmname.getSubject().toString();
                	eLabel = stmname.getObject().toString();
                }
                catch (PropertyNotFoundException e) {
                	// TODO: handle exception
                	eUri = individual.toString();
                	eLabel = individual.getURI().substring(individual.getURI().indexOf("#")+1);
                	}
                
                out.println(eUri+ "\t" + eLabel);
                entities.add(eUri);
            }
            out.close();
            
        	} catch (FileNotFoundException ex) {
            Logger.getLogger(TIAIndex.class.getName()).log(Level.SEVERE, null, ex);
        	}
        return entities;
    }
    
    public void createEntityClassesFile(HashSet<String> entities,HashSet<String> classes){
        System.out.println("Creating entity classes file...");
        try {
            System.out.println("Saving entity classes in: \n" + path + "/supportFiles/entity_classes");
            
            PrintWriter out = new PrintWriter(new FileOutputStream(path + "/supportFiles/entity_classes", false), true);
            Individual individual;
            
            ExtendedIterator<Individual> individuals = TIAModel.listIndividuals();
            while (individuals.hasNext())
            {
                individual = individuals.next();
                if (entities.contains(individual.toString()) && classes.contains(individual.getRDFType().toString()))
                	out.println(individual.toString() + "\t" + individual.getRDFType().toString());
            }
            out.close();        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TIAIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     */
    public void createBasicTypesLiteralTypesFile(){
        System.out.println("Creating basic types literal file...");
        try {
            PrintWriter out = new PrintWriter(new FileOutputStream(path + "/supportFiles/basic_types_literal_types", false), true);
            out.println("http://www.w3.org/2001/XMLSchema#string\tString");
            out.println("http://www.w3.org/2001/XMLSchema#float\tDouble");
            out.println("http://www.w3.org/1999/02/22-rdf-syntax-ns#langString\tString");
            out.close();
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TIAIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     * @throws java.io.FileNotFoundException
     */
    public void createTripleFile() throws FileNotFoundException, IOException{
        System.out.println("Creating triples file...");
        
            System.out.println("Saving triples in: \n" + path + "/supportFiles/triples");
            
            PrintWriter out = new PrintWriter(new FileOutputStream(path + "/supportFiles/triples", false), true);
            //BufferedReader in = new BufferedReader(new FileReader(sourcePath + "core-i18n/en/mappingbased_literals_en.ttl"));
            //String l = in.readLine();
            //int n = 0; //!!!
            //while (l != null /*&& n < 1000*/){ //!!!
              //  out.println(l);
                //l = in.readLine();
                //n++; //!!!
            //}
            //in.close();
            
            StmtIterator iterator;
            
            Statement triple;
            iterator = TIAModel.listStatements();
            while (iterator.hasNext())
        	{
            	triple = iterator.next();
        		out.println("<" + triple.getSubject() + ">\t<" + triple.getPredicate() + ">\t<" + triple.getObject() + ">.");
        	}
            out.close();
            
//            in = new BufferedReader(new FileReader(sourcePath + "core-i18n/en/infobox_properties_unredirected_en.ttl"));
//            l = in.readLine();
//            while (l != null){
//                out.println(l);
//                l = in.readLine();
//            }
//            in.close();
            
           // in = new BufferedReader(new FileReader(sourcePath + "core-i18n/en/mappingbased_objects_en.ttl"));
            //l = in.readLine();
            //while (l != null /*&& n < 2000*/){ //!!!
              //  out.println(l);
                //l = in.readLine();
                //n++; //!!!
            //}
            //in.close();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String dir) {
        try {
            long start = System.currentTimeMillis();
            //String directory = "/home/lucia/nlp2sparql/dbpedia/2015-10/core-i18n/en";
            TIAIndex TIA = new TIAIndex(dir);
            HashSet<String> classes = TIA.createClassParentsFile();
            TIA.createClassLabelsFile(classes);
            
            TIA.createPropertyLabelsFile();
            
            HashSet<String> entities = TIA.createEntityLabelsFile();
            TIA.createEntityClassesFile(entities, classes);
            TIA.createBasicTypesLiteralTypesFile();
            TIA.createTripleFile();
            
            System.out.println("Ended at " + new Date());
            long time = System.currentTimeMillis() - start;
            long sec = time / 1000;
            System.out.println("The process took " + (sec / 60) + "'" + (sec % 60) + "." + (time % 1000) + "\"");
            
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(TIAIndex.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TIAIndex.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
