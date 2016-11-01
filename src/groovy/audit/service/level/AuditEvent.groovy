package audit.service.level

import groovy.transform.Canonical
import groovy.transform.InheritConstructors
import groovy.transform.ToString
import org.springframework.context.ApplicationEvent

@Canonical
@InheritConstructors
class AuditEvent extends ApplicationEvent {
    String description
    String methodName
    Object[] args
}
