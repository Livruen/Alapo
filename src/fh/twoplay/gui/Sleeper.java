/**
 * 
 */
package fh.twoplay.gui;
import javafx.concurrent.Task;
/**
 * @author Natasza Szczypien
 *
 */
public class Sleeper extends Task<Void>{

	/* (non-Javadoc)
	 * @see javafx.concurrent.Task#call()
	 */
	@Override
	protected Void call() throws Exception {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		return null;
	}

}
