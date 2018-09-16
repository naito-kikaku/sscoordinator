package naitokikaku.sscoordinator.domain.model.fundamentals.pagination.request;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
public class PaginationRequest implements Serializable {
    Page page = new Page(1L);
    PageElementSize pageElementSize = new PageElementSize(20L);

    public PaginationRequest() {
    }

    public PaginationRequest(Page page, PageElementSize pageElementSize) {
        this.page = page;
        this.pageElementSize = pageElementSize;
    }

    public PageElementSize pageElementSize() {
        return pageElementSize;
    }

    public Long limit() {
        return pageElementSize.asLong();
    }

    public Long offset() {
        return (page.asLong() - 1L) * pageElementSize.asLong();
    }

    public PaginationRequest rewrite(Page page) {
        return new PaginationRequest(page, pageElementSize);
    }

    public Page currentPage() {
        return page;
    }

    public Page prevPage() {
        return page.prev();
    }

    public Page nextPage() {
        return page.next();
    }
}
