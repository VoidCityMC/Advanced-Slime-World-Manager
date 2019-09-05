package com.grinderwolf.swm.api.world;

import com.flowpowered.nbt.CompoundTag;
import com.grinderwolf.swm.api.exceptions.WorldAlreadyExistsException;
import com.grinderwolf.swm.api.loaders.SlimeLoader;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.Wither;

import java.io.IOException;

/**
 * In-memory representation of a SRF world.
 */
public interface SlimeWorld {

    /**
     * Returns the name of the world.
     *
     * @return The name of the world.
     */
    String getName();

    /**
     * Returns the {@link SlimeLoader} used
     * to load and store the world.
     *
     * @return The {@link SlimeLoader} used to load and store the world.
     */
    SlimeLoader getLoader();

    /**
     * Returns the chunk that belongs to the coordinates specified.
     *
     * @param x X coordinate.
     * @param z Z coordinate.
     *
     * @return The {@link SlimeChunk} that belongs to those coordinates.
     */
     SlimeChunk getChunk(int x, int z);

    /**
     * Returns the extra data of the world. Inside this {@link CompoundTag}
     * can be stored any information to then be retrieved later, as it's
     * saved alongside the world data.
     *
     * @return A {@link CompoundTag} containing the extra data of the world.
     */
    CompoundTag getExtraData();

    /**
     * Returns the properties of the world. These properties are automatically
     * kept up-to-date when the world is loaded and its properties are updated.
     *
     * @return A {@link SlimeProperties} object with all the current properties of the world.
     */
    SlimeProperties getProperties();

    /**
     * Returns a clone of the world with the given name. This world will never be
     * stored, as the <code>readOnly</code> property will be set to true.
     *
     * @param worldName The name of the cloned world.
     *
     * @return The clone of the world.
     *
     * @throws IllegalArgumentException if the name of the world is the same as the current one or is <code>null</code>.
     */
    SlimeWorld clone(String worldName);

    /**
     * Returns a clone of the world with the given name. The world will be
     * automatically stored inside the provided data source.
     *
     * @param worldName The name of the cloned world.
     * @param loader The {@link SlimeLoader} used to store the world or <code>null</code> if the world is temporary.
     *
     * @return The clone of the world.
     *
     * @throws IllegalArgumentException if the name of the world is the same as the current one or is <code>null</code>.
     * @throws WorldAlreadyExistsException if there's already a world with the same name inside the provided data source.
     * @throws IOException if the world could not be stored.
     */
    SlimeWorld clone(String worldName, SlimeLoader loader) throws WorldAlreadyExistsException, IOException;

    /**
     * All the currently-available properties of the world.
     */
    @Getter
    @Builder(toBuilder = true)
    class SlimeProperties {

        private double spawnX;
        @Builder.Default
        private double spawnY = 255;
        private double spawnZ;

        private int difficulty;

        @Accessors(fluent = true)
        @Builder.Default
        private boolean allowMonsters = true;
        @Accessors(fluent = true)
        @Builder.Default
        private boolean allowAnimals = true;

        @Wither
        private boolean readOnly;

        @Builder.Default
        private boolean pvp = true;

        @Builder.Default
        private String environment = "NORMAL";
    }
}
