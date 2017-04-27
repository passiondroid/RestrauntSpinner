package com.app.restrauntspinner.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tip implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("createdAt")
    @Expose
    private Integer createdAt;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("canonicalUrl")
    @Expose
    private String canonicalUrl;
    @SerializedName("likes")
    @Expose
    private Likes likes;
    @SerializedName("logView")
    @Expose
    private Boolean logView;
    @SerializedName("agreeCount")
    @Expose
    private Integer agreeCount;
    @SerializedName("disagreeCount")
    @Expose
    private Integer disagreeCount;
    @SerializedName("user")
    @Expose
    private User_ user;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCanonicalUrl() {
        return canonicalUrl;
    }

    public void setCanonicalUrl(String canonicalUrl) {
        this.canonicalUrl = canonicalUrl;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    public Boolean getLogView() {
        return logView;
    }

    public void setLogView(Boolean logView) {
        this.logView = logView;
    }

    public Integer getAgreeCount() {
        return agreeCount;
    }

    public void setAgreeCount(Integer agreeCount) {
        this.agreeCount = agreeCount;
    }

    public Integer getDisagreeCount() {
        return disagreeCount;
    }

    public void setDisagreeCount(Integer disagreeCount) {
        this.disagreeCount = disagreeCount;
    }

    public User_ getUser() {
        return user;
    }

    public void setUser(User_ user) {
        this.user = user;
    }


    protected Tip(Parcel in) {
        id = in.readString();
        createdAt = in.readByte() == 0x00 ? null : in.readInt();
        text = in.readString();
        type = in.readString();
        canonicalUrl = in.readString();
        likes = (Likes) in.readValue(Likes.class.getClassLoader());
        byte logViewVal = in.readByte();
        logView = logViewVal == 0x02 ? null : logViewVal != 0x00;
        agreeCount = in.readByte() == 0x00 ? null : in.readInt();
        disagreeCount = in.readByte() == 0x00 ? null : in.readInt();
        user = (User_) in.readValue(User_.class.getClassLoader());
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
        dest.writeString(text);
        dest.writeString(type);
        dest.writeString(canonicalUrl);
        dest.writeValue(likes);
        if (logView == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (logView ? 0x01 : 0x00));
        }
        if (agreeCount == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(agreeCount);
        }
        if (disagreeCount == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(disagreeCount);
        }
        dest.writeValue(user);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Tip> CREATOR = new Parcelable.Creator<Tip>() {
        @Override
        public Tip createFromParcel(Parcel in) {
            return new Tip(in);
        }

        @Override
        public Tip[] newArray(int size) {
            return new Tip[size];
        }
    };
}