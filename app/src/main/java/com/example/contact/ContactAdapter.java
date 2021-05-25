package com.example.contact;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contact.onclick.DeleteClickListener;
import com.example.contact.onclick.EditclickListener;

import java.util.List;


public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public List<Person> contactlist;
    private EditclickListener oneditclicklistner;
    private DeleteClickListener ondeleteclicklistener;

    public ContactAdapter(List<Person> contactlist, EditclickListener oneditclicklistner, DeleteClickListener ondeleteclicklistener) {

        this.contactlist = contactlist;
        this.oneditclicklistner = oneditclicklistner;
        this.ondeleteclicklistener = ondeleteclicklistener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_layout,parent,false);
        return new  ContactViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder,  int position) {
    ContactViewHolder contactViewHolder=(ContactViewHolder)holder;
      contactViewHolder.BindData(contactlist.get(position));
       contactViewHolder.getView().setOnLongClickListener(e->{
      ondeleteclicklistener.DeleteContact(contactlist.get(position));
       return true;
      });

/*contactViewHolder.getView().setOnClickListener(e->{
oneditclicklistner.editcontact(contactlist.get(position),position);
}); */ contactViewHolder.getView().setOnClickListener(e -> {
            oneditclicklistner.EditContact(contactlist.get(position), position);
        });

    }

    @Override
    public int getItemCount() {
        return contactlist.size();
    }
}
