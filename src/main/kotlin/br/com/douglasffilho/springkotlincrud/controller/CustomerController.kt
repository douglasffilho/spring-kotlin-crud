package br.com.douglasffilho.springkotlincrud.controller

import br.com.douglasffilho.springkotlincrud.model.Customer
import br.com.douglasffilho.springkotlincrud.service.CustomerService
import br.com.douglasffilho.springkotlincrud.util.HttpObject
import br.com.douglasffilho.springkotlincrud.util.PageObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.notFound
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customers")
class CustomerController {

    @Autowired
    lateinit var customerService: CustomerService

    @GetMapping("/{email}")
    fun findCustomerByEmail(@PathVariable("email") email: String): ResponseEntity<HttpObject<Customer>> {
        val response = customerService.findCustomerByEmail(email)
        if (response.result != null) {
            return ok(response)
        }

        return notFound().build()
    }

    @GetMapping(value = ["", "/"], params = ["page", "size"])
    fun findAll(
            @RequestParam(value = "page", required = true) page: Int,
            @RequestParam(value = "size", required = true) size: Int
    ): ResponseEntity<PageObject<Customer>> {
        val response = customerService.findAllCustomers(page, size)
        return ok(response)
    }

    @GetMapping(value = ["/active/{active}"], params = ["page", "size"])
    fun findAllActive(
            @PathVariable("active") active: Boolean,
            @RequestParam(value = "page", required = true) page: Int,
            @RequestParam(value = "size", required = true) size: Int
    ): ResponseEntity<PageObject<Customer>> {
        val response = customerService.findAllActive(active, page, size)
        return ok(response)
    }

}