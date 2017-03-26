package net.codebuilders.mybusiness

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class FeedBuilderProxy extends GroovyObjectSupport {

    private Logger log = LoggerFactory.getLogger(getClass())

    private FeedBuilder builder

    FeedBuilderProxy(FeedBuilder builder) {
        this.builder = builder
    }

    // The builder will handle method invocations from within the node closures
    Object invokeMethod(String name, Object args) {
        return builder.handleNode(name, args)
    }

    // Handle property setting within closures such that it sets properties of the current builder node
    void setProperty(String property, Object newValue) {
        if (builder.current != null) {
            try {
                builder.current."$property" = newValue
                return
            }
            catch (Exception e) {
                log.error e.message, e
            }
        }
        super.setProperty(property, newValue)
    }

    // Handle property getting withing closures such that it gets properties of the current builder node
    def getProperty(String property) {
        if (builder.current != null) {
            try {
                return builder.current."$property"
            } catch (Exception e) {
                // Ouch this is expensive, don't rely on it for functionality
            }
        }
        return super.getProperty(property)
    }
}
