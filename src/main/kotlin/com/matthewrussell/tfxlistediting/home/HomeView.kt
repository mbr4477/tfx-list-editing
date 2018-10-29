package com.matthewrussell.tfxlistediting.home

import javafx.beans.property.SimpleStringProperty
import javafx.event.EventHandler
import javafx.scene.control.ListView
import tornadofx.*

class HomeView : View() {
    init {
        importStylesheet<HomeStyles>()
    }
    val viewModel: HomeViewModel by inject()

    override val root = hbox {
        listview(viewModel.items) {
            isEditable = true
            cellFragment(StringPropertyCell::class)
            // No need to explicitly update the value
        }
        listview(viewModel.pojoItems) {
            isEditable = true
            onEditCommit = EventHandler<ListView.EditEvent<String>> {
                // Need to explicitly update the value
                items[it.index] = it.newValue
            }
            cellFragment(StringCell::class)
        }
        button("Save") {
            action {
                viewModel.saveAll()
            }
        }
        button("Add") {
            action {
                viewModel.items.add(SimpleStringProperty("New Element"))
                viewModel.pojoItems.add("New Element")
            }
        }
    }
}