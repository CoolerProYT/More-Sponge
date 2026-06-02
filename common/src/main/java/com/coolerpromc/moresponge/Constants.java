package com.coolerpromc.moresponge;

import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {
	public static final String MODID = "moresponge";
	public static final String MOD_NAME = "More Sponge";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	public static Identifier id(String name){
		return Identifier.fromNamespaceAndPath(MODID, name);
	}
}