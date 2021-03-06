package au.com.mineauz.minigamesregions.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import au.com.mineauz.minigames.MinigamePlayer;
import au.com.mineauz.minigames.Minigames;
import au.com.mineauz.minigames.commands.ICommand;
import au.com.mineauz.minigames.minigame.Minigame;
import au.com.mineauz.minigamesregions.Node;
import au.com.mineauz.minigamesregions.RegionModule;

public class SetNodeCommand implements ICommand {

	@Override
	public String getName() {
		return "node";
	}

	@Override
	public String[] getAliases() {
		return null;
	}

	@Override
	public boolean canBeConsole() {
		return false;
	}

	@Override
	public String getDescription() {
		return "Creates and modifies customizable nodes";
	}

	@Override
	public String[] getParameters() {
		return null;
	}

	@Override
	public String[] getUsage() {
		return new String[] {"/minigame set <Minigame> node create <name>",
				"/minigame set <Minigame> node modify",
				"/minigame set <minigame> node delete <name>"
		};
	}

	@Override
	public String getPermissionMessage() {
		return "You do not have permission to modify Minigame nodes!";
	}

	@Override
	public String getPermission() {
		return "minigame.set.node";
	}

	@Override
	public boolean onCommand(CommandSender sender, Minigame minigame,
			String label, String[] args) {
		if(args != null){
			MinigamePlayer ply = Minigames.plugin.pdata.getMinigamePlayer((Player)sender);
			RegionModule rmod = RegionModule.getMinigameModule(minigame);
			if(args[0].equalsIgnoreCase("create") && args.length >= 2){
				if(!rmod.hasNode(args[1])){
					rmod.addNode(args[1], new Node(args[1], ply.getLocation()));
					sender.sendMessage(ChatColor.GRAY + "Added new node called " + args[1] + " to " + minigame);
				}
				else
					sender.sendMessage(ChatColor.RED + "A node by the name " + args[1] + " already exists in " + minigame);
				return true;
			}
			else if(args[0].equalsIgnoreCase("modify")){
				rmod.displayMenu(ply, null);
				return true;
			}
			else if(args[0].equalsIgnoreCase("delete") && args.length >= 2){
				if(rmod.hasNode(args[1])){
					rmod.removeNode(args[1]);
					sender.sendMessage(ChatColor.GRAY + "Removed a node called " + args[1] + " from " + minigame);
				}
				else
					sender.sendMessage(ChatColor.RED + "A node by the name " + args[1] + " doesn't exists in " + minigame);
				return true;
			}
		}
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Minigame minigame,
			String alias, String[] args) {
		// TODO Auto-generated method stub
		return null;
	}

}
