package org.fuzzydb.samples;

import java.awt.Color;
import java.util.Formatter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.fuzzydb.core.query.Result;
import org.fuzzydb.dto.attributes.Score;
import org.springframework.util.Assert;


public abstract class Utils {

	/**
	 * Returns a string of the form rgb(100,200,0) to be used
	 * to indicate the percentage.  100% is green, 0% is red.
	 * HSV model:
	 *   Hue 0 -> 120 (red -> green)
	 *   Sat -> fixed @ 85
	 *   Brightness -> fixed @ 90
	 *   
	 *   @param value 0 - 1.0f
	 */
	public static String toCssRGBColor(float value) {
	    return toCssRGBColor(value, 0.85f);
	}

	/**
	 * Returns a string of the form rgb(100,200,0) to be used
	 * to indicate the percentage.  100% is green, 0% is red.
	 * HSV model:
	 *   Hue 0 -> 120 (red -> green)
	 *   Sat -> fixed @ 85
	 *   Brightness -> fixed @ 90
	 *   
	 *   @param value 0 - 1.0f
	 *   @param saturation 0 - 1.0f - lower for paler
	 */
	public static String toCssRGBColor(float value, float saturation) {
	    int intVal = Math.round(value * 100);
	    Color color = new Color(Color.HSBtoRGB(intVal * 1.2f / 360f, saturation, 0.90f));
	    return "rgb(" + color.getRed() + "," + color.getGreen() + ","+ color.getBlue() +")";
	}

	/**
	 * Returns a string of the form rgb(100,200,0) to be used
	 * to indicate the percentage.  100% is green, 0% is red.
	 * HSV model:
	 *   Hue 0 -> 120 (red -> green)
	 *   Sat -> fixed @ 85
	 *   Brightness -> fixed @ 90
	 *   
	 *   @param value 0 - 1.0f
	 */
	public static String toCssRGBColor(Score score) {
		return toCssRGBColor(score.total());
	}

	public static String toCssRGBColor(Score score, float sat) {
		return toCssRGBColor(score.total(), sat);
	}

	public static int toPercent(Score score) {
		return Math.round(score.total() * 100f);
	}
		
	public static <T> List<T> toList(Iterator<T> items) {
		Assert.notNull(items);
		
		List<T> list = new LinkedList<T>();
		for (Iterator<T> iterator = items; iterator.hasNext();) {
			T item = iterator.next();
			list.add(item);
		}
		return list;
	}

	
	public static <T> Float forwardsScore(Result<T> result, String matcher) {
		return result.getScore().getForwardsScore(matcher);
	}

	public static <T> Float reverseScore(Result<T> result, String matcher) {
		return result.getScore().getReverseScore(matcher);
	}

	public static <T> Float forwardsTotal(Result<T> result) {
		return result.getScore().forwardsTotal();
	}

	public static <T> Float reverseTotal(Result<T> result) {
		return result.getScore().reverseTotal();
	}

	/**
	 * A little help for things like Arrays
	 * @param object
	 * @return formatted string representation
	 */
	public static String toString(Object object) {
		if (object == null) {
			return "";
		}
		
		if (object instanceof Object[]) {
			Object[] array = (Object[]) object;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < array.length; i++) {
				sb.append(array[i].toString());
				if (i < array.length - 1) {
					sb.append(", ");
				}
			}
			return sb.toString();
		} 
		else if (object instanceof float[]) {
			float[] array = (float[]) object;
			StringBuilder sb = new StringBuilder();
			Formatter f = new Formatter(sb);
			for (int i = 0; i < array.length; i++) {
				f.format("%.1f", array[i]);
				if (i < array.length - 1) {
					sb.append(", ");
				}
			}
			return sb.toString();
		}
		else if (object instanceof Float) {
			return String.format("%.0f", object);
		}

		return object.toString();
	}
}
