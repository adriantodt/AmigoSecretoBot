package net.adriantodt.amigosecretobot

import com.jagrosh.jdautilities.commons.utils.FinderUtil
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.util.*

class Bot : ListenerAdapter() {
    // Add the names here!
    private val names = LinkedList(listOf("TODO").shuffled())

    override fun onGuildMessageReceived(event: GuildMessageReceivedEvent) {
        if (event.message.contentRaw == "!amigosecreto") {
            val channel = event.author.openPrivateChannel().complete()

            val name = names.firstOrNull { it != event.author.name }
            if (name != null) {
                names.remove(name)
                channel.sendMessage("Seu amigo secreto é: **$name**.").queue()
                event.channel.sendMessage("Seu amigo secreto foi enviado no seu privado!").queue()
            } else {
                event.channel.sendMessage("Não tem mais amigos secretos!").queue()
            }
        }
    }

    override fun onPrivateMessageReceived(event: PrivateMessageReceivedEvent) {
        val parts = event.message.contentRaw.split(' ', limit = 3)

        if (parts.size != 3) return
        val (name, who, content) = parts
        if (name != "!msg") return

        val users = FinderUtil.findUsers(who, event.jda)

        if (users.size != 1) {
            event.channel.sendMessage("\"$who\" dá match com mais de um usuário, por favor especifique melhor.").queue()
            return
        }

        users.single()
            .openPrivateChannel()
            .complete()
            .sendMessage("**Seu amigo secreto te enviou uma mensagem**:\n$content")
            .queue()
    }
}
