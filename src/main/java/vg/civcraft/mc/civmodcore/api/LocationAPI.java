package vg.civcraft.mc.civmodcore.api;

import org.bukkit.Location;
import org.bukkit.World;
import vg.civcraft.mc.civmodcore.util.NullCoalescing;

/**
 * Class of utility functions for Locations.
 */
public final class LocationAPI {

	/**
	 * Retrieves the world from a location.
	 *
	 * @param location The location to retrieve the world from.
	 * @return Returns the world if loaded, or null.
	 */
	public static World getLocationWorld(Location location) {
		if (location == null) {
			return null;
		}
		try {
			return location.getWorld();
		}
		catch (IllegalArgumentException ignored) { // Will be thrown if the world is not loaded
			return null;
		}
	}

	/**
	 * Determines whether a location is valid and safe to use.
	 *
	 * @param location The location to check.
	 * @return Returns true if the location exists, is valid, and safe to use.
	 */
	public static boolean isValidLocation(Location location) {
		if (location == null) {
			return false;
		}
		if (!location.isWorldLoaded()) {
			return false;
		}
		return true;
	}

	/**
	 * Converts a location into a block location. (Yaw and Pitch values are lost)
	 *
	 * @param location The location to convert.
	 * @return Returns a block location, or null if the given location was null.
	 */
	public static Location getBlockLocation(Location location) {
		if (location == null) {
			return null;
		}
		return new Location(getLocationWorld(location),
				location.getBlockX(),
				location.getBlockY(),
				location.getBlockZ());
	}

	/**
	 * Converts a location into a block's mid point. (Yaw and Pitch values are lost)
	 *
	 * @param location The location to convert.
	 * @return Returns a block's mid point, or null if the given location was null.
	 */
	public static Location getMidBlockLocation(Location location) {
		if (location == null) {
			return null;
		}
		return getBlockLocation(location).add(0.5d, 0.5d, 0.5d);
	}

	/**
	 * Determines whether two locations share the same world.
	 *
	 * @param former The first location.
	 * @param latter The second location.
	 * @return Returns true if the two locations are not null and share the same world.
	 */
	public static boolean areLocationsSameWorld(Location former, Location latter) {
		if (former == null || latter == null) {
			return false;
		}
		return NullCoalescing.equalsNotNull(getLocationWorld(former), getLocationWorld(latter));
	}

}
