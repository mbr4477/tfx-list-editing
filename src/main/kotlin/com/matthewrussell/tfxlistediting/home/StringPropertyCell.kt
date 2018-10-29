package com.matthewrussell.tfxlistediting.home

import javafx.beans.property.StringProperty
import javafx.scene.layout.Priority
import tornadofx.*

class StringPropertyCell : ListCellFragment<StringProperty>() {

    val textField = textfield("Default Text") {
        hgrow = Priority.ALWAYS
        managedProperty().bind(visibleProperty())
        editableProperty().bind(editingProperty)
        maxWidth = Double.MAX_VALUE
        visibleWhen {
            editingProperty
        }
    }

    val textLabel = label("Default Text") {
        hgrow = Priority.ALWAYS
        maxWidth = Double.MAX_VALUE
        managedProperty().bind(visibleProperty())
        visibleWhen {
            editingProperty.not()
        }
    }

    override val root = hbox {
        useMaxWidth = true
        maxWidth = Double.MAX_VALUE
        add(textField)
        add(textLabel)
        button("Edit") {
            action {
                if (editing) {
                    // Reassigns to item. This is equivalent to item = item,
                    // but also exits editing state and fires event
                    commitEdit(item)
                    text = "Edit"
                } else {
                    startEdit()
                    text = "Save"
                }
            }
        }
    }

    init {
        itemProperty.addListener { _, old, new ->
            if (old != null) {
                textField.textProperty().unbindBidirectional(old)
                textLabel.textProperty().unbind()
            }
            if (new != null) {
                textField.textProperty().bindBidirectional(new)
                textLabel.textProperty().bind(new)
            }
        }
    }
}