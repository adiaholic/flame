package org.alameyo.flame.models

import org.alameyo.flame.views.home.chat.DirectChatTab
import org.jivesoftware.smack.roster.RosterEntry

class FlameRosterEntry(val rosterEntry: RosterEntry) {
    val name: String? = rosterEntry.name
    val jid = rosterEntry.jid.toString()
    val bareJid = rosterEntry.jid

    var directChatTab: DirectChatTab? = null
}
