package naitokikaku.sscoordinator.infrastructure.datasource.event.snapshot;

import naitokikaku.sscoordinator.domain.model.event.criteria.EventCriteria;
import naitokikaku.sscoordinator.domain.model.event.identity.EventId;
import naitokikaku.sscoordinator.domain.model.event.snapshot.EventSnapshot;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EventSnapshotMapper {

    long countByCriteria(@Param("criteria") EventCriteria criteria);

    List<EventSnapshot> findByCriteria(@Param("criteria") EventCriteria criteria);

    EventSnapshot findById(@Param("eventId") EventId eventId);
}
