/**
 * 
 */
package fh.twoplay.gui;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
/**
 * @author Natasza Szczypien
 *
 */
public class DragAndDropEvent implements EventHandler<DragEvent> {

	/* (non-Javadoc)
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	@Override
	public void handle(DragEvent event) {
//		Dragboard db = event.getDragboard();
//		// TODO
//		// Einzige lösung
//		// Pane currentPane = getFieldWherePawnWasDropped(db);
//		ImageView guiPawn = getGuiPawn(db);
//		APawn alapoPawn = getAlapoPawn(guiPawn);
//
//		if (alapoPawn.isWhite() == isWhiteTurn()) {
//
//			System.out.println(guiPawn.getId() + getId());
//
//			EStatus status = board.move(guiPawn.getId() + getId());
//
//			if (alapoPawn.wins(board.getBoard())) {
//				finalMove = 1;
//				status = EStatus.LEGAL;
//			} else if (endGame()) {
//				showWinner();
//				board = null;
//			}
//
//			if (status == EStatus.LEGAL) {
//				// Move ist legal ändere die GUI darstellung
//				int size = pane.getChildren().size();
//				if (size == 1) {
//					ImageView enemy = (ImageView) pane.getChildren().get(0);
//					pawnList.remove(enemy);
//
//					addPoints(alapoPawn);
//
//				}
//
//				removeFromOldField(guiPawn);
//				updateId(guiPawn, pane.getId());
//
//				removeEffect(guiPawn);
//				changePlayersTurn();
//
//				event.setDropCompleted(true);
//				event.consume();
//
//				refreshGui();
//			} 
//
//		} else {
//
//			removeEffect(guiPawn);
//			
//			//TODO klasse 
//			Sleeper sleeper = new Sleeper();
//
//			/* Light the current player to show it's his turn */
//			Bloom bloom = getBloomEffect();
//			guiPawn.setEffect(bloom);
//
//			if (isWhiteTurn()) {
//				PlayerWhitePane.setEffect(bloom);
//			} else {
//				PlayerBlackPane.setEffect(bloom);
//			}
//
//			sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
//				@Override
//				public void handle(WorkerStateEvent event) {
//					PlayerWhitePane.setEffect(null);
//					PlayerBlackPane.setEffect(null);
//					removeEffect(guiPawn);
//				}
//			});
//
//			new Thread(sleeper).start();
//		}
		
	}

}
