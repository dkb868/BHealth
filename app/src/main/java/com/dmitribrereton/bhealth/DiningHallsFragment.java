package com.dmitribrereton.bhealth;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class DiningHallsFragment extends Fragment {
    private static final String TAG = "DiningHallsFragment";

    GridView mGridView;
    ArrayList<String> mItems = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dininghalls, container, false);
        mItems.add("De Neve");
        mItems.add("B plate");
        mItems.add("Covel");
        mItems.add("FEAST");
        mGridView = (GridView)view.findViewById(R.id.gridView);
        mGridView.setAdapter(new DiningHallAdapter(mItems));
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), FoodListActivity.class);
                int location;
                switch (i){
                    case 0:
                        location = 1; // DE NEVE
                        break;
                    case 1:
                        location = 2; // B PLATE
                        break;
                    case 2:
                        location = 7; // Covel
                        break;
                    case 3:
                        location = 4; // FEAST
                        break;
                    default:
                        location = 1; // BEST DINING HALL
                        break;
                }
                intent.putExtra("location", location);
                startActivity(intent);
            }
        });

        return view;

    }

    private class DiningHallAdapter extends ArrayAdapter<String>{
        public DiningHallAdapter(ArrayList<String> items){
            super(getActivity(),0,items);
        }

        @Override
        public View getView(int position,View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(android.R.layout.simple_list_item_1, parent, false);
            }
            String item = getItem(position);
            TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
            textView.setText(item);

            return convertView;
        }
    }


}
