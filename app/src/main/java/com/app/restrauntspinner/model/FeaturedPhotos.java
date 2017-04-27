package com.app.restrauntspinner.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeaturedPhotos implements Parcelable {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("items")
    @Expose
    private List<Item___> items = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Item___> getItems() {
        return items;
    }

    public void setItems(List<Item___> items) {
        this.items = items;
    }


    protected FeaturedPhotos(Parcel in) {
        count = in.readByte() == 0x00 ? null : in.readInt();
        if (in.readByte() == 0x01) {
            items = new ArrayList<Item___>();
            in.readList(items, Item___.class.getClassLoader());
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
    public static final Parcelable.Creator<FeaturedPhotos> CREATOR = new Parcelable.Creator<FeaturedPhotos>() {
        @Override
        public FeaturedPhotos createFromParcel(Parcel in) {
            return new FeaturedPhotos(in);
        }

        @Override
        public FeaturedPhotos[] newArray(int size) {
            return new FeaturedPhotos[size];
        }
    };
}