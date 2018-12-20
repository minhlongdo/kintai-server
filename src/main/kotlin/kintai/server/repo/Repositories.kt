package kintai.server.repo

import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.core.annotation.Blocking
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import javax.inject.Singleton
import javax.sql.DataSource

@Factory
class DBSettingsFactory {

    @Bean
    @Singleton
    fun db(ds:DataSource):Database {
        val db = Database.connect(ds)
        return db
    }
}

@Singleton
@Blocking
class CityRepository(db:Database) {


    fun allCity():List<City> = transaction {

        Cities.selectAll().map {
            City( it[Cities.id].value, it[Cities.name], it[Cities.poputation])
        }
    }

    fun save(city:City):Int= transaction {
        val newCity = CityEntity.new{
            name = city.name
            population = city.population
        }
        newCity.id.value
        /*
        val id = Cities.insertAndGetId {
            it[Cities.name] = city.name
            it[Cities.poputation] = city.population
        }
        id.value
        */

    }

}