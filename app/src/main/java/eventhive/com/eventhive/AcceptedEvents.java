package eventhive.com.eventhive;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AcceptedEvents extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    ListView acceptedlist;
    List<String> eventsname = new ArrayList<String>();
    List<String> eventsdesc = new ArrayList<String>();
    String displayURL = "http://madtry.ngrok.io/event/event_display.php";
    EventListAdapter eventadapter;
    int[] eventimages = {R.drawable.konika, R.drawable.preethi, R.drawable.karthika,R.drawable.ic_launcher};

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
        acceptedlist = (ListView) getActivity().findViewById(R.id.acceptedlist);
        //EventListAdapterBackgroundTask eventtask = new EventListAdapterBackgroundTask();
        //eventtask.execute();


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, displayURL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                if (response != null && response.length() > 0)



                try {
                    JSONArray jarray = response.getJSONArray("events");
                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject jevent = jarray.getJSONObject(i);
                        eventsname.add(jevent.getString("Event_Name"));
                        eventsdesc.add(jevent.getString("Event_Desc"));
                        //nametest = nametest+"~~~"+ jevent.getString("Event_Name");
                        //desctest = desctest+"~~~"+ jevent.getString("Event_Desc");


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                eventadapter = new EventListAdapter(getActivity(), eventsname, eventimages, eventsdesc);
                acceptedlist.setAdapter(eventadapter);
                Toast.makeText(getActivity(),eventsname.toString(),Toast.LENGTH_LONG).show();
                Toast.makeText(getActivity(),eventsdesc.toString(),Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(request);
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
            eventsname = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.eventname)));
            eventsdesc = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.eventdesc)));
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
    List<String> names;
    List<String> desc;

    EventListAdapter(Context c, List<String> eventsname, int[] imgs, List<String> eventdesc) {
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
        eholder.eventname.setText(names.get(position));
        eholder.eventdesc.setText(desc.get(position));
        return row;
    }
}