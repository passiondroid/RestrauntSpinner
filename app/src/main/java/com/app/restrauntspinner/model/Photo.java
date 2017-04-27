package com.app.restrauntspinner.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo implements Parcelable {

    @SerializedName("prefix")
    @Expose
    private String prefix;
    @SerializedName("suffix")
    @Expose
    private String suffix;
    @SerializedName("default")
    @Expose
    private Boolean _default;

    public Boolean get_default() {
        return _default;
    }

    public void set_default(Boolean _default) {
        this._default = _default;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }


    protected Photo(Parcel in) {
        prefix = in.readString();
        suffix = in.readString();
        byte _defaultVal = in.readByte();
        _default = _defaultVal == 0x02 ? null : _defaultVal != 0x00;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(prefix);
        dest.writeString(suffix);
        if (_default == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (_default ? 0x01 : 0x00));
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Photo> CREATOR = new Parcelable.Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };
}