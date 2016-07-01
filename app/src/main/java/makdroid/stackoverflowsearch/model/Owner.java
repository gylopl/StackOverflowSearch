package makdroid.stackoverflowsearch.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Grzecho on 01.07.2016.
 */
public class Owner {

    @SerializedName("reputation")
    public int reputation;
    @SerializedName("user_id")
    public int userId;
    @SerializedName("user_type")
    public String userType;
    @SerializedName("profile_image")
    public String profileImage;
    @SerializedName("display_name")
    public String displayName;
    @SerializedName("link")
    public String link;

}