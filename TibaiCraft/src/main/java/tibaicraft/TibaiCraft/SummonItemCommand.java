package tibaicraft.TibaiCraft;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SummonItemCommand implements CommandExecutor {

	private main plugin;
	public SummonItemCommand(main main) {
		plugin = main;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] arg3) {
		if(cmd.getName().equalsIgnoreCase("lighting_pulse"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player) sender;
				player.getInventory().addItem(plugin.lighting_pulse);
				player.getInventory().addItem(plugin.tempest_blessing);
				player.getInventory().addItem(plugin.endless_wrath);
				return true;
			}
		}
		return false;
	}
}
