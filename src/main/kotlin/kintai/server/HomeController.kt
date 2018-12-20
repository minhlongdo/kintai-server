package kintai.server

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import kintai.server.repo.City
import kintai.server.repo.CityRepository

@Controller("/")
class HomeController(val repo:CityRepository) {


    @Get("/city")
    fun city(): List<City> {
        return repo.allCity()
    }

    @Post("/city")
    fun newCity(@Body city:City): Int {

        return repo.save(city)
    }

}