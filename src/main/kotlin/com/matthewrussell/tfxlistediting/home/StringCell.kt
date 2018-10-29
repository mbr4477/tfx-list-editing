package com.matthewrussell.tfxlistediting.home

import javafx.scene.layout.Priority
import tornadofx.*

class StringCell : ListCellFragment<String>() {
    override val root = hbox {
        useMaxWidth = true
        maxWidth = Double.MAX_VALUE
        textfield(itemProperty) {
            hgrow = Priority.ALWAYS
            managedProperty().bind(visibleProperty())
            editableProperty().bind(editingProperty)
            maxWidth = Double.MAX_VALUE
            visibleWhen {
                editingProperty
            }
        }
        label(itemProperty) {
            hgrow = Priority.ALWAYS
            maxWidth = Double.MAX_VALUE
            managedProperty().bind(visibleProperty())
            visibleWhen {
                editingProperty.not()
            }
        }
        button("Edit") {
            action {
                if (editing) {
                    // Equivalent to item = item, but also exits editing state and fires the editing event
                    commitEdit(item)
                    text = "Edit"
                } else {
                    startEdit()
                    text = "Save"
                }
            }
        }
    }
}