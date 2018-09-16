package naitokikaku.sscoordinator.application.usecase.event

import naitokikaku.sscoordinator.TestApplication
import naitokikaku.sscoordinator.domain.model.event.Event
import naitokikaku.sscoordinator.domain.model.event.EventDescription
import naitokikaku.sscoordinator.domain.model.event.EventName
import naitokikaku.sscoordinator.domain.model.event.criteria.EventCriteria
import naitokikaku.sscoordinator.domain.model.event.criteria.LikeEventNameOrDescription
import naitokikaku.sscoordinator.domain.model.event.identity.EventId
import naitokikaku.sscoordinator.domain.model.fundamentals.pagination.request.PaginationRequest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

import javax.annotation.Resource

@ContextConfiguration
@SpringBootTest(classes = TestApplication)
@Transactional
@Rollback
class SearchEventSpec extends Specification {
    @Resource
    RegisterEvent registerEvent
    @Resource
    SearchEvent searchEvent

    def "イベントを検索できる"() {
        setup:
        def event = new Event(new EventId(), new EventName("test event"), new EventDescription())
        registerEvent.execute(event)
        def criteria = new EventCriteria(new LikeEventNameOrDescription("test"), new PaginationRequest())

        when:
        def searchResult = searchEvent.execute(criteria)

        then:
        // TODO assertion
        true
    }

}
