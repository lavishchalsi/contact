package com.example.contact;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;

import com.example.contact.onclick.DeleteClickListener;
import com.example.contact.onclick.EditclickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity  implements DeleteClickListener, EditclickListener {
    private FloatingActionButton floatingActionButton;
    private ContactAdapter contactAdapter;
    private List<Person> contaclist;
    private RecyclerView recyclerView;
    private final int REQUEST_CODE_EDIT = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
contaclist=new ArrayList<>();
for (int i=0;i<10;i++){
    contaclist.add(new Person("name"+(i+1),"phonenumber"+(i+1)));
}
 floatingActionButton = findViewById(R.id.floatingActionButton_add);
        recyclerView = findViewById(R.id.recyclerview_id);
        contactAdapter=new ContactAdapter(contaclist,this,this);
recyclerView.setAdapter(contactAdapter);
recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

FloatingActionButtonListner();
    }
    public  void FloatingActionButtonListner(){
        floatingActionButton.setOnClickListener(e->{
            Intent intent = new Intent(this, EditActivity.class);
            intent.putExtra(Constants.PERSON_INTENT_EDIT, false);
            startActivityForResult(intent, REQUEST_CODE_EDIT);
        });
    }
    @Override
    public void DeleteContact(Person person) {
        contaclist.remove(person);
        contactAdapter.notifyDataSetChanged();
    }

    @Override
    public void EditContact(Person person, int index) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra(Constants.PERSON_INTENT_EDIT, true);
        intent.putExtra(Constants.PERSON_INTENT_INDEX, index);
        intent.putExtra(Constants.PERSON_INTENT_OBJECT, person);
        startActivityForResult(intent, REQUEST_CODE_EDIT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_EDIT){
            if(resultCode==RESULT_OK)
            {
                if(data==null){
                    return;
                }
                boolean isEdit=data.getBooleanExtra(Constants.PERSON_INTENT_EDIT,false);
                Person person=data.getParcelableExtra(Constants.PERSON_INTENT_OBJECT);
                if (isEdit){
                 int index=data.getIntExtra(Constants.PERSON_INTENT_INDEX,-1);
                 if (index==-1){
                     return;
                 }
                 contaclist.set(index,person);
                 contactAdapter.notifyDataSetChanged();
                }else {
                    contaclist.add(person);
                    contactAdapter.notifyDataSetChanged();
                }
            }
        }
    }
}