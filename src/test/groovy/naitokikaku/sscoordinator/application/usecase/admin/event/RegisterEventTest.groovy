package naitokikaku.sscoordinator.application.usecase.admin.event

import naitokikaku.sscoordinator.TestApplication
import naitokikaku.sscoordinator.domain.model.event.Event
import naitokikaku.sscoordinator.domain.model.event.EventName
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
class RegisterEventTest extends Specification {
    @Resource
    RegisterEvent registerEvent

    def "イベントを登録できる"() {
        setup:
        Event event = new Event(new EventName("test event"))

        when:
        registerEvent.execute(event)

        then:
        // TODO assertion
        true
    }
}
