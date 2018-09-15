package naitokikaku.sscoordinator.presentation.controller.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode
@ToString
@Getter
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
}
