package com.example.islam.carsviewertask.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Created by islam on 03/12/16.
 */

public class CarModel implements Parcelable {
    public static final Parcelable.Creator<CarModel> CREATOR = new Parcelable.Creator<CarModel>() {
        @Override
        public CarModel createFromParcel(Parcel source) {
            return new CarModel(source);
        }

        @Override
        public CarModel[] newArray(int size) {
            return new CarModel[size];
        }
    };
    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("pageSize")
    @Expose
    private int pageSize;
    @SerializedName("totalPageCount")
    @Expose
    private int totalPageCount;
    @SerializedName("wkda")
    @Expose
    private HashMap<String, String> carData;

    public CarModel() {
    }

    protected CarModel(Parcel in) {
        this.page = in.readInt();
        this.pageSize = in.readInt();
        this.totalPageCount = in.readInt();
        this.carData = (HashMap<String, String>) in.readSerializable();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public HashMap<String, String> getCarData() {
        return carData;
    }

    public void setCarData(HashMap<String, String> carData) {
        this.carData = carData;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.page);
        dest.writeInt(this.pageSize);
        dest.writeInt(this.totalPageCount);
        dest.writeSerializable(this.carData);
    }
}
