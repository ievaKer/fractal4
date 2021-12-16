import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import java.lang.StringBuilder

class MainViewModel: ViewModel() {
    val rulesProperty = SimpleListProperty(mutableListOf<Rule>().toObservable())
    var rules by rulesProperty

    val axiomProperty = SimpleStringProperty("F")
    var axiom by axiomProperty

    val isTreeProperty = SimpleBooleanProperty(false)
    var isTree by isTreeProperty

    private val currentRuleProperty = SimpleStringProperty()
    var currentRule: String by currentRuleProperty

    val angleProperty = SimpleDoubleProperty(45.0)
    var angle by angleProperty

    val lengthProperty = SimpleDoubleProperty(1.0)
    var length by lengthProperty

    var iterationCount = 0

    fun nextIteration() {
        iterationCount += 1
        if (iterationCount == 1) currentRule = axiom
        val newRuleBuilder = StringBuilder()
        currentRule.forEach {
            var isAdded = false
            for (rule in rules) {
                if (rule.symbol[0] == it) {
                    newRuleBuilder.append(rule.changeTo)
                    isAdded = true
                    break
                }
            }

            if (!isAdded) newRuleBuilder.append(it)
        }

        currentRule = newRuleBuilder.toString()
    }

    fun resetRule() {
        iterationCount = 0
    }
}
