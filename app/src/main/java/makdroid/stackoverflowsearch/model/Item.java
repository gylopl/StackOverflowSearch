package makdroid.stackoverflowsearch.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grzecho on 01.07.2016.
 */
public class Item {

    @SerializedName("tags")
    public List<String> tags = new ArrayList<String>();
    @SerializedName("owner")
    public Owner owner;
    @SerializedName("is_answered")
    public boolean isAnswered;
    @SerializedName("view_count")
    public int viewCount;
    @SerializedName("answer_count")
    public int answerCount;
    @SerializedName("score")
    public int score;
    @SerializedName("last_activity_date")
    public int lastActivityDate;
    @SerializedName("creation_date")
    public int creationDate;
    @SerializedName("question_id")
    public int questionId;
    @SerializedName("link")
    public String link;
    @SerializedName("title")
    public String title;
    @SerializedName("last_edit_date")
    public int lastEditDate;

}