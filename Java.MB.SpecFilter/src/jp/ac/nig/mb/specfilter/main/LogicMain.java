package jp.ac.nig.mb.specfilter.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import jp.ac.nig.mb.specfilter.entity.Ion;
import jp.ac.nig.mb.specfilter.entity.Record;

public class LogicMain {

    public static ArrayList<Record> parseMsp(File file) {

        ArrayList<Record> ret = new ArrayList<Record>();

        BufferedReader in = null;

        try {
            in = new BufferedReader( new FileReader( file ));

            String s;

            Record rec = null;
            boolean isMass = false;
            boolean stored = false;

            while( ( s = in.readLine()) != null) {

                s = s.trim();

                if( s.equals("") ) {

                    isMass = false;
                    continue;

                }else if( s.startsWith( Record.LABEL_NAME) ) {

                    if( rec != null ) {
                        ret.add(rec);
                        stored = true;
                    }

                    rec = new Record();


                    rec.name = getValue( Record.LABEL_NAME, s);

                    isMass = false;

                    continue;
                }else if( s.startsWith( Record.LABEL_PRECURSORMZ)) {

                    String value = getValue( Record.LABEL_PRECURSORMZ, s);
                    if( ! value.equals("")) {
                        rec.precursorMz = Double.parseDouble( value );
                    }
                    continue;
                }else if( s.startsWith( Record.LABEL_PRECURSORTYPE) ) {
                    rec.precursorType = getValue( Record.LABEL_PRECURSORTYPE, s);
                    continue;
                }else if( s.startsWith( Record.LABEL_FORMULA) ) {
                    rec.formula = getValue( Record.LABEL_FORMULA, s);
                    continue;
                }else if( s.startsWith( Record.LABEL_ONTOLOGY) ) {
                    rec.ontology = getValue( Record.LABEL_ONTOLOGY, s);
                    continue;
                }else if( s.startsWith( Record.LABEL_INCHIKEY) ) {
                    rec.inchiKey = getValue( Record.LABEL_INCHIKEY, s);
                    continue;
                }else if( s.startsWith( Record.LABEL_SMILES) ) {
                    rec.smiles = getValue( Record.LABEL_SMILES, s);
                    continue;
                }else if( s.startsWith( Record.LABEL_RETENTIONTIME)) {
                    String value = getValue( Record.LABEL_RETENTIONTIME, s);
                    if( ! value.equals("") ) {
                        rec.retentionTime = Double.parseDouble( value );
                    }
                    continue;
                }else if( s.startsWith( Record.LABEL_IONMODE) ) {
                    rec.ionmode = getValue( Record.LABEL_IONMODE, s);
                    continue;
                }else if( s.startsWith( Record.LABEL_INSTRUMENTTYPE) ) {
                    rec.instrumentType = getValue( Record.LABEL_INSTRUMENTTYPE, s);
                    continue;
                }else if( s.startsWith( Record.LABEL_INSTRUMENT) ) {
                    rec.instrument = getValue( Record.LABEL_INSTRUMENT, s);
                    continue;
                }else if( s.startsWith( Record.LABEL_COLLISIONENERGY) ) {
                    rec.collisionEnergy = getValue( Record.LABEL_COLLISIONENERGY, s);
                    continue;
                }else if( s.startsWith( Record.LABEL_COMMENT) ) {
                    rec.comment = getValue( Record.LABEL_COMMENT, s);
                    continue;
                }else if( s.startsWith( Record.LABEL_NUM_PEAKS) ) {
                    String value = getValue( Record.LABEL_NUM_PEAKS, s);

                    if( ! value.equals("")) {
                        rec.numPeaks = Integer.parseInt( value );
                    }

                    isMass = true;
                    continue;
                }

                if( isMass ) {

                    String[] pairs = s.split("\t");

                    if( 1 < pairs.length ) {

                        Ion ion = new Ion();

                        ion.mass = Double.parseDouble( pairs[0] );
                        ion.intensity = Double.parseDouble( pairs[1] );

                        rec.ions.add(ion);

                        stored = false;
                    }

                }


            }

            if( ! stored ) {
                ret.add(rec);
            }


        } catch (FileNotFoundException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                // TODO 自動生成された catch ブロック
                e.printStackTrace();
            }
        }

        return ret;

    }


    private static String getValue(String label, String s) {

        String ret = "";

        ret = s.substring(label.length() + 1);
        ret = ret.trim();

        return ret;

    }

}
