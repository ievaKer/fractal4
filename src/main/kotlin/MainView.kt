import javafx.geometry.Pos
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import tornadofx.*

// FF+[+F-F-F]-[-F+F+F]
class MainView : View() {
    private val model: MainViewModel by inject()
    private val drawPane = pane()
    private val turtle = Turtle(drawPane)

    private val rulesList = vbox {
        style {
            spacing = 10.px
        }
    }

    private fun addNewRule() =
        vbox {
            style {
                spacing = 10.px
                backgroundRadius += box(5.px)
                backgroundColor += Color.LIGHTGREY
                borderRadius += box(5.px)
                borderColor += box(Color.DARKGRAY)
                borderWidth += box(1.px)
                padding = box(10.px)
            }

            val rule = Rule()
            model.rules.add(rule)

            hbox {
                val lab = label {
                    useMaxWidth = true
                    textProperty().bind(model.rulesProperty.stringBinding {
                        "Rule ${
                            it?.indexOf(rule)?.plus(1)
                        }"
                    })
                }

                button("Remove") {
                    style {
                        fontSize = 10.pt
                    }

                    action {
                        model.rules.remove(rule)
                        rulesList.children.remove(this@vbox)
                    }
                }

                add(lab)

                HBox.setHgrow(lab, Priority.ALWAYS)
            }

            hbox(alignment = Pos.CENTER) {
                label("Symbol: ") {
                    prefWidth = 70.0
                }

                textfield(rule.symbolProperty)
            }

            hbox(alignment = Pos.CENTER) {
                label("Change to: ") {
                    prefWidth = 70.0
                }

                textfield(rule.changeToProperty)
            }
        }

    override val root = borderpane {
        center = drawPane

        left = vbox {
            style {
                backgroundColor += Color.LIGHTGREY
                padding = box(10.px)
                spacing = 10.px
            }

            hbox(alignment = Pos.CENTER_LEFT) {
                spacing = 10.0

                label("Axiom") {
                    prefWidth = 50.0
                }

                textfield(model.axiomProperty)
            }

            hbox(alignment = Pos.CENTER_LEFT) {
                spacing = 10.0

                label("Angle") {
                    prefWidth = 50.0
                }

                textfield(model.angleProperty)
            }

            hbox(alignment = Pos.CENTER_LEFT) {
                spacing = 10.0

                label("Length") {
                    prefWidth = 50.0
                }

                textfield(model.lengthProperty)
            }

            checkbox("Tree", model.isTreeProperty)

            hbox {
                spacing = 10.0
                button("Add rule") {
                    action {
                        rulesList.add(addNewRule())
                    }
                }

                button("Next iteration") {
                    style {
                        backgroundColor += Color.GREEN
                        textFill = Color.WHITE
                    }

                    action {
                        model.nextIteration()

                        drawPane.children.clear()
                        turtle.setTree(model.isTree)
                        turtle.turnAngle = model.angle
                        turtle.drawRule(model.currentRule)
                        turtle.decreaseStep(model.length)
                    }
                }

                button("Reset") {
                    style {
                        backgroundColor += Color.DARKRED
                        textFill = Color.WHITE
                    }

                    action {
                        drawPane.children.clear()
                        turtle.position()
                        turtle.reset()
                        model.resetRule()
                    }
                }
            }

            add(rulesList)
            rulesList.add(addNewRule())
        }
    }
}