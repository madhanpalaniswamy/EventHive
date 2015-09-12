package eventhive.com.eventhive;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AcceptedEvents extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    ListView acceptedlist;
    String[] eventsname;
    String[] eventsdesc;
    EventListAdapter eventadapter;
    int[] eventimages = {R.drawable.konika, R.drawable.preethi, R.drawable.karthika};

    public AcceptedEvents() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_accepted_events, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventListAdapterBackgroundTask eventtask = new EventListAdapterBackgroundTask();
        eventtask.execute();
        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_structure, R.id.listtext);
        acceptedlist.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Hello", Toast.LENGTH_LONG).show();
        ;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }


    class EventListAdapterBackgroundTask extends AsyncTask<Void,EventListAdapter,Void>
    {

        @Override
        protected void onPreExecute() {
            acceptedlist = (ListView) getActivity().findViewById(R.id.acceptedlist);
            eventsname = getResources().getStringArray(R.array.eventname);
            eventsdesc = getResources().getStringArray(R.array.eventdesc);
        }

        @Override
        protected Void doInBackground(Void... params) {
            eventadapter = new EventListAdapter(getActivity(), eventsname, eventimages, eventsdesc);
            publishProgress(eventadapter);
            return null;
        }

        @Override
        protected void onProgressUpdate(EventListAdapter... values) {
            acceptedlist.setAdapter(eventadapter);
        }


    }
}

class EventListAdapter extends ArrayAdapter<String> {
    Context context;
    int[] images;
    String[] names;
    String[] desc;

    EventListAdapter(Context c, String[] eventsname, int[] imgs, String[] eventdesc) {
        super(c, R.layout.list_structure, R.id.eventtext, eventsname);
        this.context = c;
        this.images = imgs;
        this.names = eventsname;
        this.desc = eventdesc;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        EventViewHolder eholder = null;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_structure, parent, false);
            eholder = new EventViewHolder(row);
            row.setTag(eholder);
        }
        else
        {
            eholder = (EventViewHolder) row.getTag();
        }
        eholder.eventimage.setImageResource(images[position]);
        eholder.eventname.setText(names[position]);
        eholder.eventdesc.setText(desc[position]);
        return row;
    }
}