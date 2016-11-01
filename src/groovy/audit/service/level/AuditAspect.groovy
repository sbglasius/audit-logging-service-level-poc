package audit.service.level

import grails.plugin.springevents.AsyncEventPublisher
import groovy.util.logging.Log4j
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsWebRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher

@Aspect
@Log4j
class AuditAspect {
    @Autowired
    AsyncEventPublisher asyncEventPublisher

    //A more generic advice would be as below
    //@Before("execution(* com.test.*.*(..))")
    @Before(value = "@annotation(audit) && target(bean)", argNames = 'bean,audit')
    def beforeMethod(JoinPoint jp, Object bean, Audit audit) {

        try {
            String description = audit.description() ?: audit.value() ?: jp.this.getClass().canonicalName
            String method = jp.signature.name
            Object[] args = jp.args


            AuditEvent auditEvent = new AuditEvent(bean)
            auditEvent.description = description
            auditEvent.methodName = method
            auditEvent.args = args

            println "Sending event: $auditEvent"
            asyncEventPublisher.publishEvent(auditEvent)
        } catch (e) {
            log.warn(e.message, e)
        }


    }

    //A more generic advice would be as below
    //@Around("execution(* com.test.*.*(..))")
    @After("execution(* *.serviceMethod(..))")
    def afterMethod() {
        println '-- After Method.'
    }
}
