import LSymbol.*
import javafx.scene.layout.Pane
import javafx.scene.shape.Line
import tornadofx.add
import kotlin.math.cos
import kotlin.math.sin

class Turtle(private val pane: Pane) {
    private var currentX: Double = 100.0
    private var currentY: Double = 700.0

    private var stepSize: Double = 75.0

    private var angle: Double = 90.0
    var turnAngle: Double = 60.0

    private var isPenDown: Boolean = true

    fun step() {
        val angleRadians = Math.toRadians(angle)
        val endX = currentX + stepSize * sin(angleRadians)
        val endY = currentY + stepSize* cos(angleRadians)

        if (isPenDown) {
            val line = Line().apply {
                startX = currentX
                startY = currentY

                this.endX = endX
                this.endY = endY
            }

            pane.add(line)
        }

        currentX = endX
        currentY = endY
    }

    fun turn(degrees: Double) {
        angle += degrees
        angle %= 360
        if (angle < 0)
            angle += 360
    }

    fun decreaseStep(size: Double) {
        stepSize *= size
    }

    fun penUp() {
        isPenDown = false
    }

    fun penDown() {
        isPenDown = true
    }

    fun position(x: Double = 100.0, y: Double = 700.0) {
        currentX = x
        currentY = y
    }

    fun drawRule(rule: String) {
        draw(LSymbol.parse(rule))
    }

    fun setTree(isTree: Boolean) {
        if (isTree) {
            angle = 180.0
            position(700.0, 800.0)
        } else {
            angle = 90.0
            position()
        }
    }

    fun reset() {
        stepSize = 75.0
    }

    private fun draw(symbols: List<LSymbol>) {
        symbols.forEach {
            when (it) {
                P -> turn(turnAngle)
                M -> turn(-turnAngle)
                F -> step()
                I -> {
                    penUp()
                    step()
                    penDown()
                }
                L -> draw(it.value)
                R -> draw(it.value)
                S -> draw(it.value)
                Z -> draw(it.value)
                BO -> startBranch()
                BC -> endBranch()
                OTHER -> {}
            }
        }
    }

    private val branches = mutableListOf<Pair<Pair<Double, Double>, Double>>()
    private fun startBranch() {
        println("Starting branch at $currentX $currentY $angle")
        branches.add(Pair(Pair(currentX, currentY), angle))
    }

    private fun endBranch() {
        val prev = branches.removeLast()
        val pos = prev.first
        val angle = prev.second
        position(pos.first, pos.second)
        setAngle(angle)
        println("Returning to $currentX $currentY ${this.angle}")
    }

    private fun setAngle(angle: Double) {
        this.angle = angle
    }
}