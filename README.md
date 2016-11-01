Audit Logging at Method Level
=

This project introduces a few ideas I have for auditing at method level. 

It contains the following components

* An `@Audit` annotation to be used on methods
```
    @Audit('demoService')
    def serviceMethod(String arg1, Long arg2, Map arg3) {
        println "$arg1 $arg2 $arg3"

        return [success: true, message: "What do you know!"]
    }
```

* An AOP aspect `AuditAspect` that pointcuts at all methods annotated with `@Audit`
  * Creates an `AuditEvent` object which contains information about the method name, and arguments used for the call
  * Uses Spring-Events to asynchronously dispatch an event to the `AuditService`
* `AuditService` receves the event
  * If the `AuditService` downstream is unavailable (simulated) then it the `Spring-Events` plugin has a backoff and retry mechanism.
  
To demo, there is a `DemoController` calling a `DemoService`

Further ideas
---
The `@Audit` annotation could hold information about, how often an event should be recorded, and the `AuditService` could determine if the same user has done the same operation within a certain time limit (read a `Person` object for example)