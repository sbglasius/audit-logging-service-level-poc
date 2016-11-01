package audit.service.level

import grails.converters.JSON

class DemoController {
    def demoService

    def index() {
        render(demoService.serviceMethod('Hello',42, [meaning:'of life']) as JSON)
    }
}
