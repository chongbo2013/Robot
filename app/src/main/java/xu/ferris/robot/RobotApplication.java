package xu.ferris.robot;

import android.app.Application;

import com.free.launcher.wallpaperstore.mxdownload.xutils.Xutils;

/**
 * Created by xff on 2017/11/17.
 */

public class RobotApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Xutils.Ext.init(this);
        Xutils.Ext.setDebug(false);
        RobotUtils.init();

    }
}
