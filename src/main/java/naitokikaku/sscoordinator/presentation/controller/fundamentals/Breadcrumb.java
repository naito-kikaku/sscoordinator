package naitokikaku.sscoordinator.presentation.controller.fundamentals;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.page.PageInformation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode
@ToString
public class Breadcrumb implements Serializable {
    List<PageInformation> list = new ArrayList<>();

    public Breadcrumb() {
    }

    public Breadcrumb(List<PageInformation> list) {
        this.list = list;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public PageInformation beforePage() {
        List<PageInformation> reversed = new ArrayList<>(list);
        Collections.reverse(reversed);
        reversed.remove(0);
        return reversed.get(0);

    }

    public List<PageInformation> asList() {
        return Collections.unmodifiableList(list);
    }
}
