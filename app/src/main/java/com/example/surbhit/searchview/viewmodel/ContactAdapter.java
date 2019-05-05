package com.example.surbhit.searchview.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.surbhit.searchview.R;
import com.example.surbhit.searchview.model.Contact;
import com.example.surbhit.searchview.view.ContactDetail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyContactListViewHolder> {

    List<Contact> mainInfo;
    private ArrayList<Contact> arraylist;
    Context context;


    public ContactAdapter(Context context, List<Contact> mainInfo) {
        this.mainInfo = mainInfo;
        this.context = context;
        this.arraylist = new ArrayList<Contact>();
        this.arraylist.addAll(mainInfo);
    }

    public class MyContactListViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewUserImage;
        TextView textViewShowName;
        TextView textViewPhoneNumber;
       // CheckBox checkBoxSelectItem;

        public MyContactListViewHolder(View itemView) {
            super(itemView);

            textViewShowName = (TextView) itemView.findViewById(R.id.name);
            textViewPhoneNumber = (TextView) itemView.findViewById(R.id.no);
            imageViewUserImage = (ImageView) itemView.findViewById(R.id.pic);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Intent i = new Intent(context, ContactDetail.class);
                    i.putExtra("name", mainInfo.get(position).getName());
                    i.putExtra("phone", mainInfo.get(position).getPhone());
                    if( mainInfo.get(position).getImagepath()==null){
                    i.putExtra("image", "null");
                    }
                    else{
                        i.putExtra("image", mainInfo.get(position).getImagepath());

                    }
                    context.startActivity(i);

//                    Toast.makeText(context, mainInfo.get(position).getEmail(),
//                            Toast.LENGTH_LONG).show();

                }
            });
        }
    }

    @Override
    public MyContactListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowview, parent, false);
        MyContactListViewHolder holder = new MyContactListViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyContactListViewHolder holder, int position) {
        String imagepath = mainInfo.get(position).getImagepath();
        if (imagepath == null) {
            Picasso.with(context).load(R.drawable.avatar).into(holder.imageViewUserImage);
        }else {
            Picasso.with(context).load(imagepath).into(holder.imageViewUserImage);
        }
        holder.textViewShowName.setText(mainInfo.get(position).getName());
        holder.textViewPhoneNumber.setText(mainInfo.get(position).getPhone());
     //   holder.checkBoxSelectItem.setChecked(mainInfo.get(position).getCheckedBox());
    }

    @Override
    public int getItemCount() {
        return mainInfo.size();
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mainInfo.clear();
        if (charText.length() == 0) {
            mainInfo.addAll(arraylist);
        } else {
            for (Contact wp : arraylist) {
                if (wp.getName().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    mainInfo.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
