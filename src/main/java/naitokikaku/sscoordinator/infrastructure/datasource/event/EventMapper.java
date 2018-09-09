package naitokikaku.sscoordinator.infrastructure.datasource.event;

import naitokikaku.sscoordinator.domain.model.event.Event;
import naitokikaku.sscoordinator.domain.model.event.identity.EventId;
import naitokikaku.sscoordinator.domain.model.event.revision.EventRevisionId;
import naitokikaku.sscoordinator.domain.model.event.revision.EventRevisionNumber;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EventMapper {

    EventId nextEventId();

    EventRevisionId nextEventRevisionId();

    EventRevisionNumber nextEventRevisionNumber(@Param("eventId") EventId eventId);

    void store(@Param("eventId") EventId eventId);

    void storeRevision(@Param("eventRevisionId") EventRevisionId eventRevisionId,
                       @Param("eventId") EventId eventId,
                       @Param("eventRevisionNumber") EventRevisionNumber eventRevisionNumber,
                       @Param("event") Event event);

    void storeLatestPointer(@Param("eventId") EventId eventId,
                            @Param("eventRevisionId") EventRevisionId eventRevisionId,
                            @Param("eventRevisionNumber") EventRevisionNumber eventRevisionNumber);


}
