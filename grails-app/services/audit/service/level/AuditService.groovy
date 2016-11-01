package audit.service.level

import grails.plugin.springevents.RetryPolicy
import grails.plugin.springevents.RetryableFailureException
import grails.transaction.Transactional
import org.springframework.context.ApplicationListener

@Transactional
class AuditService implements ApplicationListener<AuditEvent> {
    final AuditRetryPolicy retryPolicy = new AuditRetryPolicy()

    @Override
    void onApplicationEvent(AuditEvent auditEvent) {
        // Simulate an unstable receiver of events
        if(new Random().nextBoolean()) {
            throw new RetryableFailureException("Failing by random")
        }
        println "Received event: $auditEvent ${new Date(auditEvent.timestamp).format('yyyy-MM-dd HH:mm:ss.SSS')}"
    }
}
