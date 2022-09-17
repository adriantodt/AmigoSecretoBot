package net.adriantodt.amigosecretobot

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity

fun main(args: Array<String>) {
    JDABuilder.createDefault(args[0])
        .addEventListeners(Bot())
        .setActivity(Activity.playing("Noite do Salgado!"))
        .build()
}
