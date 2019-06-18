package com.shadowolfyt.zander.discord.moderation;

import com.shadowolfyt.zander.ZanderMain;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.util.List;
import static org.apache.commons.lang.StringUtils.containsIgnoreCase;

public class SwearFilter extends ListenerAdapter {
    private ZanderMain plugin;
    public SwearFilter(ZanderMain plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        List<String> FilteredWords = plugin.configurationManager.getfilter().getStringList("filteredwords");
        String message = event.getMessage().getContentRaw();

        for (String word: FilteredWords){
            if (containsIgnoreCase(message, word)){
                event.getMessage().delete().queue();

                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle("Foul Language");
                embed.setColor(Color.red);
                embed.setDescription(event.getMember().getUser().getName() + " you cannot use that word!");
                event.getChannel().sendMessage(embed.build()).queue();
                break;
            }
        }
    }
}