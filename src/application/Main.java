package application;

//Farah Khafate - a00159

import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {
	Random rand = new Random();
	int random = rand.nextInt(101);
	int counter = 0;
	// Add a utility method to check if a string represents a valid integer
	private boolean isInteger(String str) {
	    try {
	        Integer.parseInt(str);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	
	@Override 
	public void start(Stage primaryStage) {
		// create the tabs 
		TabPane alltabs= new TabPane();
		
		// Assignment 2 game
		
		// create gridpane
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		// define instructions
		Text instructions = new Text("Choose a random integer between 0 and 100");
		instructions.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
		grid.add(instructions, 1, 1);

		TextField userGuess = new TextField();
		grid.add(userGuess, 2, 2);

		Label inputText = new Label("Please enter your guess:");
		grid.add(inputText, 1, 2);

		Button submitButton = new Button("Submit");
		grid.add(submitButton, 2, 3);

		Text attemptsCounter = new Text("Attempts Counter : " + counter);
		grid.add(attemptsCounter, 1, 3);

		Text ourGuess = new Text();
		grid.add(ourGuess, 3, 3);

		// add button to give up/play again
		Button giveUp = new Button("Give up");
		grid.add(giveUp, 1, 4);
		giveUp.setOnAction(f -> {
			ourGuess.setText("The answer is: " + random);
			
			Stage stagetwo = new Stage();
            GridPane gridtwo = new GridPane();
            gridtwo.setAlignment(Pos.CENTER);
            gridtwo.setHgap(10);
            gridtwo.setVgap(10);
            gridtwo.setPadding(new Insets(25, 25, 25, 25));
            Scene scenetwo = new Scene(gridtwo, 400, 200);

            Button playAgain = new Button("Play Again ?");
            gridtwo.add(playAgain, 1, 1);

            Button quitGame = new Button("Quit Game");
            gridtwo.add(quitGame, 1, 2);

            playAgain.setOnAction(e -> {
                counter = 0;
                random = rand.nextInt(101);
                stagetwo.close();
                attemptsCounter.setText("Attempts Counter : " + counter);
                ourGuess.setText("");
                userGuess.clear();
            });

            quitGame.setOnAction(e -> {
                stagetwo.close();
                primaryStage.close();
            });

            stagetwo.setScene(scenetwo);
            stagetwo.show();
		});

		submitButton.setOnAction(e -> {
		    String input = userGuess.getText();

		    if (input.isEmpty() || !isInteger(input)) {
		        // handle the case where the input is empty or not an integer
		        ourGuess.setText("Please enter a valid integer.");
		        userGuess.clear();
		    } else {
		        int guess = Integer.parseInt(input);

		        if (guess == random) {
		            // handle the case where the guess is correct
		            ourGuess.setText("Correct Guess!");

		            Stage stagetwo = new Stage();
		            GridPane gridtwo = new GridPane();
		            gridtwo.setAlignment(Pos.CENTER);
		            gridtwo.setHgap(10);
		            gridtwo.setVgap(10);
		            gridtwo.setPadding(new Insets(25, 25, 25, 25));
		            Scene scenetwo = new Scene(gridtwo, 400, 200);

		            Button playAgain = new Button("Play Again ?");
		            gridtwo.add(playAgain, 1, 1);

		            Button quitGame = new Button("Quit Game");
		            gridtwo.add(quitGame, 1, 2);

		            playAgain.setOnAction(f -> {
		                counter = 0;
		                random = rand.nextInt(101);
		                stagetwo.close();
		                attemptsCounter.setText("Attempts Counter : " + counter);
		                ourGuess.setText("");
		                userGuess.clear();
		            });

		            quitGame.setOnAction(f -> {
		                stagetwo.close();
		                primaryStage.close();
		            });

		            stagetwo.setScene(scenetwo);
		            stagetwo.show();

		        } else if (guess < random) {
		            // handle the case where the guess is too low
		            ourGuess.setText("Too low");
		            counter++;
		            attemptsCounter.setText("Attempts Counter : " + counter);
		        } else {
		            // handle the case where the guess is too high
		            ourGuess.setText("Too high");
		            counter++;
		            attemptsCounter.setText("Attempts Counter : " + counter);
		        }
		    }
		});
			
		// create a grid pane and set the size
		VBox vbox = new VBox();
		Menu options = new Menu("Options");
		MenuItem item1 = new MenuItem("New game");
		CheckMenuItem item2 = new CheckMenuItem("Advanced");
		MenuItem item3 = new MenuItem("Exit");
		
		// add items to options menu
		options.getItems().addAll(item1,item2,item3);
		MenuBar menubar = new MenuBar();

		vbox.getChildren().addAll(menubar);
		Menu font = new Menu("Font Size");
		MenuItem item4 = new MenuItem("Small");
		MenuItem item5 = new MenuItem("Large");
		
		// show font
		font.getItems().addAll(item4, item5);
		
		// create theme menu and its items
		Menu theme = new Menu("Theme");
		MenuItem theme1 = new MenuItem("Red");
		MenuItem theme2 = new MenuItem("Black");
		MenuItem theme3 = new MenuItem("Blue");
		theme.getItems().addAll(theme1,theme2,theme3);
		
		// create view menu and show it
		Menu view = new Menu("View");
		view.getItems().addAll(font, theme);
		menubar.getMenus().addAll(options,view);
		Text name = new Text();

		// explain the game
		Text explaination = new Text("Calculate to find x and select the correct answer");
		explaination.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
		vbox.getChildren().add(explaination);
		
		// create buttons in a hbox for answer options
		HBox box = new HBox();
		Button btn1 = new Button(" ");
		Button btn2 = new Button(" ");
		Button btn3 = new Button(" ");
		Button btn4 = new Button(" ");
		
		// disable buttons initially
		btn1.setDisable(true);
		btn2.setDisable(true);
		btn3.setDisable(true);
		btn4.setDisable(true);
		
		// ask the user for the name
		Label username = new Label("Please enter your name:");
		vbox.getChildren().add(username);
		
		// take the name
		TextField userGuess2 = new TextField();
		name.textProperty().bind(userGuess2.textProperty());
		vbox.getChildren().add(userGuess2);

		// use TextFormatter to restrict input to text-only
		userGuess2.setTextFormatter(new TextFormatter<>(change ->
		        (change.getControlNewText().matches("[a-zA-Z]*")) ? change : null));

		// bind the disable property of the buttons to the condition that checks if the input is not an integer
		btn1.disableProperty().bind(userGuess2.textProperty().isEmpty());
		btn2.disableProperty().bind(userGuess2.textProperty().isEmpty());
		btn3.disableProperty().bind(userGuess2.textProperty().isEmpty());
		btn4.disableProperty().bind(userGuess2.textProperty().isEmpty());
		
		// random number between 0 and 10
		Random random2 = new Random();
		int a = random2.nextInt(11);
		int b = random2.nextInt(11) * a;
		int x = b/a;
		String correctA = Integer.toString(x);
		int wrongB = random2.nextInt(11);
		int wrongC = wrongB + 3;
		int wrongD = wrongC + 2;
		String answerB = Integer.toString(wrongB);
		String answerC = Integer.toString(wrongC);
		String answerD = Integer.toString(wrongD);

		// show the question
		Label question = new Label(a + " x = " + b);
		vbox.getChildren().add(question);

		
		box.getChildren().addAll(btn1,btn2,btn3,btn4);
		vbox.getChildren().add(box);

		btn1.setText(correctA);
		btn2.setText(answerB);
		btn3.setText(answerC);
		btn4.setText(answerD);
		
		Text ansFeedback = new Text();
		vbox.getChildren().add(ansFeedback);

		btn1.setOnAction(e-> {
			ansFeedback.setText("Good Job, " + name.getText());
		});
		btn2.setOnAction(e-> {
			ansFeedback.setText("Try Again, " + name.getText());
		});
		btn3.setOnAction(e-> {
			ansFeedback.setText("Try Again, " + name.getText());
		});
		btn4.setOnAction(e-> {
			ansFeedback.setText("Try Again, " + name.getText());
		});
		
		item1.setOnAction(e -> {
			item2.setSelected(false);
			// random number between 0 and 10
			int a1 = random2.nextInt(11);
			int b1 = random2.nextInt(11) * a1;
			int x1 = b1/a1;
			String correctA1 = Integer.toString(x1);
			int wrongB1 = random2.nextInt(11);
			int wrongC1 = wrongB1 + 3;
			int wrongD1 = wrongC1 + 2;
			String answerB1 = Integer.toString(wrongB1);
			String answerC1 = Integer.toString(wrongC1);
			String answerD1 = Integer.toString(wrongD1);
				
			question.setText(a1+" x = "+b1);
			btn1.setText(correctA1);
			btn2.setText(answerB1);
			btn3.setText(answerC1);
			btn4.setText(answerD1);
				
			ansFeedback.setText("");
		});
		
		item2.setOnAction(e -> {
			if (item2.isSelected()) {
				// random number between 0 and 100
				int a1 = random2.nextInt(101);
				int b1 = random2.nextInt(101) * a1;
				int x1 = b1/a1;
				String correctA1 = Integer.toString(x1);
				int wrongB1 = random2.nextInt(101);
				int wrongC1 = wrongB1 + 3;
				int wrongD1 = wrongC1 + 2;
				String answerB1 = Integer.toString(wrongB1);
				String answerC1 = Integer.toString(wrongC1);
				String answerD1 = Integer.toString(wrongD1);
				
				question.setText(a1 + " x = " + b1);
				btn1.setText(correctA1);
				btn2.setText(answerB1);
				btn3.setText(answerC1);
				btn4.setText(answerD1);
				
				ansFeedback.setText("");
			} else {
				// random number between 0 and 10
				int a1 = random2.nextInt(11);
				int b1 = random2.nextInt(11) * a1;
				int x1 = b1/a1;
				String correctA1 = Integer.toString(x1);
				int wrongB1 = random2.nextInt(11);
				int wrongC1 = wrongB1 + 3;
				int wrongD1 = wrongC1 + 2;
				String answerB1 = Integer.toString(wrongB1);
				String answerC1 = Integer.toString(wrongC1);
				String answerD1 = Integer.toString(wrongD1);
				
				question.setText(a1 + " x = " + b1);
				btn1.setText(correctA1);
				btn2.setText(answerB1);
				btn3.setText(answerC1);
				btn4.setText(answerD1);
				
				ansFeedback.setText("");
			
			}
		});

		item3.setOnAction(e -> Platform.exit());
		
		item4.setOnAction(e -> {
			ansFeedback.setFont((Font.font("Calibri", FontWeight.NORMAL, 13)));
			explaination.setFont((Font.font("Calibri", FontWeight.NORMAL, 13)));
			username.setFont((Font.font("Calibri", FontWeight.NORMAL, 13)));
			question.setFont((Font.font("Calibri", FontWeight.NORMAL, 13)));
			
		});
		
		item5.setOnAction(e -> {
			ansFeedback.setFont((Font.font("Calibri", FontWeight.NORMAL, 20)));
			explaination.setFont((Font.font("Calibri", FontWeight.NORMAL, 20)));
			username.setFont((Font.font("Calibri", FontWeight.NORMAL, 20)));
			question.setFont((Font.font("Calibri", FontWeight.NORMAL, 20)));
			
		});
		
		theme1.setOnAction(e->{
			vbox.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
		});
		theme2.setOnAction(e->{
			vbox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		    explaination.setFill(Color.WHITE);

		});
		theme3.setOnAction(e->{
			vbox.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		});
		
		
		Tab guessgame = new Tab("Guess the Number Game", grid);
		Tab findx = new Tab("Find x Game", vbox);
		alltabs.getTabs().addAll(guessgame,findx);
		
		// show the scene and the stage
		primaryStage.setScene(new Scene(alltabs, 600, 400));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}