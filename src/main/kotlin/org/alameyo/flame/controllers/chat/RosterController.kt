package org.alameyo.flame.controllers.chat

import org.jivesoftware.smack.roster.Roster
import org.jivesoftware.smack.roster.RosterEntry
import org.jxmpp.jid.BareJid
import tornadofx.Controller

class RosterController(private val roster: Roster) : Controller() {

    fun entries(): MutableSet<RosterEntry> {
        roster.reloadAndWait()
        roster.subscriptionMode = Roster.SubscriptionMode.accept_all
        return roster.entries
    }

    fun addContactToRoster(bareJid: BareJid)  {
        roster.reloadAndWait()
        roster.createEntry(bareJid, "tester2", null )
        roster.reloadAndWait()
        roster.sendSubscriptionRequest(bareJid)
    }
}