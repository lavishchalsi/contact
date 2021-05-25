package com.example.contact;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public TextView phone_number;
    public View view;

    public ContactViewHolder( View view) {
        super(view);
        this.view=view;
        name=view.findViewById(R.id.textView_name);
        phone_number=view.findViewById(R.id.textView_number);
    }
    public View getView(){
       return view;
    }
    public void BindData(Person person){
        name.setText(person.getName());
        phone_number.setText(person.getNumber());
    }
}
