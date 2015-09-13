package eventhive.com.eventhive.Network;

/**
 * Created by Eigenaar on 13-09-2015.
 */

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {

    private static VolleySingleton instance = null;
    private RequestQueue rqueue;

    private VolleySingleton() {
        rqueue = Volley.newRequestQueue(ApplicationContext.getContext());
    }

    public static VolleySingleton getInstance() {
        if (instance == null) {
            instance = new VolleySingleton();
        }
        return instance;
    }

    public RequestQueue getRequestQueue()
    {
        return rqueue;

    }
}