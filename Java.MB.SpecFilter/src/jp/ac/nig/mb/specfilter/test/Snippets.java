package jp.ac.nig.mb.specfilter.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.oro.text.perl.Perl5Util;

import jp.ac.nig.mb.specfilter.entity.Record;
import jp.or.kazusa.sakurai.util.mfcalculator.MfCalculator;

public class Snippets {

    /**
     * ファイルを開く。<br/>
     * ファイルIOなどにエラーがあったときに確実にファイルをcloseできるよう、
     * ファイルハンドラ（in）はtryの前に宣言しておく。<br/>
     * @param inFile
     */
    public static void openFile(File inFile) {

        BufferedReader in = null;

        try { // try ~ catch ~ finallyは、例外処理
            in = new BufferedReader ( new FileReader( inFile ));

            String s;

            while( ( s = in.readLine()) != null) {

                // TODO something

                String[] line = s.split("\t");

                for(int i=0; i< line.length ; i++) {

                    // TODO something

                }


            }

        } catch (FileNotFoundException e) { // 例外があったらこのブロックに飛ぶので、必要な処理をする。
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (IOException e) { // 例外があったらこのブロックに飛ぶので、必要な処理をする。
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }finally { // 例外の有無に限らず、必ず実行される。ファイルのクローズはここでする。

            try { // オープンミスがあるかもしれないので、ここでも例外処理が必要。
                in.close();
            } catch (IOException e) {
                // TODO 自動生成された catch ブロック
                e.printStackTrace();
            }
        }

    }

    /**
     * ファイルに書き出す。<br/>
     * ファイルIOなどにエラーがあったときに確実にファイルをcloseできるよう、
     * ファイルハンドラ（out）はtryの前に宣言しておく。<br/>
     * out.close()で書き出しが行われるので、忘れずに。<br/>
     * @param outFile
     */
    public static void writeFile(File outFile) {

        PrintWriter out = null;

        try {
            out = new PrintWriter ( new FileWriter( outFile ));

            out.println("something");

        } catch (IOException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }finally {
            out.close();
        }


    }


    /**
     * 任意に並び替えする
     * @param list
     */
    public static void doSort(ArrayList<Record> list) {

        // 一度配列に入れる
        Record[] array = list.toArray( new Record[0] );

        // コンパレーターで好きに並べ替え
        Arrays.sort(array, new Comparator<Record>() {

            @Override
            public int compare(Record obj1, Record obj2) {

                double a = obj1.precursorMz;
                double b = obj2.precursorMz;

                // 小さい順に並べる場合
                if( a < b )
                    return -1;
                else if( a > b )
                    return 1;
                else
                    return 0;

            }

        });

        // 元のリストを空にして、並び替えたものと入れ替える

        // （注意）
        // 引数で渡した「オブジェクト」は必ず参照渡しとなるので、
        // 操作を加えると渡したオブジェクト自体に変更が加わります。
        // メソッド内で渡したオブジェクト（list）をnewしてしまうと、
        // これが崩れるのでnewしないこと。

        list.clear();

        for(Record d: array) {
            list.add(d);
        }

    }

    /**
     * HashSetを使ったUnique要素の列挙
     * @param list
     */
    public static void distinctUsingHashSet(ArrayList<Record> list) {

        HashSet<String> inchikeys = new HashSet<String>();

        for( Record rec: list ) {

            inchikeys.add( rec.inchiKey );

        }

        // テスト書き出し。Hashなので順番はバラバラになる。適宜ソートを。
        for(String s: inchikeys) {
            System.out.println( s );
        }

    }

    /**
     * HashMapを使った要素数のカウント
     * @param list
     */
    public static void countUsingHashMap(ArrayList<Record> list) {

        HashMap<String, Integer> formulaToCount = new HashMap<String, Integer>();

        for( Record rec: list ) {

            int count = 0;
            if( formulaToCount.containsKey(rec.formula)) {
                count = formulaToCount.get(rec.formula);
            }
            count++;
            formulaToCount.put(rec.formula, count);

        }

        // テスト書き出し。Hashなので順番はバラバラになる。適宜ソートを。
        for( String formula: formulaToCount.keySet() ) {

            System.out.println(formula + "\t" + formulaToCount.get(formula));

        }
    }

    /**
     * ビルドパスに追加したライブラリーの使い方の例。<br/>
     * MfCalculatorオブジェクトを作って、その中のメソッドを使う。<br/>
     * ここではformulaから精密質量を計算する。
     * @param formula
     */
    public static void calcExactMass(String formula) {

        MfCalculator calc = new MfCalculator();

        System.out.println( calc.getExactMass(formula) );

    }


    /**
     * Jakarta OROライブラリー（非常に古い）を使ってPerlのような正規表現をする。<br/>
     * Native Javaだけでも、replaceやmatchesなどの簡単な処理は行えるし、
     * Patternクラスを使って複雑な正規表現を処理することはできる。
     * @param list
     */
    public static void regularExpression(ArrayList<Record> list) {

        Perl5Util util = new Perl5Util();

        for( Record rec: list ) {

            if( util.match("/^(glutamine.*)/i", rec.name) ) {

                String matched = util.group(1);
                System.out.println( matched );


            }

        }

    }

}
