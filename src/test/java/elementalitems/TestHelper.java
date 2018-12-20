package elementalitems;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.mockito.internal.matchers.VarargCapturingMatcher;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;

public class TestHelper {

	public static boolean doPotionsMatch(@Nonnull PotionEffect toTest, Potion effect, int duration, int power, boolean ambient, boolean showParticles) {
		return toTest.getPotion().equals(effect) && toTest.getDuration() == duration && toTest.getAmplifier() == power && toTest.getIsAmbient() == ambient && toTest.doesShowParticles() == showParticles;
	}

	public static void setField(Object toSet, String fieldName, Object fieldValue, Class<?> classWithField) {
		try {
			Field field = classWithField.getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(toSet, fieldValue);
		} catch(NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public static VarargCapturingMatcher<PotionEffect> matchPotionOfLevel(Potion potion, int level) {
		return new VarargCapturingMatcher<PotionEffect>() {
			@Override
			public boolean matches(Object argument) {
				return (argument instanceof PotionEffect) && (((PotionEffect) argument).getAmplifier() == level) && (((PotionEffect) argument).getPotion().equals(potion));
			}
		};
	}

	public static VarargCapturingMatcher<PotionEffect> matchPotionEffect(Potion potion) {
		return new VarargCapturingMatcher<PotionEffect>() {
			@Override
			public boolean matches(Object argument) {
				return argument instanceof PotionEffect && ((PotionEffect) argument).getPotion().equals(potion);
			}
		};
	}

	public static VarargCapturingMatcher<Potion> matchPotion(Potion potion) {
		return new VarargCapturingMatcher<Potion>() {
			@Override
			public boolean matches(Object argument) {
				return argument.equals(potion);
			}
		};
	}
}
