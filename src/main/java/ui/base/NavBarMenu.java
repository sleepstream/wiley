package ui.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class NavBarMenu {

    @Getter
    private Integer subMenuCount;

    @Getter
    private String rootItemTitle;

    @Getter
    private List<String> subMenuItems;
}
