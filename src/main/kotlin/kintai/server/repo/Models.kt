package kintai.server.repo

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable

// DSL pattern
object Cities: IntIdTable() {
    val name = varchar("name", 50)
    val poputation = integer("poputation")
}
// DAO pattern
class CityEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<CityEntity>(Cities)

    var name by Cities.name

    var population by Cities.poputation
}


data class City(val id:Int, val name:String, val population:Int)