package jp.ac.nig.mb.specfilter.test;

/**
 * <p>シングルトンモデルのサンプルです。<br/>
 * プログラム内で一度だけ作成でき、どこからでも呼び出せるインスタンスを
 * 作成する仕組みです。<br/>
 * 引数渡しで持ち回るのではなく、いろんなところからいきなり参照される可能性のある
 * メインデータや、あちこちから書き換えられる可能性のあるスレッド処理の進行状況など、
 * メモリに常駐させておきたいデータを保持するのに使われる。
 * </p>
 * <p>コンストラクタをprivateにして隠し、外部からはgetInstance()というstaticな
 * メソッドでアクセスして、内部に持つ自身のstaticインスタンス（なければ作成）を返します。
 * こうすることで、プログラムのどこから呼び出しても唯一のインスタンスを返す
 * オブジェクトを作成できます。
 * </p>
 * @author sakura
 *
 */
public class SnippetSingleton {

    private static SnippetSingleton manager;

    // 保持したいデータを持たせたりする。
    // public ArrayList<Record> masses;

    private SnippetSingleton() {

    }

    public static SnippetSingleton getInstance() {
        if(manager == null) {
            manager = new SnippetSingleton();
        }
        return manager;
    }

}
