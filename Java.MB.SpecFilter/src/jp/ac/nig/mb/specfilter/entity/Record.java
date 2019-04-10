package jp.ac.nig.mb.specfilter.entity;

import java.util.ArrayList;

public class Record {

    public static String LABEL_NAME = "NAME";
    public static String LABEL_PRECURSORMZ = "PRECURSORMZ";
    public static String LABEL_PRECURSORTYPE = "PRECURSORTYPE";
    public static String LABEL_FORMULA = "FORMULA";
    public static String LABEL_ONTOLOGY = "Ontology";
    public static String LABEL_INCHIKEY = "INCHIKEY";
    public static String LABEL_SMILES = "SMILES";
    public static String LABEL_RETENTIONTIME = "RETENTIONTIME";
    public static String LABEL_IONMODE = "IONMODE";
    public static String LABEL_INSTRUMENTTYPE = "INSTRUMENTTYPE";
    public static String LABEL_INSTRUMENT = "INSTRUMENT";
    public static String LABEL_COLLISIONENERGY = "COLLISIONENERGY";
    public static String LABEL_COMMENT = "Comment";
    public static String LABEL_NUM_PEAKS = "Num Peaks";


    public String name;
    public double precursorMz;
    public String precursorType;
    public String formula;
    public String ontology;
    public String inchiKey;
    public String smiles;
    public double retentionTime;
    public String ionmode;
    public String instrumentType;
    public String instrument;
    public String collisionEnergy;
    public String comment;
    public int numPeaks;

    public ArrayList<Ion> ions = new ArrayList<Ion>();


}
