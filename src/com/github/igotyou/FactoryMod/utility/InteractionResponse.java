package com.github.igotyou.FactoryMod.utility;


import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;


public class InteractionResponse 
{
	public static enum InteractionResult
	{
		SUCCESS,
		FAILURE,
		IGNORE,
		DEBUG,
		CHAT
	}
	
	private final InteractionResult interactionResult; //The result of this interaction attempt
	private final String interactionMessage; //The message to send to player(s) after interaction attempt
	private final Location location;
	
	/**
	 * Constructor
	 */
	public InteractionResponse(InteractionResult interactionResult,
		String interactionMessage) {
		this.interactionResult = interactionResult;
		this.interactionMessage = interactionMessage;
		this.location=null;
	}
	
	public InteractionResponse(InteractionResult interactionResult,
		String interactionMessage,
		Location location) {
		this.interactionResult = interactionResult;
		this.interactionMessage = interactionMessage;
		this.location=location;
	}
	
	/**
	 * Returns the interaction result message to be sent to player(s)
	 */
	public String getInteractionMessage()
	{
		return getMessageColor() + interactionMessage;
	}
	
	/**
	 * Messages the given Player this object's interaction message
	 */
	public static void messagePlayerResult(Player player, InteractionResponse interactionResponse)
	{
		player.sendMessage(interactionResponse.getInteractionMessage());
	}
	
	/**
	 * Messages the given Player all of this object's interaction message
	 */
	public static void messagePlayerResults(Player player, List<InteractionResponse> interactionResponses)
	{
		for(InteractionResponse interactionResponse:interactionResponses)
		{
			player.sendMessage(interactionResponse.getInteractionMessage());
		}
		InteractionResponse interactionResponse = interactionResponses.get(0);
		interactionResponse.getMessageSound().playSound(interactionResponse.location!=null ? interactionResponse.location : player.getLocation(), player);
		
	}
	/**
	 * Returns the appropriate sound for the interaction message based on its type
	 */
	public Sound getMessageSound() {
		switch (interactionResult)
		{
		case SUCCESS:
			List<String> successSoundNames = new ArrayList<String>();
			successSoundNames.add("liquid.swim");
			successSoundNames.add("mob.irongolem.hit");
			successSoundNames.add("mob.zombie.metal");
			successSoundNames.add("mob.blaze.hit");
			return new Sound(successSoundNames);
		case FAILURE:
			List<String> failuireSoundNames = new ArrayList<String>();
			failuireSoundNames.add("note.pling");
			return new Sound(failuireSoundNames);
		default:
			List<String> defaultSoundNames = new ArrayList<String>();
			defaultSoundNames.add("note.pling");
			return new Sound(defaultSoundNames);
		}
	}
	/**
	 * Returns the appropriate color for interaction messages based on type
	 */
	private ChatColor getMessageColor()
	{
		switch (interactionResult)
		{
		case SUCCESS:
			return ChatColor.GREEN;
		case FAILURE:
			return ChatColor.RED;
		case CHAT:
			return ChatColor.WHITE;
		default:
			return ChatColor.YELLOW;
		}
	}
	
	/**
	 * 'interactionResult' public accessor
	 */
	public InteractionResult getInteractionResult()
	{
		return interactionResult;
	}

}