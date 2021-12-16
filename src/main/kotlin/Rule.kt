import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class Rule {
    val symbolProperty = SimpleStringProperty("")
    var symbol by symbolProperty

    val changeToProperty = SimpleStringProperty("")
    var changeTo by changeToProperty
}