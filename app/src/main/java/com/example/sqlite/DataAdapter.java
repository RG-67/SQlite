package com.example.sqlite;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//  Creating Adapter class (DataAdapter) and extends "RecyclerView.Adapter".
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    //  Creating variables for arraylist.
    private ArrayList<DataModal> dataModalArrayList;

    //  creating variables for Context.
    private Context context;

    //  Creating constructor for "DataAdapter".
    public DataAdapter(ArrayList<DataModal> dataModalArrayList, Context context) {
        this.dataModalArrayList = dataModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override

    //  Creating method "onCreateViewHolder" for inflating layout (item_view) for the RecyclerView_Items.
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //  Creating inflater.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);

        //  Returning the layout and it's variable in the ViewHolder.
        return new ViewHolder(view);
    }

    @Override

    //  By "onBindViewHolder" display data at the specified position.
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder holder, int position) {

        //  Creating variable of "DataModel" and getting position of arraylist.
        DataModal dataModal = dataModalArrayList.get(position);

        //  Setting data to the views (nameView, numberView, emailView) to the items.
        holder.nameView.setText(dataModal.getPersonName());
        holder.numberView.setText(dataModal.getPersonNumber());
        holder.emailView.setText(dataModal.getPersonEmail());

        //  Setting "OnClickListener" method for RecyclerView_Items.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override

            //  Below method will work when a user click on the item from "item_view".
            public void onClick(View v) {

                //  After click on the item a new activity (activity_update_data.xml) will open.
                Intent intent = new Intent(context, UpdateData.class);

                //  Extending data to the intent of "UpdateData.class" where we first specified the extra data ("id", "name", "number", "email") and second we specified the values itself.
                intent.putExtra("id", dataModal.getId());
                intent.putExtra("name", dataModal.getPersonName());
                intent.putExtra("number", dataModal.getPersonNumber());
                intent.putExtra("email", dataModal.getPersonEmail());

                //  After extending data we start the activity (activity_update_data.xml) of "UpdateData.class" where we using context for getting all information about the current activity.
                context.startActivity(intent);
            }
        });
    }

    @Override

    //  Getting the size of the arraylist.
    public int getItemCount() {

        //  Returning the size of arraylist.
        return dataModalArrayList.size();
    }

    //  Creating a class (ViewHolder) for holding the
    public class ViewHolder extends RecyclerView.ViewHolder {

        //  Creating variables for TextView.
        public TextView nameView, numberView, emailView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //  Inflating  and identified textViews.
            nameView = itemView.findViewById(R.id.name);
            numberView = itemView.findViewById(R.id.number);
            emailView = itemView.findViewById(R.id.email);
        }
    }
}
