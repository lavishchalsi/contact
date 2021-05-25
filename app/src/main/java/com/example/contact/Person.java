package com.example.contact;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable
{
private  String name;
private String number;
public Person() {
    name=number="";
}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public static Creator<Person> getCREATOR() {
        return CREATOR;
    }

    public Person(String name, String number) {
        this.name = name;
        this.number = number;

    }

    protected Person(Parcel in) {
        name = in.readString();
        number = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(number);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}



