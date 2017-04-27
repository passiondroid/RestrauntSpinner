package com.app.restrauntspinner.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BeenHere implements Parcelable {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("marked")
    @Expose
    private Boolean marked;
    @SerializedName("lastCheckinExpiredAt")
    @Expose
    private Integer lastCheckinExpiredAt;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Boolean getMarked() {
        return marked;
    }

    public void setMarked(Boolean marked) {
        this.marked = marked;
    }

    public Integer getLastCheckinExpiredAt() {
        return lastCheckinExpiredAt;
    }

    public void setLastCheckinExpiredAt(Integer lastCheckinExpiredAt) {
        this.lastCheckinExpiredAt = lastCheckinExpiredAt;
    }


    protected BeenHere(Parcel in) {
        count = in.readByte() == 0x00 ? null : in.readInt();
        byte markedVal = in.readByte();
        marked = markedVal == 0x02 ? null : markedVal != 0x00;
        lastCheckinExpiredAt = in.readByte() == 0x00 ? null : in.readInt();
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
        if (marked == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (marked ? 0x01 : 0x00));
        }
        if (lastCheckinExpiredAt == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(lastCheckinExpiredAt);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<BeenHere> CREATOR = new Parcelable.Creator<BeenHere>() {
        @Override
        public BeenHere createFromParcel(Parcel in) {
            return new BeenHere(in);
        }

        @Override
        public BeenHere[] newArray(int size) {
            return new BeenHere[size];
        }
    };
}