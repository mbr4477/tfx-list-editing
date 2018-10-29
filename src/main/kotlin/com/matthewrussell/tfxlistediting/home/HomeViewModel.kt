package com.matthewrussell.tfxlistediting.home

import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.collections.FXCollections
import tornadofx.ViewModel

class HomeViewModel : ViewModel() {
    val items = FXCollections.observableArrayList<StringProperty>()
    val pojoItems = FXCollections.observableArrayList<String>()

    init {
        items.addAll(
                listOf("Property 1", "Property 2", "Property 3").map { SimpleStringProperty(it) }
        )
        items.map {
            it.addListener { _, old, new ->
                println("$old -> $new")
            }
        }

        pojoItems.addAll(listOf("POJO 1", "POJO 2", "POJO 3"))
    }

    fun saveAll() {
        print("Property List: ")
        println(items)
        print("POJO List: ")
        println(pojoItems)
    }
}