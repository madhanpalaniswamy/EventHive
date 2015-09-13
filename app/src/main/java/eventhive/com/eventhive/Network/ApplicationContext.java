package eventhive.com.eventhive.Network;

import android.app.Application;
import android.content.Context;

/**
 * Created by Eigenaar on 13-09-2015.
 */
public class ApplicationContext extends Application {

    private static ApplicationContext appinstance;
    @Override
    public void onCreate() {
        super.onCreate();
        appinstance = this;
    }

    public static ApplicationContext getInstance()
    {
        return appinstance;
    }
    public static Context getContext()
    {
        return appinstance.getApplicationContext();
    }
}
