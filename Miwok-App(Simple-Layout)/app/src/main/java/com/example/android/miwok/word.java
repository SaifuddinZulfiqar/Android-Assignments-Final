package com.example.android.miwok;

/**
 * Created by arora on 26/6/16.
 */

public class word {

    private String mDefaultTranslation;

    private String mMiwokTranslation;

    /** Image resource ID for the word */
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    /** Constant value that represents no image was provided for this word */
    private static final int NO_IMAGE_PROVIDED = -1;

    private int mAudioResourceId;

    public word(String defaultTranslation, String miwokTranslation){

        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;

    }

    public word(String defaultTranslation, String miwokTranslation, int imageResouceId){

        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = imageResouceId;

    }

    public String getDefaultTranslation() {

        return  mDefaultTranslation;
    }


    public String getMiwokTranslation(){

        return  mMiwokTranslation;
    }

    public int getImageResourceId(){
        return mImageResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

}
