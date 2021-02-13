package api.body;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

public class Pages {

    @Getter
    private String id;

    @Getter
    private String boost;

    @Getter
    private String title;

    @Getter
    private String url;

    @Getter
    private String content;
}
