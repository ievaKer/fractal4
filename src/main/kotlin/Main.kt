import javafx.stage.Stage
import tornadofx.launch

class App : tornadofx.App(MainView::class) {
    override fun start(stage: Stage) {
        super.start(stage)
        stage.isMaximized = true
    }
}

fun main() {
    launch<App>()
}