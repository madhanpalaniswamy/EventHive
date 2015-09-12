package eventhive.com.eventhive;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Eigenaar on 31-08-2015.
 */
public class EventViewHolder {

    ImageView eventimage;
    TextView eventname;
    TextView eventdesc;

    EventViewHolder(View v)
    {
        eventimage = (ImageView) v.findViewById(R.id.eventimage);
        eventname = (TextView) v.findViewById(R.id.eventtext);
        eventdesc = (TextView) v.findViewById(R.id.eventdesc);
    }
}
