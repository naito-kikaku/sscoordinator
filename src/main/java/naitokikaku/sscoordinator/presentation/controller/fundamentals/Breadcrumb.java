package naitokikaku.sscoordinator.presentation.controller.fundamentals;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.page.PageInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode
@ToString
public class Breadcrumb implements Serializable {
    List<PageInfo> list = new ArrayList<>();

    public Breadcrumb() {
    }

    public Breadcrumb(List<PageInfo> list) {
        this.list = list;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public PageInfo beforePage() {
        List<PageInfo> reversed = new ArrayList<>(list);
        Collections.reverse(reversed);
        reversed.remove(0);
        return reversed.get(0);

    }

    public List<PageInfo> asList() {
        return Collections.unmodifiableList(list);
    }
}
