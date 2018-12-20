package kintai.server

import io.micronaut.context.annotation.Value
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.discovery.event.ServiceStartedEvent
import io.micronaut.runtime.Micronaut
import kintai.server.repo.Cities
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import javax.annotation.PostConstruct
import javax.inject.Singleton

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("kintai.server")
                .mainClass(Application.javaClass)
                .start()
    }

    @PostConstruct
    fun setupSchema(db:Database, @Value("\${schema.gen}") genScheme:Boolean) {

        println("Start Scheme Generation of ${db}, flag- ${genScheme}")
        if (!genScheme) {
            return
        }
        transaction {
            SchemaUtils.create(Cities)
        }

    }
}

@Singleton
class DoOnStartup(val db:Database, @Value("\${schema.gen}") val genScheme:Boolean) : ApplicationEventListener<ServiceStartedEvent> {

    override fun onApplicationEvent(event:ServiceStartedEvent) {

        println("Start Scheme Generation of ${db}, flag- ${genScheme}")
        if (!genScheme) {
            return
        }
        transaction {
            SchemaUtils.create(Cities)
        }
    }
}