package naitokikaku.sscoordinator.application.usecase.event

import naitokikaku.sscoordinator.TestApplication
import naitokikaku.sscoordinator.domain.model.event.Event
import naitokikaku.sscoordinator.domain.model.event.EventDescription
import naitokikaku.sscoordinator.domain.model.event.EventName
import naitokikaku.sscoordinator.domain.model.event.identity.EventId
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
class RegisterEventSpec extends Specification {
    @Resource
    RegisterEvent registerEvent

    def "イベントを登録できる"() {
        setup:
        Event event = new Event(new EventId(), new EventName("test event"), new EventDescription("test description"))

        when:
        registerEvent.execute(event)

        then:
        // TODO assertion
        true
    }
}
