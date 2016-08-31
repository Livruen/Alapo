package fh.twoplay.helper;

import java.awt.Point;
import java.util.HashMap;

/**
 * @author livruen
 * 
 *         Helper class transforms the column and row names form the Board to
 *         real indexes from the two dimensional board array
 *
 * 
 */
public class ColumnNames {
	protected static HashMap<String, Integer> columnIndex;
	protected static HashMap<Integer, String> columnIndexS;
	protected static final int X_POSITION = 0;
	protected static final int Y_POSITION = 1;
	protected static int size;

	protected static int getSize() {
		return size;
	}

	public static void setSize(int size) {
		ColumnNames.size = size;
	}

	public static int getXIndex(String columnName) {
		return columnIndex.get(columnName);
	}

	public static String getXtoString(int index) {
		return columnIndexS.get(index);
	}

	public static String getYtoString(int y) {
		return "" + (getSize() - y);
	}

	public static int yStringToInteger(String y) {
		return getSize() - Integer.parseInt(y);
	}
	
	


	public HashMap<String, Integer> getColumnIndex() {
		return columnIndex;
	}

	public static void setColumnIndex(HashMap<String, Integer> columnIndex) {
		ColumnNames.columnIndex = columnIndex;
	}

	public HashMap<Integer, String> getColumnIndexS() {
		return columnIndexS;
	}

	public static void setColumnIndexS(HashMap<Integer, String> columnIndexS) {
		ColumnNames.columnIndexS = columnIndexS;
	}

	/**
	 * @param buffer
	 * @return
	 */
	public static Point preparePoint(StringBuffer buffer) {

		int y = yStringToInteger(buffer.charAt(Y_POSITION) + "");

		String letter = buffer.charAt(X_POSITION) + "";
		int x = getXIndex(letter);
		return new Point(y, x);

	}
}
