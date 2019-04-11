package org.alameyo.flame.views.home.roster

import javafx.scene.control.TabPane
import org.alameyo.flame.controllers.chat.ChatAreaController
import org.alameyo.flame.controllers.FlameController
import org.alameyo.flame.css.FlameStyle
import org.alameyo.flame.models.FlameRosterEntry
import tornadofx.*

class LeftSideView : View() {

    private val rosterView: RosterView by inject()
    private val multiUserChatView: MultiUserChatView by inject()
    override val root = tabpane {
        tab("Friends") {
            content = rosterView.root
        }

        tab("Rooms") {
            content = multiUserChatView.root
        }

        tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
    }
}
