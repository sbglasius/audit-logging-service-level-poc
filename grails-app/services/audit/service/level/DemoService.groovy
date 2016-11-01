package audit.service.level

import grails.transaction.Transactional
import org.springframework.context.ApplicationListener

@Transactional
class DemoService  {

    @Audit('demoService')
    def serviceMethod(String arg1, Long arg2, Map arg3) {
        println "$arg1 $arg2 $arg3"

        return [success: true, message: "What do you know!"]
    }

}
