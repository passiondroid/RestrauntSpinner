package com.app.restrauntspinner.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HereNow implements Parcelable {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("groups")
    @Expose
    private List<Object> groups = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<Object> getGroups() {
        return groups;
    }

    public void setGroups(List<Object> groups) {
        this.groups = groups;
    }


    protected HereNow(Parcel in) {
        count = in.readByte() == 0x00 ? null : in.readInt();
        summary = in.readString();
        if (in.readByte() == 0x01) {
            groups = new ArrayList<Object>();
            in.readList(groups, Object.class.getClassLoader());
        } else {
            groups = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (count == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(count);
        }
        dest.writeString(summary);
        if (groups == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(groups);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<HereNow> CREATOR = new Parcelable.Creator<HereNow>() {
        @Override
        public HereNow createFromParcel(Parcel in) {
            return new HereNow(in);
        }

        @Override
        public HereNow[] newArray(int size) {
            return new HereNow[size];
        }
    };
}