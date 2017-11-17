package xu.ferris.robot.beans;

import com.free.launcher.wallpaperstore.mxdownload.xutils.db.annotation.Column;
import com.free.launcher.wallpaperstore.mxdownload.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * 解析xml
 * Created by xff on 2017/9/25.
 */
@Table(name = "WordsBean")
public class WordsBean implements Serializable{
    @Column(name = "id", isId = true)
    private int id;
    //问题
    @Column(name = "string_q")
    private String string_q;

    //回答
    @Column(name = "string_a")
    private String string_a;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getString_q() {
        return string_q;
    }

    public void setString_q(String string_q) {
        this.string_q = string_q;
    }

    public String getString_a() {
        return string_a;
    }

    public void setString_a(String string_a) {
        this.string_a = string_a;
    }
}
