package cn.shadow.OhTheWorld.gui.impl;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import cn.shadow.OhTheWorld.config.WorldConfigs;
import cn.shadow.OhTheWorld.config.sub.SingleWorldConfig;
import cn.shadow.OhTheWorld.config.sub.WorldCreatureConfig;
import cn.shadow.OhTheWorld.config.sub.WorldGeneratorConfig;
import cn.shadow.OhTheWorld.config.sub.WorldWeatherConfig;
import cn.shadow.OhTheWorld.gui.GuiInventoryHolder;
import cn.shadow.OhTheWorld.gui.GuiMacro;
import cn.shadow.OhTheWorld.gui.GuiProvider;
import cn.shadow.OhTheWorld.gui.SelectWorldIcon;
import cn.shadow.OhTheWorld.gui.WorldCreature;
import cn.shadow.OhTheWorld.gui.WorldGeneratorList;
import cn.shadow.OhTheWorld.gui.WorldWeather;
import cn.shadow.OhTheWorld.utils.I18n;
import cn.shadow.OhTheWorld.utils.ItemStackUtil;
import cn.shadow.OhTheWorld.utils.MultiVersion;
import cn.shadow.OhTheWorld.utils.SeedUtil;
import cn.shadow.OhTheWorld.world.WorldManager;
import de.themoep.inventorygui.DynamicGuiElement;
import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import net.md_5.bungee.api.ChatColor;


// Code Generated by InventoryGUIMaker


public abstract class BasicWorldGui implements GuiProvider {
	
	protected GuiInventoryHolder gh;
	protected InventoryGui gui;
	
	private String[] worldName = { "New_World" };
	private String[] worldAlias = { "世界别名" };
	private boolean[] bedRespawn = { true };
	private GameMode gamemode = GameMode.SURVIVAL;
	private boolean[] keepSpawnInMemory = { true };
	private boolean[] allowNetherPortal = { true };
	private boolean[] allowEndPortal = { true };
	private boolean[] hunger = { true };
	private boolean[] autoHeal = { true };
	private boolean[] lockDimId = { false };
	private String[] respawnWorldName = { "" };
	private String[] seed = { SeedUtil.generateSeed(10) };
	private String[] regenOnEveryXHours = { "0" };
	private String[] enterFee = { "0" };
	private Environment env = Environment.NORMAL;
	private ItemStack envItem = MultiVersion.getInstance().getGrassBlock();
	private String[] worldIcon = { 
			ItemStackUtil.itemStackToString(
					new ItemStack(MultiVersion.getInstance().getDirt())
					) 
			};
	private Difficulty difficulty = Difficulty.EASY;
	private boolean[] generateStructures = { true };
	private boolean[] normalAnimals = { true };
	private boolean[] waterAnimals = { true };
	private boolean[] monsters = { true };
	private boolean[] allowRain = { true };
	private boolean[] allowThunder = { true };
	private boolean[] blueSky = { false };

	
	private WorldGeneratorConfig[] generator = { new WorldGeneratorConfig() };	
	
	protected GuiProvider parent;

	private SingleWorldConfig upgrade(SingleWorldConfig config) {
		long regen;
		try {
			regen = Long.parseLong(regenOnEveryXHours[0]);
		} catch (NumberFormatException ex) {
			regen = 0;
		}
		double fee;
		try {
			fee = Double.parseDouble(enterFee[0]);
		} catch (NumberFormatException ex) {
			fee = 0;
		}
		
		WorldCreatureConfig creatures = new WorldCreatureConfig();
		creatures.monsters = monsters[0];
		creatures.normalAnimals = normalAnimals[0];
		creatures.waterAnimals = waterAnimals[0];
		WorldWeatherConfig weathers = new WorldWeatherConfig();
		weathers.allowRain = allowRain[0];
		weathers.allowThunder = allowThunder[0];
		
		config
		.setBlueSky(blueSky[0])
		.setAlias(worldAlias[0])
		.setBedRespawn(bedRespawn[0])
		.setGameMode(gamemode)
		.setKeepSpawnInMemory(keepSpawnInMemory[0])
		.setAllowEndPortal(allowEndPortal[0])
		.setAllowNetherPortal(allowNetherPortal[0])
		.setHunger(hunger[0])
		.setAutoHeal(autoHeal[0])
		.setLockDimIdStatus(lockDimId[0])
		.setRespawnWorldName(respawnWorldName[0])
		.setSeed(seed[0])
		.setRegenOnEveryXHours(regen)
		.setEnterFee(fee)
		.setEnvironment(env)
		.setWorldIcon(worldIcon[0])
		.setDifficulty(difficulty)
		.setgetGenerateStructures(generateStructures[0])
		.setCreatures(creatures)
		.setWeather(weathers)
		.setGenerator(generator[0]);
		return config;
	}
	
	private SingleWorldConfig buildConfig() {
		SingleWorldConfig config = upgrade(new SingleWorldConfig());
		return config;
	}
	
	public BasicWorldGui(GuiProvider parent) {
		this.parent = parent;
	}
	
	public BasicWorldGui(GuiProvider parent, String worldName) {
		SingleWorldConfig swc = WorldConfigs.getInstance().worlds.get(worldName);
		this.parent = parent;
				
		this.worldName = new String[] { worldName };
		this.worldAlias = new String[] { swc.getAlias() };
		this.bedRespawn = new boolean[] { swc.getBedRespawn() };
		this.gamemode = swc.getGameMode();
		this.keepSpawnInMemory = new boolean[] { swc.getKeepSpawnInMemory() };
		this.allowNetherPortal = new boolean[] { swc.getAllowNetherPortal() };
		this.allowEndPortal = new boolean[] { swc.getAllowEndPortal() };
		this.hunger = new boolean[] { swc.getHunger() };
		this.autoHeal = new boolean[] { swc.getAutoHeal() };
		this.lockDimId = new boolean[] { swc.getLockDimIdStatus() };
		this.respawnWorldName = new String[] { swc.getRespawnWorldName() };
		this.seed = new String[] { swc.getSeed() };
		this.regenOnEveryXHours = new String[] { Long.toString(swc.getRegenOnEveryXHours()) };
		this.enterFee = new String[] { Double.toString(swc.getEnterFee()) };
		this.env = swc.getEnvironment();
		this.worldIcon = new String[] { swc.getWorldIconStr() };
		this.difficulty = swc.getDifficulty();
		this.generateStructures = new boolean[] { swc.getGenerateStructures() };
		this.normalAnimals = new boolean[] { swc.getCreatures().normalAnimals };
		this.waterAnimals = new boolean[] { swc.getCreatures().waterAnimals };
		this.monsters = new boolean[] { swc.getCreatures().monsters };
		this.allowRain = new boolean[] { swc.getWeather().allowRain };
		this.allowThunder = new boolean[] { swc.getWeather().allowThunder };
		this.generator = new WorldGeneratorConfig[] { swc.getGenerator() };
		this.blueSky = new boolean[] { swc.getBlueSky() };
	}
	
	@Override
	public void openInv(Player p) {
		gui.setCloseAction(close -> {
		    return false;
		});
		//世界名称
		GuiMacro.InputBox('a', I18n.getInstance().WorldName, worldName, this, gui);
		//世界别名
		GuiMacro.InputBox('b', I18n.getInstance().WorldAlias, worldAlias, this, gui);
		//在床上重生
		GuiMacro.checkBox('c', I18n.getInstance().BedRespawn, bedRespawn, this, gui);
		//游戏模式
		gui.addElement(new DynamicGuiElement('d', (viewer) -> {
		    return new StaticGuiElement('d', new ItemStack (MultiVersion.getInstance().getCompass()), 
		        click -> {
		        	switch(gamemode) {
			        	case SURVIVAL: {
			        		gamemode = GameMode.CREATIVE;
			        		break;
			        	}
			        	case CREATIVE: {
			        		gamemode = GameMode.SPECTATOR;
			        		break;
			        	}
			        	case SPECTATOR: {
			        		gamemode = GameMode.ADVENTURE;
			        		break;
			        	}
			        	case ADVENTURE: {
			        		gamemode = GameMode.SURVIVAL;
			        		break;
			        	}
		        	}
		            click.getGui().draw();
		            return true;
		        },
		        I18n.getInstance().GameMode[0],
		        I18n.getInstance().Status + gamemode.toString(),
		        I18n.getInstance().GameMode[1]
		        );
		}));
		
		//出生点加载
		GuiMacro.checkBox('e', I18n.getInstance().KeepSpawnInMemory, keepSpawnInMemory, this, gui);
		//允许生成下界传送门
		GuiMacro.checkBox('f', I18n.getInstance().AllowNetherPortal, allowNetherPortal, this, gui);
		//允许激活末地传送门
		GuiMacro.checkBox('g', I18n.getInstance().AllowEndPortal, allowEndPortal, this, gui);
		//饥饿
		GuiMacro.checkBox('h', I18n.getInstance().Hunger, hunger, this, gui);
		//自然回复
		GuiMacro.checkBox('i', I18n.getInstance().AutoHeal, autoHeal, this, gui);
		//维度id锁定
		GuiMacro.checkBox('j', I18n.getInstance().LockDimId, lockDimId, this, gui);
		//重生世界名称
		GuiMacro.InputBox('k', I18n.getInstance().RespawnWorldName, respawnWorldName, this, gui);
		//世界种子
		GuiMacro.InputBox('l', I18n.getInstance().Seed, seed, this, gui);
		//定期重生
		try {
			long l = Long.parseLong(regenOnEveryXHours[0]);
			regenOnEveryXHours[0] = Long.toString(l);
		} catch(NumberFormatException ex) {
			regenOnEveryXHours[0] = "0";
		}
		GuiMacro.InputBox('m', I18n.getInstance().Regen, regenOnEveryXHours, this, gui);
		//入场费用
		try {
			double d = Double.parseDouble(enterFee[0]);
			enterFee[0] = Double.toString(d);
		} catch(NumberFormatException ex) {
			enterFee[0] = "0";
		}
		GuiMacro.InputBox('n', I18n.getInstance().EnterFee, enterFee, this, gui);
		
		//世界图标
		gui.addElement(new StaticGuiElement('o',
		        new ItemStack(ItemStackUtil.stringToItemStack(worldIcon[0])),
		        1, // Display a number as the item count
		        click -> {
		        	gui.close();
		        	SelectWorldIcon swi = new SelectWorldIcon(this, worldIcon);
		        	swi.openInv(p);
		        	return true;
		        },
		        I18n.getInstance().WorldIcon
		)); 

		//环境
		gui.addElement(new DynamicGuiElement('p', (viewer) -> {
		    return new StaticGuiElement('p', envItem, 
		        click -> {
		        	switch(env) {
			        	case NORMAL: {
			        		env = Environment.NETHER;
			        		envItem = MultiVersion.getInstance().getNetherRack();
			        		break;
			        	}
			        	case NETHER: {
			        		env = Environment.THE_END;
			        		envItem = MultiVersion.getInstance().getEndStone();
			        		break;
			        	}
			        	case THE_END: {
			        		env = Environment.NORMAL;
			        		envItem = MultiVersion.getInstance().getGrassBlock();
			        		break;
			        	}
		        	}
		            click.getGui().draw();
		            return true;
		        },
		        I18n.getInstance().Environment[0],
		        I18n.getInstance().Status + env.toString(),
		        I18n.getInstance().Environment[1]
		        );
		}));
		
		//难度
		gui.addElement(new DynamicGuiElement('q', (viewer) -> {
		    return new StaticGuiElement('q',
		    	new ItemStack(MultiVersion.getInstance().getDiamondSword()),
		        click -> {
		        	switch(difficulty) {
		        	case PEACEFUL:
		        		difficulty = Difficulty.EASY;
		        		break;
		        	case EASY:
		        		difficulty = Difficulty.NORMAL;
		        		break;
		        	case NORMAL:
		        		difficulty = Difficulty.HARD;
		        		break;
		        	case HARD:
		        		difficulty = Difficulty.PEACEFUL;
		        		break;
		        	}
		            click.getGui().draw();
		            return true;
		        },
		        I18n.getInstance().Difficulty[0],
		        I18n.getInstance().Status + difficulty.toString(),
		        I18n.getInstance().Difficulty[1]
		        );
		}));
		
		//生成结构
		GuiMacro.checkBox('r', I18n.getInstance().GenerateStructures, generateStructures, this, gui);
		
		WorldCreatureConfig creatures = new WorldCreatureConfig();
		creatures.monsters = monsters[0];
		creatures.normalAnimals = normalAnimals[0];
		creatures.waterAnimals = waterAnimals[0];

		//生物生成
		gui.addElement(new StaticGuiElement('s',
		        new ItemStack(MultiVersion.getInstance().getDiamondSword()),
		        1, // Display a number as the item count
		        click -> {
		        	gui.close();
		        	WorldCreature wc = new WorldCreature(this, this.normalAnimals, this.waterAnimals,
		        			this.monsters);
		        	wc.openInv(p);
		        	return true;
		        },
		        creatures.lores()
		));
		
		//生成器
		gui.addElement(new StaticGuiElement('t',
		        new ItemStack(MultiVersion.getInstance().getEndCrystal()),
		        1, // Display a number as the item count
		        click -> {
		        	gui.close();
		        	WorldGeneratorList w = new WorldGeneratorList(this, generator);
		        	w.openInv(p);
		        	return true;
		        },
		        I18n.getInstance().WorldGenerator
		));
		
		//天气控制
		gui.addElement(new StaticGuiElement('u',
		        new ItemStack(MultiVersion.getInstance().getSunflower()),
		        1, // Display a number as the item count
		        click -> {
		        	gui.close();
		        	WorldWeather w = new WorldWeather(this, allowRain, allowThunder);
		        	w.openInv(p);
		        	return true;
		        },
		        I18n.getInstance().Weather
		));
		
		//蓝色天空
		GuiMacro.checkBox('v', I18n.getInstance().BlueSky, blueSky, this, gui);
		
		
		//创建世界
		gui.addElement(new StaticGuiElement('3',
		        new ItemStack(MultiVersion.getInstance().getLever()),
		        1, // Display a number as the item count
		        click -> {
		        	if(Bukkit.getWorld(worldName[0]) != null) {
		        		p.sendMessage(ChatColor.YELLOW + I18n.getInstance().WorldAlreadyExist);
		        		return true;
		        	}
		        	
		        	File f = new File(Bukkit.getWorldContainer(), worldName[0]);
		        	if(!f.exists()) {
		        		String msg = String.format(I18n.getInstance().WorldNotFound, f.getAbsolutePath());
		        		p.sendMessage(ChatColor.RED + msg);
		        		return true;
		        	}
		        	
		        	SingleWorldConfig config = buildConfig();
		        	
		        	gui.close();
		        	parent.openInv(p);
		        	if(WorldManager.importWorld(p, worldName[0], config)) {
		        		WorldConfigs.getInstance().worlds.put(worldName[0], config);
		        		WorldConfigs.safeSave();
		        	}
		        	return true;
		        },
		        I18n.getInstance().BuildWorld
		));
				
		//创建世界
		gui.addElement(new StaticGuiElement('0',
		        new ItemStack(MultiVersion.getInstance().getLever()),
		        1, // Display a number as the item count
		        click -> {
		        	if(Bukkit.getWorld(worldName[0]) != null) {
		        		p.sendMessage(ChatColor.YELLOW + I18n.getInstance().WorldAlreadyExist);
		        		return true;
		        	}
		        	
		        	SingleWorldConfig config = buildConfig();
		        	
		        	gui.close();
		        	parent.openInv(p);
		        	if(WorldManager.createWorld(p, worldName[0], config)) {
		        		WorldConfigs.getInstance().worlds.put(worldName[0], config);
		        		WorldConfigs.safeSave();
		        	}
		        	return true;
		        },
		        I18n.getInstance().BuildWorld
		));
		
		//更新世界
		gui.addElement(new StaticGuiElement('1',
		        new ItemStack(MultiVersion.getInstance().getLever()),
		        1, // Display a number as the item count
		        click -> {
		        	SingleWorldConfig swc = WorldConfigs.getInstance().worlds.get(worldName[0]);
		        	if(swc == null) return true;
		        	upgrade(swc);
		        	WorldManager.updateWorld(p, worldName[0], swc);
		        	WorldConfigs.safeSave();
		        	gui.close();
		        	parent.openInv(p);
		        	return true;
		        },
		        I18n.getInstance().UpdateWorld
		));
		
		gui.addElement(new StaticGuiElement('2',
		        new ItemStack(MultiVersion.getInstance().getBarrier()),
		        1, // Display a number as the item count
		        click -> {
		        	gui.close();
		        	parent.openInv(p);
		        	return true;
		        },
		        I18n.getInstance().Back
		));
	
		gui.show(p);
	}
}
