package com.example.me.toolbar.history;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.me.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends ArrayAdapter<HistoryData> implements View.OnClickListener
{
    //this will hold the array that will contain the calculations
    private ArrayList<HistoryData> historyList;
    private Context context;
    private LayoutInflater inflater;
    //maybe also hold the textview of the fragments?
    private TextView calcFragment, unitFragment, currFragment;

    private static class ViewHolder
    {
        private TextView calcs;
        private TextView subText;

    }

    public HistoryAdapter(Context mContext, int id, List<HistoryData> data)
    {
        super(mContext,id, data);
        //this.historyList = data;
        this.context = mContext;

    }

    @Override
    public int getCount()
    {
        return 7;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    //this sets the views of the list items
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder = new ViewHolder();
        //HistoryData historyData = getItem(position);
        inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.history_textview, parent, false);
            holder.calcs =convertView.findViewById(R.id.history_textview);
            holder.subText = convertView.findViewById(R.id.history_subtext);
            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder) convertView.getTag();
            //holder.calcs.setText(historyData.getCalculation());
            //holder.subText.setText(historyData.getSubText());

        return convertView;
    }

    //this will have to do two things: on click, close and set the selected resultView to whichever calculation was pressed.
    // The rest of the work should be done between the fragments and MainActivity
    @Override
    public void onClick(View v) {
       /* int position = (Integer)v.getTag();
        Object object = getItem(position);
        HistoryData display = (HistoryData)object;*/

        //maybe would need to test which fragment is displayed and then set the resultView accordingly
       // display.setCalculation(calcFragment);
        //display.setCalculation(currFragment);
        //display.setCalculation(unitFragment);



    }
}
