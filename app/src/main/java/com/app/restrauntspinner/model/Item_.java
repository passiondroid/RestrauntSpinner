package com.app.restrauntspinner.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item_ implements Parcelable {

    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("reasonName")
    @Expose
    private String reasonName;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReasonName() {
        return reasonName;
    }

    public void setReasonName(String reasonName) {
        this.reasonName = reasonName;
    }


    protected Item_(Parcel in) {
        summary = in.readString();
        type = in.readString();
        reasonName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(summary);
        dest.writeString(type);
        dest.writeString(reasonName);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Item_> CREATOR = new Parcelable.Creator<Item_>() {
        @Override
        public Item_ createFromParcel(Parcel in) {
            return new Item_(in);
        }

        @Override
        public Item_[] newArray(int size) {
            return new Item_[size];
        }
    };
}