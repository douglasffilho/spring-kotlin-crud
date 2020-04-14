package br.com.douglasffilho.springkotlincrud.util

class PageObject<T>(var items: List<T>, var page: Int, var size: Int, var ref: String) {

    init {
        if (this.page < 1) {
            this.page = 1
        }

        if (this.size < 1) {
            this.size = 1
        }
    }

}