package xu.ferris.robot;

import android.content.Context;
import android.content.res.AssetManager;

import com.free.launcher.wallpaperstore.mxdownload.xutils.DbManager;
import com.free.launcher.wallpaperstore.mxdownload.xutils.Xutils;
import com.free.launcher.wallpaperstore.mxdownload.xutils.db.Selector;
import com.free.launcher.wallpaperstore.mxdownload.xutils.ex.DbException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import xu.ferris.robot.beans.WordsBean;

/**
 * Created by xff on 2017/11/17.
 */

public class RobotUtils {
    private static  DbManager db=null;
    public static void init(){
        DbManager.DaoConfig daoConfig = (new DbManager.DaoConfig()).setDbName("RobotUtils").setDbVersion(1);
        db = Xutils.getDb(daoConfig);
    }



    public  static void saveWords(List<WordsBean> words){
        try {
            db.save(words);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }




    public static  void readAssetsFiles(Context context){



            for(int i=0;i<22;i++) {
                test(readAssetsTxt(context, i+".txt"));
            }

    }

    public static String readAssetsTxt(Context context,String fileName){
        try {
            //Return an AssetManager instance for your application's package
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            // Convert the buffer into a string.
            String text = new String(buffer, "utf-8");
            // Finally stick the string into the text view.
            return text;
        } catch (IOException e) {
            // Should never happen!
//            throw new RuntimeException(e);
            e.printStackTrace();
        }
        return "读取错误，请检查文件名";
    }

    public static void test(String tttt){
        BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(tttt.getBytes(Charset.forName("utf8"))), Charset.forName("utf8")));
        String line;
        WordsBean currentWord=null;
        List<WordsBean> wordsBeans=new ArrayList<>();
        try {
            while ( (line = br.readLine()) != null ) {
                if(!line.trim().equals("")){

                    if(currentWord==null){
                        currentWord=new WordsBean();
                        currentWord.setString_q(line);
                    }else{
                        currentWord.setString_a(line);
                        wordsBeans.add(currentWord);
                        currentWord=null;
                    }
                }
            }

            saveWords(wordsBeans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getAnswer(String questions){
        try {
           List<WordsBean> wordsBeans=db.selector(WordsBean.class).where("string_q", "like","%"+ questions+"%").findAll();
           if(wordsBeans!=null&&wordsBeans.size()>0) {
               Random random = new Random();
               int index = random.nextInt(wordsBeans.size());
               WordsBean wordsBean=wordsBeans.get(index);
               if (wordsBean != null) {
                   String[] answers = wordsBean.getString_a().split(",");
                   if (answers != null && answers.length > 0) {
                        index = random.nextInt(answers.length);
                       return answers[index];
                   }
               }
           }
        } catch (DbException e) {
            e.printStackTrace();
        }

        return "抱歉我还不会";
    }
}
