package com.example.islam.carsviewertask.data.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by islam on 04/12/16.
 */

public class KeyValue implements Parcelable {
    public static final Parcelable.Creator<KeyValue> CREATOR = new Parcelable.Creator<KeyValue>() {
        @Override
        public KeyValue createFromParcel(Parcel source) {
            return new KeyValue(source);
        }

        @Override
        public KeyValue[] newArray(int size) {
            return new KeyValue[size];
        }
    };
    private String key, value;

    public KeyValue(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public KeyValue() {
    }

    protected KeyValue(Parcel in) {
        this.key = in.readString();
        this.value = in.readString();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.key);
        dest.writeString(this.value);
    }
}
