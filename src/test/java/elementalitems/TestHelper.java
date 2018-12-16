package elementalitems;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;

public class TestHelper {

	public static boolean doPotionsMatch(@Nonnull PotionEffect toTest, Potion effect, int duration, int power, boolean ambient, boolean showParticles) {
		return toTest.getPotion().equals(effect) && toTest.getDuration() == duration && toTest.getAmplifier() == power && toTest.getIsAmbient() == ambient && toTest.doesShowParticles() == showParticles;
	}

	public static void setField(Object toSet, String fieldName, Object fieldValue, Class<?> toSetClass) {
		try {
			Field field = toSetClass.getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(toSet, fieldValue);
		} catch(NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
