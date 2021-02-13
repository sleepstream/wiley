package api.body;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

public class SuggestionsBody {

    @SerializedName("term")
    @Getter
    private String term;
}
