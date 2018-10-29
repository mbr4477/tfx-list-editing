package com.matthewrussell.tfxlistediting.home

import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.collections.FXCollections
import tornadofx.ViewModel

class HomeViewModel : ViewModel() {
    val items = FXCollections.observableArrayList<StringProperty>()
    val pojoItems = FXCollections.observableArrayList<String>()

    init {
        items.addAll((1..30).map { SimpleStringProperty(it.toString()) })
        items.map {
            it.addListener { _, old, new ->
                println("$old -> $new")
            }
        }

        pojoItems.addAll((1..30).map { it.toString() })
    }

    fun saveAll() {
        print("Property List: ")
        println(items)
        print("POJO List: ")
        println(pojoItems)
    }
}