package com.app.restrauntspinner.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photos implements Parcelable {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("groups")
    @Expose
    private List<Group_> groups = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Group_> getGroups() {
        return groups;
    }

    public void setGroups(List<Group_> groups) {
        this.groups = groups;
    }


    protected Photos(Parcel in) {
        count = in.readByte() == 0x00 ? null : in.readInt();
        if (in.readByte() == 0x01) {
            groups = new ArrayList<Group_>();
            in.readList(groups, Group_.class.getClassLoader());
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
        if (groups == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(groups);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Photos> CREATOR = new Parcelable.Creator<Photos>() {
        @Override
        public Photos createFromParcel(Parcel in) {
            return new Photos(in);
        }

        @Override
        public Photos[] newArray(int size) {
            return new Photos[size];
        }
    };
}