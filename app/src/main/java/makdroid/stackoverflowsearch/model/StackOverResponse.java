package makdroid.stackoverflowsearch.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grzecho on 01.07.2016.
 */
public class StackOverResponse {

    @SerializedName("items")
    public List<Item> items = new ArrayList<Item>();
    @SerializedName("has_more")
    public boolean hasMore;
    @SerializedName("quota_max")
    public int quotaMax;
    @SerializedName("quota_remaining")
    public int quotaRemaining;

}