package org.alameyo.flame.views.home.roster

import javafx.scene.control.Button
import javafx.scene.image.ImageView
import org.alameyo.flame.controllers.FlameController
import org.alameyo.flame.controllers.chat.ChatAreaController
import org.alameyo.flame.css.FlameStyle
import org.alameyo.flame.css.FlameStyle.Companion.rosterList
import org.alameyo.flame.models.FlameContactEntry
import org.alameyo.flame.views.home.contact.ContactView
import org.jxmpp.jid.impl.JidCreate
import tornadofx.*

class RosterView : View() {

    private val flameController: FlameController by inject()
    private val rosterController = flameController.rosterController
    private val chatAreaController: ChatAreaController by inject()
    private val contactView: ContactView by inject()

    override val root = scrollpane {
        vbox {
            hbox {
                addClass(rosterList)
                button {
                    var image = imageview("UI/Friend_Add.png")
                    fitImageToButton(image, this)
                    addClass(FlameStyle.roundButton)
                    action {
                        runAsync {
                            rosterController.addContactToRoster(JidCreate.bareFrom("tester2@alameyo.gq"))
                        } ui {
                            contactView.replaceWith<AddContactView>()
                            image = imageview("UI/Friends_Add_Actie.png")
                            fitImageToButton(image, this)
                        }
                    }
                }
            }
            rosterController.entries().forEach {
                val flameRosterEntry = FlameContactEntry(it)
                chatAreaController.addChat(flameRosterEntry)
                hbox {
                    addClass(rosterList)
                    button(it.name ?: it.jid.toString()) {
                        addClass(FlameStyle.roundButton)
                        action {
                            chatAreaController.openChatWithRosterEntry(flameRosterEntry)
                        }
                    }
                }
            }
        }
    }

    private fun fitImageToButton(imageView: ImageView, button: Button) {
        imageView.fitHeightProperty().bind(button.heightProperty())
        imageView.fitWidthProperty().bind(button.widthProperty())
    }
}
