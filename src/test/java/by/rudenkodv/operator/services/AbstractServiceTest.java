package by.rudenkodv.operator.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.math3.random.RandomData;
import org.apache.commons.math3.random.RandomDataImpl;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
/*@ContextConfiguration(locations = { "classpath:spring-context.xml" })*/
public abstract class AbstractServiceTest {
	
	private final  Random random = new Random();
	protected static final RandomData RANDOM_DATA = new RandomDataImpl();
	private  final int RANDOM_STRING_SIZE = 8;
	final GregorianCalendar gc = new GregorianCalendar();
	
	public  String randomString() {
		return RandomStringUtils.randomAlphabetic(RANDOM_STRING_SIZE);
	}

	public  String randomString(final String prefix) {
		return String.format("%s-%s", new Object[] { prefix, randomString() });
	}

	public  int randomTestObjectsCount() {
		return RANDOM_DATA.nextInt(0, 5) + 1;
	}

	public  int randomInteger() {
		return randomInteger(0, 9999);
	}

	public  int randomInteger(final int lower, final int upper) {
		return RANDOM_DATA.nextInt(lower, upper);
	}

	public  boolean randomBoolean() {
		return Math.random() < 0.5;
	}

	public  long randomLong() {
		return RANDOM_DATA.nextLong(0, 9999999);
	}

	public  byte[] randomByte() {
		byte[] b = new byte[randomInteger()];
		random.nextBytes(b);
		return b;
	}

	public  BigDecimal randomBigDecimal() {
		return new BigDecimal(randomDouble()).setScale(2, RoundingMode.HALF_UP);
	}

	public  double randomDouble() {
		final double value = random.nextDouble() + randomInteger();
		return Math.round(value * 1e2) / 1e2;

	}

	public  <T> T randomFromCollection(final Collection<T> all) {
		final int size = all.size();
		final int item = new Random().nextInt(size); // In real life, the Random
														// object should be
														// rather more shared
														// than this
		int i = 0;
		for (final T obj : all) {
			if (i == item) {
				return obj;
			}
			i = i + 1;
		}
		return null;
	}

	public Date randomDate() {
		final int year = randBetween(1900, 2010);	
		gc.set(Calendar.YEAR, year);
		final int dayOfYear = randBetween(1,gc.getActualMaximum(Calendar.DAY_OF_YEAR));
		gc.set(Calendar.DAY_OF_YEAR, dayOfYear);
		return gc.getTime();
	}

	public static int randBetween(final int start, final int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}
	
	// generated random enum value
	public <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
	
	public Timestamp randomTimestamp(){
		long offset = Timestamp.valueOf("1980-01-01 00:00:00").getTime();
		long end = Timestamp.valueOf("2015-01-01 00:00:00").getTime();
		long diff = end - offset + 1;
		Timestamp randTimestamp = new Timestamp(offset + (long)(Math.random() * diff));
		randTimestamp.setNanos(00);
		return randTimestamp;
	}
	
	
	public int randInt(int min, int max) {
	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = random.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	public int randIntForLoop() {
	    int randomNum = random.nextInt(21);
	    return randomNum;
	}
	
	protected double randomDooble(){
		return 1000  * random.nextDouble();
	}
	

}
