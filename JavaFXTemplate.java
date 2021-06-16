/*
 * Keno JavaFX GUI 
 * Project 2
 * Description
 */

import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class JavaFXTemplate extends Application {

	Stage window;
	Scene scene1, scene2, scene3, currentScene;

	// 1 draw event containing 20 sub-draws
	private final int TOTAL_DRAWINGS = 20;
	private int i = 0, j = 0, k = 0, m = 0;

	private static ArrayList<ToggleButton> tglist = new ArrayList<ToggleButton>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;

		// Scene 3 Text (Shows rules or odds based on user's choice)
		Text text1 = new Text("TEXT");

		/* Scene 1 Starts */
		VBox layout1 = new VBox(10);
		scene1 = new Scene(layout1, 800, 640);

		// Menu
		Menu rulesMenu = new Menu("Rules");
		Menu winOddsMenu = new Menu("Odds of Winning");
		Menu exitMenu = new Menu("Exit");

		// Create a menu bar
		MenuBar menuBar = new MenuBar();
		// Add menus to a menu bar
		menuBar.getMenus().addAll(rulesMenu, winOddsMenu, exitMenu);

		// Create the Menu Box
		HBox menu = new HBox(20);
		// Add the MenuBar to the Menu Box
		menu.getChildren().add(menuBar);

		// Sub Menu
		MenuItem exitConfirm = new MenuItem("Confirm Exit");
		exitConfirm.setOnAction(e -> window.close());
		MenuItem rule1 = new MenuItem("Rules");
		rule1.setOnAction((ActionEvent) -> {
			try {
				text1.setText(KenoManual.showText(0));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			currentScene = scene1;
			window.setScene(scene3);
		});
		MenuItem odd1 = new MenuItem("Odds");
		odd1.setOnAction((ActionEvent) -> {
			try {
				text1.setText(KenoManual.showText(1));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			currentScene = scene1;
			window.setScene(scene3);
		});

		// Adding Sub Menu
		exitMenu.getItems().addAll(exitConfirm);
		rulesMenu.getItems().addAll(rule1);
		winOddsMenu.getItems().addAll(odd1);

		// Button 1
		Button button1 = new Button("Play");
		button1.setOnAction(e -> window.setScene(scene2));

		// Welcome
		Label welcomeText = new Label("Welcome to Keno");
		welcomeText.setAlignment(Pos.BASELINE_CENTER);
		welcomeText.setStyle("-fx-font: 24px \"Serif\";");

		layout1.setStyle("-fx-padding: 5;");
		layout1.getChildren().addAll(menu, welcomeText, button1);

		/* Scene 2 Starts */

		VBox layout2 = new VBox(10);
		scene2 = new Scene(layout2, 800, 640);

		// Texts
		Label amountText = new Label("Amount");
		Label drawText = new Label("Draws");
		Label spotText = new Label("Spots");

		// Random input CheckBox
		Label checkBoxText = new Label("Random Input");
		CheckBox checkBox = new CheckBox();
		checkBox.setDisable(true);
		HBox checkBoxBox = new HBox(10);
		checkBoxBox.getChildren().addAll(checkBoxText, checkBox);

		// Menu
		Menu rulesMenu2 = new Menu("Rules");
		Menu winOddsMenu2 = new Menu("Odds of Winning");
		Menu newLook2 = new Menu("New Look");
		Menu exitMenu2 = new Menu("Exit");

		// Sub Menu
		MenuItem exitConfirm2 = new MenuItem("Confirm Exit");
		exitConfirm2.setOnAction(e -> window.close());
		MenuItem rule2 = new MenuItem("Rules");
		rule2.setOnAction((ActionEvent) -> {
			try {
				text1.setText(KenoManual.showText(0));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			currentScene = scene2;
			window.setScene(scene3);
		});
		MenuItem odd2 = new MenuItem("Odds");
		odd2.setOnAction((ActionEvent) -> {
			try {
				text1.setText(KenoManual.showText(1));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			currentScene = scene2;
			window.setScene(scene3);
		});

		// Two flavors of look are provided
		MenuItem defaultLook = new MenuItem("Default");
		MenuItem modernLook = new MenuItem("Modern");

		// Menu Bar
		MenuBar menuBar2 = new MenuBar();
		menuBar2.getMenus().addAll(rulesMenu2, winOddsMenu2, newLook2, exitMenu2);

		// Placing Menu Bar in HBOX, to be placed at top of the window
		HBox menu2 = new HBox(20);
		menu2.getChildren().add(menuBar2);

		// Adding sub menu
		exitMenu2.getItems().addAll(exitConfirm2);
		newLook2.getItems().addAll(defaultLook, modernLook);
		rulesMenu2.getItems().addAll(rule2);
		winOddsMenu2.getItems().addAll(odd2);

		// Back Button
		Button backButton = new Button("Back");

		// Clear Button
		Button clearButton = new Button("Clear");

		// Draw Button
		Button draw = new Button("Draw");
		draw.setDisable(true);

		// Bet Amount
		RadioButton amount1 = new RadioButton("$1");
		RadioButton amount2 = new RadioButton("$2");
		RadioButton amount3 = new RadioButton("$3");
		RadioButton amount5 = new RadioButton("$5");
		RadioButton amount10 = new RadioButton("$10");

		// Spot ToggleGroup
		ToggleGroup amountRadioGroup = new ToggleGroup();

		// Assigning Toggle Group
		amount1.setToggleGroup(amountRadioGroup);
		amount2.setToggleGroup(amountRadioGroup);
		amount3.setToggleGroup(amountRadioGroup);
		amount5.setToggleGroup(amountRadioGroup);
		amount10.setToggleGroup(amountRadioGroup);

		// Set Default amount to $1
		amountRadioGroup.selectToggle(amount1);

		// Set No. of Draws
		amount1.setOnAction(e -> KenoBackend.setAmount(1));
		amount2.setOnAction(e -> KenoBackend.setAmount(2));
		amount3.setOnAction(e -> KenoBackend.setAmount(3));
		amount5.setOnAction(e -> KenoBackend.setAmount(5));
		amount10.setOnAction(e -> KenoBackend.setAmount(10));

		// No. of Drawings Holder
		HBox amountBox = new HBox(20, amount1, amount2, amount3, amount5, amount10);

		// No. of Drawings
		RadioButton numDraw1 = new RadioButton("1");
		RadioButton numDraw2 = new RadioButton("2");
		RadioButton numDraw3 = new RadioButton("3");
		RadioButton numDraw4 = new RadioButton("4");

		// Spot ToggleGroup
		ToggleGroup numDrawRadioGroup = new ToggleGroup();

		// Assigning Toggle Group
		numDraw1.setToggleGroup(numDrawRadioGroup);
		numDraw2.setToggleGroup(numDrawRadioGroup);
		numDraw3.setToggleGroup(numDrawRadioGroup);
		numDraw4.setToggleGroup(numDrawRadioGroup);

		// Set Default draws to 1
		numDrawRadioGroup.selectToggle(numDraw1);

		// Set No. of Draws
		// Each draw will have 20 sub drawings
		numDraw1.setOnAction(e -> KenoBackend.setNoOfDraws(20));
		numDraw2.setOnAction(e -> KenoBackend.setNoOfDraws(40));
		numDraw3.setOnAction(e -> KenoBackend.setNoOfDraws(60));
		numDraw4.setOnAction(e -> KenoBackend.setNoOfDraws(80));

		// No. of Drawings Holder
		HBox numDrawBox = new HBox(20, numDraw1, numDraw2, numDraw3, numDraw4);

		// Spots
		RadioButton spot1 = new RadioButton("1");
		RadioButton spot4 = new RadioButton("4");
		RadioButton spot8 = new RadioButton("8");
		RadioButton spot10 = new RadioButton("10");

		// Spot ToggleGroup
		ToggleGroup spotRadioGroup = new ToggleGroup();

		// Assigning Toggle Group
		spot1.setToggleGroup(spotRadioGroup);
		spot4.setToggleGroup(spotRadioGroup);
		spot8.setToggleGroup(spotRadioGroup);
		spot10.setToggleGroup(spotRadioGroup);

		// Spot Holder
		HBox spotBox = new HBox(20, spot1, spot4, spot8, spot10);

		// Create a Grid Pane to Hold Spots 1-80
		GridPane gridPane = new GridPane();

		// Nodes are disabled until spots are selected
		gridPane.setDisable(true);

		// Enable Grid Pane when the spot is selected
		spot1.setOnAction((ActionEvent) -> {

			gridPane.setDisable(false);
			checkBox.setDisable(false);
			draw.setDisable(false);
		});
		spot4.setOnAction((ActionEvent) -> {

			gridPane.setDisable(false);
			checkBox.setDisable(false);
			draw.setDisable(false);
		});
		spot8.setOnAction((ActionEvent) -> {

			gridPane.setDisable(false);
			checkBox.setDisable(false);
			draw.setDisable(false);
		});
		spot10.setOnAction((ActionEvent) -> {

			gridPane.setDisable(false);
			checkBox.setDisable(false);
			draw.setDisable(false);
		});

		// Create 80 Buttons Dynamically
		for (i = 1; i <= 80; i++) {
			ToggleButton btnNumber = new ToggleButton();
			btnNumber.setText(String.valueOf(i));
			btnNumber.setMinWidth(40);
			btnNumber.setOnAction((ActionEvent) -> {
				KenoBackend.storeSelectedSpots(btnNumber.getText(), btnNumber.isSelected());
			});

			// Adding buttons to a list for later operations.
			tglist.add(btnNumber);
			// Adding buttons to the gridPane
			gridPane.add(btnNumber, j, k, 1, 1);

			j++;
			if (i % 8 == 0) {
				k++;
				j = 0;
			}

		}

		// Draw Output Label
		Label drawNotice = new Label("Draw Result:");
		Label drawOutput = new Label("NILL");
		Label amountWonNotice = new Label("Amount Won:");
		Label amountWonOutput = new Label("NILL");

		// Labels for Result History Tab
		Label selectedChoiceText = new Label("Choices Provided:");
		Label selectedChoice = new Label("NILL");
		Label selectedBetText = new Label("Bet");
		Label selectedBet = new Label("NILL");
		Label totalBetText = new Label("Total Bet");
		Label totalBet = new Label("NILL");
		Label noOfMatchedText = new Label("No. of Matched Items");
		Label itemsMatchedText = new Label("Matched Items");
		Label wonText = new Label("Won");
		Label drawNoText = new Label("Draw");
		Label draw1 = new Label("1");
		Label draw2 = new Label("2");
		Label draw3 = new Label("3");
		Label draw4 = new Label("4");
		Label oneNoOfMatched = new Label("NILL");
		Label oneItemsMatched = new Label("NILL");
		Label oneWon = new Label("NILL");
		Label twoNoOfMatched = new Label("NILL");
		Label twoItemsMatched = new Label("NILL");
		Label twoWon = new Label("NILL");
		Label threeNoOfMatched = new Label("NILL");
		Label threeItemsMatched = new Label("NILL");
		Label threeWon = new Label("NILL");
		Label fourNoOfMatched = new Label("NILL");
		Label fourItemsMatched = new Label("NILL");
		Label fourWon = new Label("NILL");

		Label hintUser = new Label("Try Your Luck");
		hintUser.setStyle("-fx-padding: 80; -fx-font: 24px \"Serif\";");

		// Show Results in a single horizontal tab
		HBox drawBox = new HBox(10);
		drawBox.getChildren().addAll(drawNotice, drawOutput, amountWonNotice, amountWonOutput);

		// Let the draw Begin
		draw.setOnAction((ActionEvent) -> {

			// Extracting button's text
			RadioButton sRB = (RadioButton) spotRadioGroup.getSelectedToggle();
			String str = sRB.getText();

			// check validity of inputs and remaining draws before proceeding
			if (KenoBackend.checkSpots(str) == true && KenoBackend.getNoOfDraws() > 0
					|| checkBox.isSelected() == true) {

				// A draw is executed (1-4 draws)
				KenoBackend.nextDraw();

				// Extracting button's text
				RadioButton nRB = (RadioButton) numDrawRadioGroup.getSelectedToggle();
				String nstr = nRB.getText();

				// Update Labels
				// Selected Choices by user or random
				selectedChoice.setText(KenoBackend.getSelectedList());
				selectedBet.setText("$" + KenoBackend.getUserAmount());
				totalBet.setText("$" + Integer.parseInt(KenoBackend.getUserAmount()) * Integer.parseInt(nstr));

				hintUser.setText("Drawing!");
				KenoBackend.drawExecute();

				// Disable unnecessary tabs
				amountBox.setDisable(true);
				spotBox.setDisable(true);
				numDrawBox.setDisable(true);
				gridPane.setDisable(true);
				draw.setDisable(true);
				backButton.setDisable(true);
				clearButton.setDisable(true);
				checkBox.setDisable(true);

				/*
				 * Adding Pause between Draws Threaded Execution, allows other functions to
				 * execute between drawings.
				 */
				Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						drawOutput.setText(KenoBackend.getDrawResult(m));

						// Looping until all draws are complete
						KenoBackend.decrementNoOfDraws();
						m++;

						// A Draw completes i.e 20 sub draws
						if (m == 20) {
							String tempAmount;

							hintUser.setText("Continue to Next Draw");
							if (Integer.parseInt(nstr) == KenoBackend.getCurrentDraw()) {
								hintUser.setText("Game Over");
								draw.setDisable(true);
								// User can go back after game overs
								backButton.setDisable(false);
								// User can Clear after game overs
								clearButton.setDisable(false);
							} else {
								draw.setDisable(false);
							}

							// Show results
							tempAmount = KenoBackend.getAmountResult();
							amountWonOutput.setText("$" + KenoBackend.getTotalAmountWon());

							if (KenoBackend.getCurrentDraw() == 1) {
								oneNoOfMatched.setText(KenoBackend.getMatchedSize());
								oneItemsMatched.setText(KenoBackend.getMatchedList());
								oneWon.setText("$" + tempAmount);
							} else if (KenoBackend.getCurrentDraw() == 2) {
								twoNoOfMatched.setText(KenoBackend.getMatchedSize());
								twoItemsMatched.setText(KenoBackend.getMatchedList());
								twoWon.setText("$" + tempAmount);
							} else if (KenoBackend.getCurrentDraw() == 3) {
								threeNoOfMatched.setText(KenoBackend.getMatchedSize());
								threeItemsMatched.setText(KenoBackend.getMatchedList());
								threeWon.setText("$" + tempAmount);
							} else if (KenoBackend.getCurrentDraw() == 4) {
								fourNoOfMatched.setText(KenoBackend.getMatchedSize());
								fourItemsMatched.setText(KenoBackend.getMatchedList());
								fourWon.setText("$" + tempAmount);
							} else {
								throw new RuntimeException("Illegal Draw");
							}

							// Reset
							KenoBackend.resetDraw();
						}
					}
				}));

				// Reset printing draws position to zero
				m = 0;

				// 20 Draws
				timeline.setCycleCount(TOTAL_DRAWINGS - m);
				timeline.play();

			} else {
				// do Warn User
				hintUser.setText("Check Your Spots");
			}
		});

		// Back Button Returns to Main Menu and Resets the Game
		backButton.setOnAction((ActionEvent) -> {
			i = 0;
			j = 0;
			k = 0;
			spotBox.setDisable(false);
			amountBox.setDisable(false);
			numDrawBox.setDisable(false);
			amountRadioGroup.selectToggle(amount1);
			numDrawRadioGroup.selectToggle(numDraw1);
			spotRadioGroup.selectToggle(null);
			draw.setDisable(true);
			drawOutput.setText("NILL");
			selectedChoice.setText("NILL");
			amountWonOutput.setText("NILL");
			oneNoOfMatched.setText("NILL");
			twoNoOfMatched.setText("NILL");
			threeNoOfMatched.setText("NILL");
			fourNoOfMatched.setText("NILL");
			oneItemsMatched.setText("NILL");
			twoItemsMatched.setText("NILL");
			threeItemsMatched.setText("NILL");
			fourItemsMatched.setText("NILL");
			totalBet.setText("NILL");
			selectedBet.setText("NILL");
			oneWon.setText("NILL");
			twoWon.setText("NILL");
			threeWon.setText("NILL");
			fourWon.setText("NILL");
			hintUser.setText("Try Your Luck");

			// Enabling gridPane to fire
			gridPane.setDisable(false);
			// Reset all nodes
			for (i = 1; i <= 80; i++) {
				ToggleButton btn = new ToggleButton();
				btn = tglist.get(i - 1);
				if (btn.isSelected())
					btn.fire();
			}
			gridPane.setDisable(true);

			// Enabling checkBox to fire
			checkBox.setDisable(false);
			if (checkBox.isSelected())
				checkBox.fire();
			checkBox.setDisable(true);

			KenoBackend.resetGame();

			// Go to Main Menu
			window.setScene(scene1);
		});

		clearButton.setOnAction((ActionEvent) -> {

			i = 0;
			j = 0;
			k = 0;
			spotBox.setDisable(false);
			amountBox.setDisable(false);
			numDrawBox.setDisable(false);
			amountRadioGroup.selectToggle(amount1);
			numDrawRadioGroup.selectToggle(numDraw1);
			spotRadioGroup.selectToggle(null);
			draw.setDisable(true);
			checkBox.setDisable(true);
			drawOutput.setText("NILL");
			selectedChoice.setText("NILL");
			amountWonOutput.setText("NILL");
			oneNoOfMatched.setText("NILL");
			twoNoOfMatched.setText("NILL");
			threeNoOfMatched.setText("NILL");
			fourNoOfMatched.setText("NILL");
			oneItemsMatched.setText("NILL");
			twoItemsMatched.setText("NILL");
			threeItemsMatched.setText("NILL");
			fourItemsMatched.setText("NILL");
			totalBet.setText("NILL");
			selectedBet.setText("NILL");
			oneWon.setText("NILL");
			twoWon.setText("NILL");
			threeWon.setText("NILL");
			fourWon.setText("NILL");
			hintUser.setText("Try Your Luck");

			// Enabling gridPane to fire
			gridPane.setDisable(false);
			// Reset all nodes
			for (i = 1; i <= 80; i++) {
				ToggleButton btn = new ToggleButton();
				btn = tglist.get(i - 1);
				if (btn.isSelected())
					btn.fire();
			}
			gridPane.setDisable(true);

			checkBox.setDisable(false);
			if (checkBox.isSelected())
				checkBox.fire();
			checkBox.setDisable(true);

			KenoBackend.resetGame();
		});

		// On setting checkBox, manual input is restricted
		checkBox.setOnAction((ActionEvent) -> {

			if (checkBox.isSelected() == true) {
				RadioButton sRB = (RadioButton) spotRadioGroup.getSelectedToggle();
				String str = sRB.getText();
				KenoBackend.setRandom(str);

				// Prevent manual input
				gridPane.setDisable(false);
				// Reset all nodes
				for (i = 1; i <= 80; i++) {
					ToggleButton btn = new ToggleButton();
					btn = tglist.get(i - 1);
					if (btn.isSelected())
						btn.fire();
				}
				gridPane.setDisable(true);

				spotBox.setDisable(true);

			} else {
				gridPane.setDisable(false);
				spotBox.setDisable(false);
			}

		});

		/*
		 * Setting User Interface Arranging tabs
		 */
		VBox vbox1 = new VBox(10);
		VBox vbox21 = new VBox(10);
		VBox vbox2 = new VBox(10);
		HBox drawAndClear = new HBox(20);

		drawAndClear.getChildren().addAll(draw, clearButton);

		vbox1.getChildren().addAll(backButton, amountText, amountBox, drawText, numDrawBox, spotText, spotBox, gridPane,
				checkBoxBox, drawAndClear);

		// Displays history Tab (Right Window below Selected Choice)
		GridPane tab = new GridPane();
		tab.setHgap(30);
		tab.setVgap(10);

		tab.add(drawNoText, 0, 0, 1, 1);
		tab.add(noOfMatchedText, 1, 0, 1, 1);
		tab.add(itemsMatchedText, 2, 0, 1, 1);
		tab.add(wonText, 3, 0, 1, 1);

		tab.add(draw1, 0, 1, 1, 1);
		tab.add(oneNoOfMatched, 1, 1, 1, 1);
		tab.add(oneItemsMatched, 2, 1, 1, 1);
		tab.add(oneWon, 3, 1, 1, 1);

		tab.add(draw2, 0, 2, 1, 1);
		tab.add(twoNoOfMatched, 1, 2, 1, 1);
		tab.add(twoItemsMatched, 2, 2, 1, 1);
		tab.add(twoWon, 3, 2, 1, 1);

		tab.add(draw3, 0, 3, 1, 1);
		tab.add(threeNoOfMatched, 1, 3, 1, 1);
		tab.add(threeItemsMatched, 2, 3, 1, 1);
		tab.add(threeWon, 3, 3, 1, 1);

		tab.add(draw4, 0, 4, 1, 1);
		tab.add(fourNoOfMatched, 1, 4, 1, 1);
		tab.add(fourItemsMatched, 2, 4, 1, 1);
		tab.add(fourWon, 3, 4, 1, 1);

		HBox betTab = new HBox(20);
		betTab.getChildren().addAll(selectedBetText, selectedBet, totalBetText, totalBet);

		vbox21.getChildren().addAll(selectedChoiceText, selectedChoice, betTab, tab, hintUser);

		vbox2.getChildren().addAll(drawBox, vbox21);

		HBox hbox12 = new HBox(30);
		hbox12.getChildren().addAll(vbox1, vbox2);

		layout2.getChildren().addAll(menu2, hbox12);
		layout2.setStyle("-fx-padding: 5;");

		/*
		 * New Look (Modern) Adding CSS
		 */
		modernLook.setOnAction((ActionEvent) -> {

			layout2.setStyle("-fx-padding: 5; -fx-background-color: MINTCREAM;");
			menuBar2.setStyle("-fx-background-color: GAINSBORO; -fx-font: 14px \"Serif\";");
			draw.setStyle("-fx-background-color: NAVY; -fx-text-fill: white;" + " -fx-font: 14px \"Serif\";");
			clearButton.setStyle("-fx-background-color: NAVY; -fx-text-fill: white;" + " -fx-font: 14px \"Serif\";");
			backButton.setStyle("-fx-background-color: NAVY; -fx-text-fill: white;" + "-fx-font: 14px \"Serif\";");
			checkBoxText.setStyle("-fx-font: 14px \"Serif\"; -fx-font-style: italic;");
			amountText.setStyle("-fx-font: 14px \"Serif\"; -fx-font-style: italic;");
			drawText.setStyle("-fx-font: 14px \"Serif\"; -fx-font-style: italic;");
			spotText.setStyle("-fx-font: 14px \"Serif\"; -fx-font-style: italic;");
			vbox2.setStyle("-fx-font: 14px \"Serif\"; -fx-font-style: italic;");
			tab.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
					+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: NAVY;");
			hintUser.setStyle("-fx-text-fill: NAVY; -fx-padding: 80; -fx-font: 24px \"Serif\";");

			for (i = 1; i <= 80; i++) {
				ToggleButton btn = new ToggleButton();
				btn = tglist.get(i - 1);
				btn.setStyle("-fx-text-fill: NAVY; -fx-font: 14 \"Serif\"; -fx-border-color: NAVY;");
			}

		});

		// Default look resets all CSS set by the Modern Look
		defaultLook.setOnAction((ActionEvent) -> {

			layout2.setStyle("-fx-padding: 5;");
			menuBar2.setStyle(null);
			draw.setStyle(null);
			clearButton.setStyle(null);
			backButton.setStyle(null);
			checkBoxText.setStyle(null);
			amountText.setStyle(null);
			drawText.setStyle(null);
			spotText.setStyle(null);
			vbox2.setStyle(null);
			tab.setStyle(null);
			hintUser.setStyle("-fx-padding: 80; -fx-font: 24px \"Serif\";");

			for (i = 1; i <= 80; i++) {
				ToggleButton btn = new ToggleButton();
				btn = tglist.get(i - 1);
				btn.setStyle(null);
			}
		});

		/* Scene 3 Starts */
		VBox layout3 = new VBox(10);
		scene3 = new Scene(layout3, 800, 640);

		// Back Button Scene 3
		Button rulesBackButton = new Button("Back");
		rulesBackButton.setOnAction(e -> window.setScene(currentScene));

		ScrollPane scrollArea1 = new ScrollPane();

		text1.wrappingWidthProperty().bind(scene3.widthProperty());
		scrollArea1.setFitToWidth(true);
		scrollArea1.setContent(text1);

		layout3.getChildren().addAll(rulesBackButton, scrollArea1);
		layout3.setStyle("-fx-padding: 5;");

		window.setScene(scene1);
		window.setTitle("KENO");
		window.show();
	}
}