package com.micro.mysegmentdefault.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class BestTag implements Parcelable {

    public String id;
    public String name;
    public String url;

    public BestTag(){

    }

    protected BestTag(Parcel in) {
        id = in.readString();
        name = in.readString();
        url = in.readString();
    }

    public static final Creator<BestTag> CREATOR = new Creator<BestTag>() {
        @Override
        public BestTag createFromParcel(Parcel in) {
            return new BestTag(in);
        }

        @Override
        public BestTag[] newArray(int size) {
            return new BestTag[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(url);
    }
}