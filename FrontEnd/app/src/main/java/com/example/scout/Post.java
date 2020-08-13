package com.example.scout;

import android.provider.MediaStore;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable {
    private int mImage;
    private String mPostName;
    private String mDescrip;

    private String mNeedRoles;
    private String mThumbnailDescription;
    private String mCompensation;
    private String mOwnerId;
    private String mOwnerName;

    public Post(int image, String name, String description, String needRoles, String thumbnailDescip, String compensation, String ownerID, String ownerName) {
        mImage = image;
        mPostName = name;
        mDescrip = description;

        mNeedRoles = needRoles;
        mThumbnailDescription = thumbnailDescip;
        mCompensation = compensation;
        mOwnerId = ownerID;
        mOwnerName = ownerName;
    }

    public String getmCompensation(){
        return mCompensation;
    }

    public String getNeedRoles(){
        return mNeedRoles;
    }

    public String getmThumbnailDescription(){
        return mThumbnailDescription;
    }

    public int getImage(){
        return mImage;
    }

    public String getPostName(){
        return mPostName;
    }

    public String getDescrip(){
        return mDescrip;
    }


    public String getmOwnerId() {
        return mOwnerId;
    }

    public void setmOwnerId(String mOwnerId) {
        this.mOwnerId = mOwnerId;
    }

    public String getmOwnerName() {
        return mOwnerName;
    }

    public void setmOwnerName(String mOwnerName) {
        this.mOwnerName = mOwnerName;
    }
}
