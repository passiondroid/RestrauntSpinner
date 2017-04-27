package com.app.restrauntspinner.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item___ implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("createdAt")
    @Expose
    private Integer createdAt;
    @SerializedName("prefix")
    @Expose
    private String prefix;
    @SerializedName("suffix")
    @Expose
    private String suffix;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("visibility")
    @Expose
    private String visibility;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
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

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }


    protected Item___(Parcel in) {
        id = in.readString();
        createdAt = in.readByte() == 0x00 ? null : in.readInt();
        prefix = in.readString();
        suffix = in.readString();
        width = in.readByte() == 0x00 ? null : in.readInt();
        height = in.readByte() == 0x00 ? null : in.readInt();
        user = (User) in.readValue(User.class.getClassLoader());
        visibility = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        if (createdAt == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(createdAt);
        }
        dest.writeString(prefix);
        dest.writeString(suffix);
        if (width == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(width);
        }
        if (height == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(height);
        }
        dest.writeValue(user);
        dest.writeString(visibility);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Item___> CREATOR = new Parcelable.Creator<Item___>() {
        @Override
        public Item___ createFromParcel(Parcel in) {
            return new Item___(in);
        }

        @Override
        public Item___[] newArray(int size) {
            return new Item___[size];
        }
    };
}