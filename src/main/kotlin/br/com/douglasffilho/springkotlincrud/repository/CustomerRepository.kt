package br.com.douglasffilho.springkotlincrud.repository

import br.com.douglasffilho.springkotlincrud.model.Customer
import org.springframework.stereotype.Repository

@Repository
class CustomerRepository {
    private val customers: List<Customer> = listOf(
            Customer("Douglas Fernandes", "douglasf.filho@gmail.com", true),
            Customer("Douglas Filho", "douglasffilho@github.io", false)
    )

    fun findAll(): List<Customer> {
        return customers
    }

    fun findAllActive(active: Boolean): List<Customer> {
        return customers.filter{ customer: Customer -> customer.active == active }
    }

    fun findByEmail(email: String): Customer? {
        return customers.find { customer: Customer -> customer.email == email }
    }

}