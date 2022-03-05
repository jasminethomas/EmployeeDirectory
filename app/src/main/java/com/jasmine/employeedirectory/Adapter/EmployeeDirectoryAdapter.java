package com.jasmine.employeedirectory.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jasmine.employeedirectory.ItemClickListener;
import com.jasmine.employeedirectory.R;
import com.jasmine.employeedirectory.model.EmployeeModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EmployeeDirectoryAdapter extends RecyclerView.Adapter<EmployeeDirectoryAdapter.ViewHolder> {

    // creating a variable for array list and context.
    private ArrayList<EmployeeModel> employeeModelArrayList;
    private Context context;
    ItemClickListener listener;

    // creating a constructor for our variables.
    public EmployeeDirectoryAdapter(ArrayList<EmployeeModel> employeeModelArrayList, Context context,ItemClickListener listener) {
        this.employeeModelArrayList = employeeModelArrayList;
        this.context = context;
        this.listener = listener;
    }

    // method for filtering our recyclerview items.
    public void filterList(ArrayList<EmployeeModel> filterllist) {
        // below line is to add our filtered
        // list in our course array list.
        employeeModelArrayList = filterllist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EmployeeDirectoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.directory_list_item, parent, false);
        final ViewHolder mViewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getPosition());
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // setting data to our views of recycler view.
        EmployeeModel model = employeeModelArrayList.get(position);
        Picasso.get().load(model.getEmployeeProfileImage()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(holder.employeeImage);
        holder.employeeNameTV.setText(model.getEmployeeName());
        holder.employeeCompanyNameTV.setText(model.getEmployeeCompanyName());
    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return employeeModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our views.
        private TextView employeeNameTV, employeeCompanyNameTV;
        private ImageView employeeImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our views with their ids.
            employeeImage = itemView.findViewById(R.id.ivEmployeeImg);
            employeeNameTV = itemView.findViewById(R.id.tvEmployeeName);
            employeeCompanyNameTV = itemView.findViewById(R.id.tvEmployeeCompany);
        }
    }
}