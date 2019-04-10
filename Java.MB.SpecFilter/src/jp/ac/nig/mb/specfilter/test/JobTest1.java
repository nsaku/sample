package jp.ac.nig.mb.specfilter.test;

import java.io.File;
import java.util.ArrayList;

import jp.ac.nig.mb.specfilter.entity.Record;
import jp.ac.nig.mb.specfilter.main.LogicMain;

public class JobTest1 {

    public static void selectJob() {
        test1();
    }

    private static void test1() {

        File inFile = new File("data/lib/MSMS-Public-Pos-VS11.msp");

        ArrayList<Record> specs = LogicMain.parseMsp(inFile);

        for(Record rec: specs) {

            if( rec.numPeaks != rec.ions.size()) {
                System.out.println( rec.numPeaks + "\t" + rec.ions.size());
            }

        }

    }

}
