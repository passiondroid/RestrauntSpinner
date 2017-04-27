package com.app.restrauntspinner.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reasons implements Parcelable {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("items")
    @Expose
    private List<Item_> items = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Item_> getItems() {
        return items;
    }

    public void setItems(List<Item_> items) {
        this.items = items;
    }


    protected Reasons(Parcel in) {
        count = in.readByte() == 0x00 ? null : in.readInt();
        if (in.readByte() == 0x01) {
            items = new ArrayList<Item_>();
            in.readList(items, Item_.class.getClassLoader());
        } else {
            items = null;
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
        if (items == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(items);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Reasons> CREATOR = new Parcelable.Creator<Reasons>() {
        @Override
        public Reasons createFromParcel(Parcel in) {
            return new Reasons(in);
        }

        @Override
        public Reasons[] newArray(int size) {
            return new Reasons[size];
        }
    };
}