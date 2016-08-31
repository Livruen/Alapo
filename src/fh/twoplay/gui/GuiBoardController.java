package fh.twoplay.gui;

import java.awt.Point;

import javafx.scene.Node;
import javafx.scene.input.*;

import java.util.ArrayList;

import javafx.scene.effect.Lighting;
import javafx.scene.effect.Bloom;

import javafx.concurrent.WorkerStateEvent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import fh.twoplay.alapo.AlapoBoard;
import fh.twoplay.base.EStatus;

import fh.twoplay.games.ABoard;

import fh.twoplay.helper.ColumnNames;

import fh.twoplay.pawns.APawn;
import fh.twoplay.pawns.BlankField;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.*;

/**
 * @author Natasza Szczypien
 *
 */
public class GuiBoardController {
	
	@FXML
	private javafx.scene.control.MenuBar menuBar;
	@FXML
	private javafx.scene.control.MenuItem menuNewGame;
	@FXML
	private javafx.scene.control.MenuItem menuClose;
	@FXML
	javafx.scene.control.TextField scoreWhite;
	@FXML
	private javafx.scene.control.TextField scoreBlack;
//	@FXML
//	private javafx.scene.control.Button hintWhite;
//	@FXML
//	private javafx.scene.control.Button hintBlack;
	@FXML
	private javafx.scene.layout.GridPane gridPane;
	@FXML
	private javafx.scene.layout.AnchorPane PlayerWhitePane;
	@FXML
	private javafx.scene.layout.AnchorPane PlayerBlackPane;

	private ABoard board;
	private boolean iswhite;
	private int finalMove = 0;
	private ImageView activeImage;
	private ArrayList<ImageView> pawnList;
	private int whitePoints;
	private int blackPoints;
	private static final int FIELD_SIZE = 70;
	
	/**
	 * File -> close
	 * @param e
	 */
	@FXML
	private void handleMenuCloseAction(ActionEvent e) {
		Platform.exit();
	}
//private GuiBoardView view;
	/**
	 * When File->New Game is clicked
	 * 
	 * @param e
	 */
	@FXML
	private void handleNewGameAction(ActionEvent e) {
		
		init();
		fillBoardWithPanes();
		setPawnList();
		fillWithPawns();

	}

	/**
	 * 
	 */
	private void setPawnList() {
		pawnList = new ArrayList<ImageView>();
		for (int row = 1; row <= 6; row++) {
			for (int column = 1; column <= 6; column++) {

				APawn pawn = board.getPawn(new Point(column - 1, row - 1));
				String pawnType = pawn.getType();
				if (!pawnType.equals(" ")) {
					ImageView image = ImageFabric.getImage(pawnType,
							pawn.isWhite());
					String guiPawnId = getGuiPawnId(row, column);
					image.setId(guiPawnId);
					setImageAction(pawn, image);

					pawnList.add(image);
				}
			}
		}
	}

	private void init() {
		board = new AlapoBoard();
		iswhite = true;
	}

	/**
	 * Fills the board with panes
	 */
	private void fillBoardWithPanes() {

		for (int row = 1; row <= 6; row++) {
			for (int column = 1; column <= 6; column++) {
				/**
				 * The id contains the position on the board like [A2] Why -1?
				 * because the real board starts from 1. It have descriptions in
				 * the grid which are at te zero positions
				 */
				String guiPawnId = getGuiPawnId(row, column);
				Pane pane = getNewPane(guiPawnId);
				setColor(row, column, pane);

				gridPane.add(pane, row, column);
			}
		}

	}

	/**
	 * TODO doesnt work properly
	 */
	private void fillWithPawns() {
		for (ImageView imageView : pawnList) {
			Pane pane = (Pane) getPaneById(imageView.getId());
			pane.getChildren().add(imageView);
		}

	}

	/**
	 * 
	 * @param searchedI
	 * @return searched pane
	 */
	public Pane getPaneById(String searchedId) {
		Pane result = null;
		ObservableList<javafx.scene.Node> childrens = gridPane.getChildren();
		for (javafx.scene.Node node : childrens) {
			//
			if (node.getTypeSelector().equals("Pane")) {
				Pane p = (Pane) node;
				String id = p.getId();
				if (id == null) {
					continue;
				} else if (id.equals(searchedId)) {
					result = (Pane) p;
					break;
				}
			}

		}

		return result;
	}

	/**
	 * creates the format [A2]
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	private String getGuiPawnId(int row, int column) {
		String guiPawnId = ColumnNames.getXtoString(row - 1)
				+ ColumnNames.getYtoString(column - 1);
		return guiPawnId;
	}

	/**
	 * Creates a pane with specific board parameters
	 * 
	 * @param guiPawnId
	 * @return
	 */
	private Pane getNewPane(String guiPawnId) {
		Pane pane = new Pane();
		pane.setPrefSize(FIELD_SIZE, FIELD_SIZE);
		pane.setId(guiPawnId);

		addPaneAction(pane);
		return pane;
	}

	/**
	 * All actions for each Image
	 * 
	 * @param pawn
	 * @param image
	 */
	private void setImageAction(APawn pawn, ImageView image) {
		
		image.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						String moves = board.getMoves(pawn.positionToString());
						showMoves(moves);
					}

					/**
					 * Lights the possible moves for the clicked pawn
					 * 
					 * @param moves
					 */
					protected void showMoves(String moves) {

						String[] temp = moves.split(" ");
						ArrayList<String> posibleMoves = new ArrayList<String>();

						for (String string : temp) {
							String temp2 = string.substring(2);
							posibleMoves.add(temp2);
						}
						
						/*
						 * Lights the possible moves for 2 seconds of clicked
						 * Image
						 */
						lightPanesForFiveSeconds(posibleMoves);

						/* Sleeps 2 seconds */
						Sleeper sleeper = new Sleeper();
//						Task<Void> sleeper = new Task<Void>() {
//							@Override
//							protected Void call() throws Exception {
//								try {
//									Thread.sleep(1000);
//								} catch (InterruptedException e) {
//								}
//								return null;
//							}
//						};

						/*
						 * When sleeper wakes up delete the effect from pane
						 */
						sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
							@Override
							public void handle(WorkerStateEvent event) {
								lightsOff(posibleMoves);
							}
						});
						new Thread(sleeper).start();
					}

					/**
					 * Gets the pane container from the clicked Image
					 * 
					 * @param searchedId
					 * @return pane where the image is in
					 */
					public Pane getImagesPaneContainer(String searchedId) {

						Pane result = null;

						// Grid pane searches his childs
						ObservableList<javafx.scene.Node> childrens = gridPane
								.getChildren();

						for (javafx.scene.Node node : childrens) {
							/* searches for panes in grid */
							if (node.getTypeSelector().equals("Pane")) {
								Pane p = (Pane) node;
								String id = p.getId();
								if (id == null) {
									continue;
								} else if (id.equals(searchedId)) {
									result = (Pane) p;
									break;
								}
							}
						}
						return result;
					}

					/**
					 * Sets on the lightning effect
					 * 
					 * @param buffer
					 */
					private void lightPanesForFiveSeconds(
							ArrayList<String> buffer) {

						Lighting lighting = getLightningEffect();
						for (int i = 0; i < buffer.size(); i++) {
							Pane paneFromimage = (Pane) getImagesPaneContainer(buffer
									.get(i));
							paneFromimage.setEffect(lighting);
						}
					}

					/**
					 * Sets off the effect
					 * 
					 * @param possibleMoves
					 */
					private void lightsOff(ArrayList<String> possibleMoves) {
						for (int move = 0; move < possibleMoves.size(); move++) {
							Pane paneFromimage = (Pane) getImagesPaneContainer(possibleMoves
									.get(move));
							paneFromimage.setEffect(null);
						}
					}
				});
		/**
		 * Press the image
		 */
		image.setOnDragDetected((MouseEvent event) -> {

			Bloom bloom = getBloomEffect();
			image.setEffect(bloom);

			activeImage = image;
			Dragboard db = image.startDragAndDrop(TransferMode.MOVE);
			ClipboardContent content = new ClipboardContent();
			// Store node ID in order to know what is dragged.
			content.putString(image.getId());
			db.setContent(content);
			event.consume();
		});
	}

	/**
	 * All pane actions
	 * 
	 * @param pane
	 */
	private void addPaneAction(Pane pane) {

		pane.setOnDragOver((DragEvent event) -> {
			if (event.getGestureSource() != pane
					&& event.getDragboard().hasString()) {
				event.acceptTransferModes(TransferMode.MOVE);
			}
			event.consume();
		});
		
		pane.setOnDragDropped((DragEvent event) -> {
			Dragboard db = event.getDragboard();
			// TODO
			// Einzige lösung
			// Pane currentPane = getFieldWherePawnWasDropped(db);
			ImageView guiPawn = getGuiPawn(db);
			APawn alapoPawn = getAlapoPawn(guiPawn);

			if (alapoPawn.isWhite() == isWhiteTurn()) {

				System.out.println(guiPawn.getId() + pane.getId());

				EStatus status = board.move(guiPawn.getId() + pane.getId());

				if (alapoPawn.wins(board.getBoard())) {
					finalMove = 1;
					status = EStatus.LEGAL;
				} else if (endGame()) {
					showWinner();
					board = null;
				}

				if (status == EStatus.LEGAL) {
					// Move ist legal ändere die GUI darstellung
					int size = pane.getChildren().size();
					if (size == 1) {
						ImageView enemy = (ImageView) pane.getChildren().get(0);
						pawnList.remove(enemy);

						addPoints(alapoPawn);

					}

					removeFromOldField(guiPawn);
					updateId(guiPawn, pane.getId());

					removeEffect(guiPawn);
					changePlayersTurn();

					event.setDropCompleted(true);
					event.consume();

					refreshGui();
				} 

			} else {

				removeEffect(guiPawn);
				
				//TODO klasse 
				Sleeper sleeper = new Sleeper();

				/* Light the current player to show it's his turn */
				Bloom bloom = getBloomEffect();
				guiPawn.setEffect(bloom);

				if (isWhiteTurn()) {
					PlayerWhitePane.setEffect(bloom);
				} else {
					PlayerBlackPane.setEffect(bloom);
				}

				sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
					@Override
					public void handle(WorkerStateEvent event) {
						PlayerWhitePane.setEffect(null);
						PlayerBlackPane.setEffect(null);
						removeEffect(guiPawn);
					}
				});

				new Thread(sleeper).start();
			}

		});
	}

	private void showWinner() {
		javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
				javafx.scene.control.Alert.AlertType.INFORMATION);
		alert.setTitle("The end");
		alert.setHeaderText("Player "
				+ (iswhite ? "black" : "white") + " wins.");
		alert.setContentText("I have a great message for you!");

		alert.showAndWait();
		Platform.exit();
	}

	private boolean endGame() {
		return finalMove == 1 || whitePoints == AlapoBoard.MAX_PAWNS_ON_FIELD
				|| blackPoints == AlapoBoard.MAX_PAWNS_ON_FIELD;
	}

	private void addPoints(APawn alapoPawn) {
		if (alapoPawn.isWhite()) {
			whitePoints++;
		} else {
			blackPoints++;
		}
	}

	/**
	 * refreshes the GUI
	 */
	private void refreshGui() {
		//gridPane.requestLayout();
		removePawns();
		fillBoardWithPanes();
		fillWithPawns();
		setScore();
	}

	/**
	 * 
	 */
	private void setScore() {
		scoreWhite.setText(String.valueOf(whitePoints));
		scoreBlack.setText(String.valueOf(blackPoints));
	}

	/**
	 * removes the bloom effect
	 * 
	 * @param guiPawn
	 */
	private void removeEffect(ImageView guiPawn) {
		guiPawn.setEffect(null);
	}

	/**
	 * Removes the pawn from the field where he stands before
	 * 
	 * @param guiPawn
	 */
	private void removeFromOldField(ImageView guiPawn) {
		Pane parent = (Pane) guiPawn.getParent();
		parent.getChildren().remove(0);
	}

	/**
	 * Entfernt das board
	 */
	private void removePawns() {
		for (int i = 0; i < gridPane.getChildren().size(); i++) {
			Node node = gridPane.getChildren().get(i);
			if (node instanceof Pane) {
				gridPane.getChildren().remove(i);
				i--;
			}
		}
	}

	/**
	 * @param guiPawn
	 */
	private void updateId(ImageView guiPawn, String newId) {
		for (ImageView imageView : pawnList) {
			if (imageView.getId().equals(guiPawn.getId())) {
				imageView.setId(newId);
			}
		}

	}

	/**
	 * die position des image entspricht der position des pawns im feld
	 * 
	 * @param guiPawn
	 * @return
	 */
	private APawn getAlapoPawn(ImageView guiPawn) {
		String imagePosition = guiPawn.getId();
		Point pawnPositionOnField = board.getPawnPositionOnField(imagePosition);
		APawn alapoPawn = board.getPawn(pawnPositionOnField);
		return alapoPawn;
	}

	private ImageView getGuiPawn(Dragboard db) {

		Pane paneWithDroppedImage = getFieldWherePawnWasDropped(db);
		/* PAwn from Board */
		ImageView guiPawn = (ImageView) paneWithDroppedImage.getChildren().get(
				0);
		return guiPawn;
	}

	// TODO
	private Pane getFieldWherePawnWasDropped(Dragboard db) {
		Pane paneWithDroppedImage = (Pane) gridPane
				.lookup("#" + db.getString());
		return paneWithDroppedImage;
	}

	/**
	 * The bloom effect
	 * 
	 * @return
	 */
	private Bloom getBloomEffect() {
		Bloom bloom = new Bloom();
		bloom.setThreshold(0.1);

		return bloom;
	}

	private Lighting getLightningEffect() {
		Lighting lighting = new Lighting();
		lighting.setSurfaceScale(5.0);
		return lighting;
	}

	/**
	 * Gets the colors from stylesheet.css Colors are placed alternately color
	 * for every pane
	 * 
	 * @param row
	 * @param column
	 * @param pane
	 */
	private void setColor(int row, int column, Pane pane) {
		if (row % 2 == 1) {
			if (column % 2 == 1) {
				pane.getStyleClass().add("light_board_places");
			} else {
				pane.getStyleClass().add("dark_board_places");
			}
		} else {
			if (column % 2 == 1) {
				pane.getStyleClass().add("dark_board_places");
			} else {
				pane.getStyleClass().add("light_board_places");
			}
		}
	}

	/**
	 * Players turn
	 * 
	 * @return
	 */
	private boolean isWhiteTurn() {
		return iswhite;
	}

	/**
	 * Changes the player turn.
	 */
	private void changePlayersTurn() {
		if (isWhiteTurn()) {
			iswhite = false;
		} else {
			iswhite = true;
		}
	}

}
