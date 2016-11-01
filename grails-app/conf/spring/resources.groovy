import audit.service.level.AuditAspect

// Place your Spring DSL code here
beans = {
    auditAspect(AuditAspect)

    xmlns aop: "http://www.springframework.org/schema/aop"
    aop {
        config("proxy-target-class": true) {
            aspect(id: "auditAspectService", ref: "auditAspect")
        }
    }
}
