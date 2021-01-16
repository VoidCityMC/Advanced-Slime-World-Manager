package com.grinderwolf.swm.api.world.properties.type;

import com.flowpowered.nbt.ByteTag;
import com.flowpowered.nbt.CompoundMap;
import com.flowpowered.nbt.CompoundTag;
import com.flowpowered.nbt.IntTag;
import com.grinderwolf.swm.api.world.properties.SlimeProperty;

import java.util.function.Function;

/**
 * A slime property of type integer
 */
public class SlimePropertyInt extends SlimeProperty<Integer> {

	public SlimePropertyInt(String nbtName, Integer defaultValue) {
		super(nbtName, defaultValue);
	}

	public SlimePropertyInt(String nbtName, Integer defaultValue, Function<Integer, Boolean> validator) {
		super(nbtName, defaultValue, validator);
	}

	@Override
	protected void writeValue(CompoundMap compound, Integer value) {
		compound.put(getNbtName(), new IntTag(getNbtName(), value));
	}

	@Override
	protected Integer readValue(CompoundTag compound) {
		return compound.getIntValue(getNbtName())
			.orElse(getDefaultValue());
	}
}
