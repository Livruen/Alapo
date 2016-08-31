/**
 * 
 */
package fh.twoplay.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Natasza Szczypien
 *
 */
public class ImageFabric {
	/**
	 * 
	 */
	private static final int SMALL_SIZE = 50;
	/**
	 * 
	 */
	private static final int BIG_SIZE = 70;

	public static ImageView getImage(String pawnType, boolean isWhite) {

		Image image = null;
		ImageView imageView = new ImageView();

		switch (pawnType) {
		case "B":
			if (isWhite) {
				image = new Image("/fh/twoplay/gui/big_bauer_white.png");

			} else {
				image = new Image("/fh/twoplay/gui/big_bauer_black.png");
			}
			break;
		case "D":
			if (isWhite) {
				image = new Image("/fh/twoplay/gui/big_dame_white.png");

			} else {
				image = new Image("/fh/twoplay/gui/big_dame_black.png");
			}

			break;
		case "d":
			if (isWhite) {
				image = new Image("/fh/twoplay/gui/small_dame_white.png");

			} else {
				image = new Image("/fh/twoplay/gui/small_dame_black.png");
			}

			break;
		case "T":
			if (isWhite) {
				image = new Image("/fh/twoplay/gui/big_turm_white.png");

			} else {
				image = new Image("/fh/twoplay/gui/big_turm_black.png");
			}

			break;
		case "t":
			if (isWhite) {
				image = new Image("/fh/twoplay/gui/small_turm_white.png");

			} else {
				image = new Image("/fh/twoplay/gui/small_turm_black.png");
			}

			break;
		case "L":
			if (isWhite) {
				image = new Image("/fh/twoplay/gui/big_laufer_white.png");

			} else {
				image = new Image("/fh/twoplay/gui/big_laufer_black.png");
			}

			break;
		case "l":
			if (isWhite) {
				image = new Image("/fh/twoplay/gui/small_laufer_white.png");

			} else {
				image = new Image("/fh/twoplay/gui/small_laufer_black.png");
			}

			break;
		}
		imageView.setFitWidth(BIG_SIZE);
		imageView.setFitHeight(BIG_SIZE);
		imageView.setImage(image);
		return imageView;
	}
}
