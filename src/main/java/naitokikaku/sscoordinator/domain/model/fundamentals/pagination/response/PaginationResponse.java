package naitokikaku.sscoordinator.domain.model.fundamentals.pagination.response;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import naitokikaku.sscoordinator.domain.model.fundamentals.pagination.request.PaginationRequest;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
public class PaginationResponse implements Serializable {
    PaginationRequest request = new PaginationRequest();
    TotalElementSize totalElementSize = new TotalElementSize(0L);

    public PaginationResponse() {
    }

    public PaginationResponse(PaginationRequest request, TotalElementSize totalElementSize) {
        this.request = request;
        this.totalElementSize = totalElementSize;
    }

    public TotalElementSize totalElementSize() {
        return totalElementSize;
    }

    public TotalPageSize totalPageSize() {
        return TotalPageSize.of(totalElementSize, request.pageElementSize());
    }
}
