package org.alameyo.flame.views.home.roster

import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import org.alameyo.flame.controllers.FlameController
import org.alameyo.flame.controllers.chat.ChatAreaController
import org.alameyo.flame.css.FlameStyle.Companion.rosterList
import org.alameyo.flame.css.FlameStyle.Companion.roundButton
import org.alameyo.flame.models.FlameContactEntry
import org.alameyo.flame.views.fitSize
import org.alameyo.flame.views.home.contact.ContactView
import org.jivesoftware.smack.roster.RosterEntry
import org.jxmpp.jid.impl.JidCreate
import tornadofx.*

class RosterView : View() {

    private val flameController: FlameController by inject()
    private val rosterController = flameController.rosterController
    private val chatAreaController: ChatAreaController by inject()
    private val contactView: ContactView by inject()
    lateinit var holdBox: VBox

    override val root = scrollpane {
        holdBox = vbox {
            add(addUserBox())
            populateRoster(this)
        }
    }

    fun populateRoster(box: VBox) {
        rosterController.entries().forEach {
            box.add(rosterBox(it))
        }
    }

    private fun addUserBox(): HBox {
        return hbox {
            addClass(rosterList)
            button {
                var image = imageview("UI/Friends.png")
                image.fitSize(this)
                addClass(roundButton)
                action {
                    contactView.replaceWith<AddContactView>()
                    image = imageview("UI/Friends_Active.png")
                    image.fitSize(this)
                }
            }
        }
    }

    private fun rosterBox(rosterEntry: RosterEntry) = rosterBoxFromEntry(FlameContactEntry(rosterEntry))

    fun rosterBox( name: String, jid: String) = rosterBoxFromEntry(FlameContactEntry(name, jid, JidCreate.bareFrom(jid)))

    private fun rosterBoxFromEntry(flameRosterEntry: FlameContactEntry): HBox {
        chatAreaController.addChat(flameRosterEntry)
        return hbox {
            addClass(rosterList)
            button(flameRosterEntry.name ?: flameRosterEntry.jid) {
                addClass(roundButton)
                action {
                    chatAreaController.openChatWithRosterEntry(flameRosterEntry)
                }
            }
        }
    }
}
