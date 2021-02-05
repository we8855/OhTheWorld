package cn.shadow.OhTheWorld.command.impl;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cn.shadow.OhTheWorld.gui.MainMenu;
import cn.shadow.OhTheWorld.permission.PermissionManager;
import cn.shadow.OhTheWorld.utils.I18n;
import net.md_5.bungee.api.ChatColor;

public class MainMenuCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
        	sender.sendMessage(ChatColor.YELLOW + I18n.getInstance().PlayerOnlyCommand);
        	return true;
        }
    	Player p = (Player) sender;
    	if(!PermissionManager.hasManagePermission(p)) {
    		p.sendMessage(ChatColor.RED + I18n.getInstance().NoPermission);
    		return true;
    	}
        MainMenu m = new MainMenu();
        m.openInv(p);
        return true;
    }
}
