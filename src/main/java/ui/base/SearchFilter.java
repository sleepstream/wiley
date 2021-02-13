package ui.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class SearchFilter {

    @Getter
    private Integer subFilterCount;

    @Getter
    private String rootFilterTitle;

    @Getter
    private List<String> subFilterItems;
}
