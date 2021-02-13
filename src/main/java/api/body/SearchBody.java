package api.body;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SearchBody {

    @Getter
    private List<SuggestionsBody> suggestions;

    @Getter
    private List<Pages> pages;

    @Getter
    private List<Object> products;

    @Getter
    private boolean showSeeAllProducts;

    @Getter
    private boolean showSeeAllPages;
}
