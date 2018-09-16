package naitokikaku.sscoordinator.infrastructure.datasource.event.snapshot;

import naitokikaku.sscoordinator.domain.model.event.criteria.EventCriteria;
import naitokikaku.sscoordinator.domain.model.event.identity.EventId;
import naitokikaku.sscoordinator.domain.model.event.snapshot.EventSnapshot;
import naitokikaku.sscoordinator.domain.model.event.snapshot.EventSnapshotRepository;
import naitokikaku.sscoordinator.domain.model.event.snapshot.EventSnapshots;
import naitokikaku.sscoordinator.domain.model.fundamentals.pagination.response.PaginationResponse;
import naitokikaku.sscoordinator.domain.model.fundamentals.pagination.response.TotalElementSize;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class EventSnapshotDataSource implements EventSnapshotRepository {
    @Resource
    EventSnapshotMapper mapper;

    @Override
    public EventSnapshot get(EventId eventId) {
        // TODO impl
        return null;
    }

    @Override
    public EventSnapshots findBy(EventCriteria criteria) {
        Long count = mapper.countByCriteria(criteria);
        PaginationResponse paginationResponse = new PaginationResponse(criteria.pagination(), new TotalElementSize(count));
        List<EventSnapshot> list = mapper.findByCriteria(criteria);
        return new EventSnapshots(list, paginationResponse);
    }
}
