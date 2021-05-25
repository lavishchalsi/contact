package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    private EditText edittextname;
    private EditText edittextnumber;
    private Button buttonedit;
    private Button buttoncancel;
    private Person person;
    private int index = -1;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        setview();
        initPerson();
        setButtonCancelListener();
        setButtonEditListener();



    }

    private void setview() {
        edittextname = findViewById(R.id.idEdtName);
        edittextnumber = findViewById(R.id.idEdtPhoneNumber);
        buttonedit = findViewById(R.id.btn_edit);
        buttoncancel = findViewById(R.id.button_cancel);
    }

    private void initPerson() {
        Intent intent = getIntent();
        if (intent != null) {
            isEdit = intent.getBooleanExtra(Constants.PERSON_INTENT_EDIT, false);
            if (isEdit) {
                person = intent.getParcelableExtra(Constants.PERSON_INTENT_OBJECT);
                index = intent.getIntExtra(Constants.PERSON_INTENT_INDEX, -1);
                if (index == -1) {
                    setResult(RESULT_CANCELED);
                    finish();
                }
                edittextname.setText(person.getName());
                edittextnumber.setText(person.getNumber());
                buttonedit.setText(getString(R.string.button_edit));
            } else {
                person = new Person();
                buttonedit.setText(getString(R.string.button_add));
            }

        }
    }

    private void setButtonCancelListener() {
        buttoncancel.setOnClickListener(e -> {
            setResult(RESULT_CANCELED);
            finish();
        });


    }

    private void setButtonEditListener() {
        buttonedit.setOnClickListener(e -> {
            String Name = edittextname.getText().toString().trim();
            String Number = edittextnumber.getText().toString().trim();
            person.setName(Name);
            person.setNumber(Number);

            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constants.PERSON_INTENT_OBJECT, person);
            bundle.putBoolean(Constants.PERSON_INTENT_EDIT, isEdit);
            bundle.putInt(Constants.PERSON_INTENT_INDEX, index);
            intent.putExtras(bundle);

            setResult(RESULT_OK, intent);
            finish();
        });
    }
}