package br.com.douglasffilho.springkotlincrud.service

import br.com.douglasffilho.springkotlincrud.model.Customer
import br.com.douglasffilho.springkotlincrud.repository.CustomerRepository
import br.com.douglasffilho.springkotlincrud.util.HttpObject
import br.com.douglasffilho.springkotlincrud.util.PageObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CustomerService {

    @Autowired
    lateinit var customerRepository: CustomerRepository

    fun findCustomerByEmail(email: String): HttpObject<Customer> {
        val customer = customerRepository.findByEmail(email)
        if (customer != null) {
            return HttpObject(customer, "/customers/${customer.email}")
        }

        return HttpObject(null, "/customers/null")
    }

    fun findAllCustomers(page: Int, size: Int): PageObject<Customer> {
        val customers = customerRepository.findAll()
        return PageObject(customers, page, size, ref = "/customers?page=${page}&size=${size}")
    }

    fun findAllActive(active: Boolean, page: Int, size: Int): PageObject<Customer> {
        val customers = customerRepository.findAllActive(active)
        return PageObject(customers, page, size, "/customers/active?page=${page}&size=${size}")
    }

}