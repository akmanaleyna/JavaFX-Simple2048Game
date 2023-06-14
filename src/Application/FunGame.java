package Application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import java.lang.Math;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class FunGame extends Application {

	// stores the array of buttons that represents the screen
	private Button[][] buttons;

	/**
	 * creates the GUI display
	 * 
	 * @param primaryStage the main window
	 */
	public void start(@SuppressWarnings("exports") Stage primaryStage) {
		// stores the width/length of the board
		int width = 4;
		int length = 4;

		buttons = new Button[length][width];
		GridPane grid = new GridPane();
		// gridte hücreler arasında boşluk bıraktık
		grid.setPadding(new Insets(10));
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setStyle("-fx-background-color: transparent;");// scene arkaplan rengi belli olsun diye grid arkaplan
															// rengini transparan yaptık

		// initializes every button as a blank button
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				buttons[i][j] = new Button("  ");
				buttons[i][j].setPrefSize(110, 110); // Buttonu istenilen Boyuta ayarldık
				buttons[i][j].setFocusTraversable(false);// Buttona odaklanmayı kapattık
				buttons[i][j].setStyle("-fx-background-color: D8C4B6;");// Butonun Arkaplan rengini değiştirdik
				grid.add(buttons[i][j], j, i);
			}
		}

		// stores the two indices to start one random square off as one
		int indOne = (int) (Math.random() * width);
		int indTwo = (int) (Math.random() * length);
		buttons[indOne][indTwo].setText("1");

		// GridPane i Border Pane İçine koyarak Stage te ortalayabildik
		BorderPane root = new BorderPane();
		root.setCenter(grid);
		// scene arkaplan rengi belli olsun diye BorderPane arkaplan rengini transparan
		// yaptık
		root.setStyle("-fx-background-color: transparent;");

		// new scene for the grid
		Scene scene = new Scene(root);
		// arkaplan rengini değiştirdik
		scene.setFill(Color.web("#F5EFE7"));
		// buttons on the left,right,top,bottom side clicked
		// burada sag sol yukarı ve aşağıya tuşlarına basıldığı zaman yapılacak işlemler
		// yer alacak
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				int[][] sayiMatris = buttonToInt(buttons);
				// Bunu eğer hareket ettirdiğimizde herhangi bir değişim olmazsa diye ekledik
				Boolean flag = false;
				switch (event.getCode()) {

				case W:
				case UP:
					for (int i = 3; i >= 0; i--) {
						for (int j = 3; j > 0; j--) {
							if (sayiMatris[j][i] == sayiMatris[j - 1][i] && sayiMatris[j][i] != 0) {
								sayiMatris[j - 1][i] += sayiMatris[j][i];
								sayiMatris[j][i] = 0;
								flag = true;
								if(sayiMatris[j - 1][i]==2048)
									win();
							}
							if (sayiMatris[j - 1][i] == 0 && sayiMatris[j][i] != 0) {
								sayiMatris[j - 1][i] += sayiMatris[j][i];
								sayiMatris[j][i] = 0;
								flag = true;
							}

						}
					}
					intToButton(sayiMatris);
					sayiMatris = buttonToInt(buttons);
					// sayılarda toplama yapıldığı zaman bazı matrisler arasında olmaması gereken
					// boşluklar oluyordu onlar olmasın diye tekrardan for döngüsüne soktum ve o
					// problemi çözdüm
					for (int i = 3; i >= 0; i--) {
						for (int j = 3; j > 0; j--) {
							if (sayiMatris[j - 1][i] == 0 && sayiMatris[j][i] != 0) {
								sayiMatris[j - 1][i] += sayiMatris[j][i];
								sayiMatris[j][i] = 0;
							}

						}
					}
					intToButton(sayiMatris);
					break;
				case S:
				case DOWN:
					for (int i = 0; i <= 3; i++) {
						for (int j = 0; j < 3; j++) {
							if (sayiMatris[j][i] == sayiMatris[j + 1][i] && sayiMatris[j][i] != 0) {
								sayiMatris[j + 1][i] += sayiMatris[j][i];
								sayiMatris[j][i] = 0;
								flag = true;
								if(sayiMatris[j + 1][i]==2048)
									win();
							}
							if (sayiMatris[j + 1][i] == 0 && sayiMatris[j][i] != 0) {
								sayiMatris[j + 1][i] += sayiMatris[j][i];
								sayiMatris[j][i] = 0;
								flag = true;
							}
						}
					}
					intToButton(sayiMatris);
					sayiMatris = buttonToInt(buttons);
					// sayılarda toplama yapıldığı zaman bazı matrisler arasında olmaması gereken
					// boşluklar oluyordu onlar olmasın diye tekrardan for döngüsüne soktum ve o
					// problemi çözdüm
					for (int i = 0; i <= 3; i++) {
						for (int j = 0; j < 3; j++) {
							if (sayiMatris[j + 1][i] == 0 && sayiMatris[j][i] != 0) {
								sayiMatris[j + 1][i] += sayiMatris[j][i];
								sayiMatris[j][i] = 0;
							}
						}
					}
					intToButton(sayiMatris);
					break;
				case A:
				case LEFT:
					for (int i = 3; i >= 0; i--) {
						for (int j = 3; j > 0; j--) {
							if (sayiMatris[i][j] == sayiMatris[i][j - 1] && sayiMatris[i][j] != 0) {
								sayiMatris[i][j - 1] += sayiMatris[i][j];
								sayiMatris[i][j] = 0;
								flag = true;
								if(sayiMatris[i][j - 1]==2048)
									win();
								
							}
							if (sayiMatris[i][j - 1] == 0 && sayiMatris[i][j] != 0) {
								sayiMatris[i][j - 1] += sayiMatris[i][j];
								sayiMatris[i][j] = 0;
								flag = true;
							}

						}
					}
					intToButton(sayiMatris);
					sayiMatris = buttonToInt(buttons);
					// sayılarda toplama yapıldığı zaman bazı matrisler arasında olmaması gereken
					// boşluklar oluyordu onlar olmasın diye tekrardan for döngüsüne soktum ve o
					// problemi çözdüm
					for (int i = 3; i >= 0; i--) {
						for (int j = 3; j > 0; j--) {
							if (sayiMatris[i][j - 1] == 0 && sayiMatris[i][j] != 0) {
								sayiMatris[i][j - 1] += sayiMatris[i][j];
								sayiMatris[i][j] = 0;
							}

						}
					}
					intToButton(sayiMatris);
					break;
				case D:
				case RIGHT:
					for (int i = 0; i <= 3; i++) {
						for (int j = 0; j < 3; j++) {
							if (sayiMatris[i][j] == sayiMatris[i][j + 1] && sayiMatris[i][j] != 0) {
								sayiMatris[i][j + 1] += sayiMatris[i][j];
								sayiMatris[i][j] = 0;
								flag = true;
								if(sayiMatris[i][j + 1]==2048)
									win();
							}
							if (sayiMatris[i][j + 1] == 0 && sayiMatris[i][j] != 0) {
								sayiMatris[i][j + 1] += sayiMatris[i][j];
								sayiMatris[i][j] = 0;
								flag = true;
							}

						}
					}
					intToButton(sayiMatris);
					sayiMatris = buttonToInt(buttons);
					// sayılarda toplama yapıldığı zaman bazı matrisler arasında olmaması gereken
					// boşluklar oluyordu onlar olmasın diye tekrardan for döngüsüne soktum ve o
					// problemi çözdüm
					for (int i = 0; i <= 3; i++) {
						for (int j = 0; j < 3; j++) {
							if (sayiMatris[i][j + 1] == 0 && sayiMatris[i][j] != 0) {
								sayiMatris[i][j + 1] += sayiMatris[i][j];
								sayiMatris[i][j] = 0;
							}
						}
					}
					intToButton(sayiMatris);
					break;
				default:
					// System.out.println(event.getCode().toString());
					break;
				}
				if (flag == true)
					intToButton(addOne(buttonToInt(buttons)));
			}
		});
		primaryStage.setScene(scene);
		// Buradan Stage Ayarlamalarını Yaptık Boyutunu ve başlığını ekledik
		primaryStage.setTitle("2048 Game");
		primaryStage.setWidth(500);
		primaryStage.setHeight(500);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	//Oyunu kazandığımız zaman bize haber versin
	public void win() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Win");
		alert.setHeaderText("You are winner =)");
		alert.setContentText("Congratulations, you won the game. Keep playing let's break your own record");
		alert.show();
		
	}
	/**
	 * helper method to convert from an array of ints to an array of buttons
	 * 
	 * @param ints an array of ints
	 */
	public void intToButton(int[][] ints) {
		// loops through each of the ints to set the text on buttons
		for (int i = 0; i < ints.length; i++) {
			for (int j = 0; j < ints[i].length; j++) {
				if (ints[i][j] == 0) {
					buttons[i][j].setText("  ");
				} else {
					buttons[i][j].setText(Integer.toString(ints[i][j]));
				}
			}
		}
	}

	/**
	 * helper method to convert an array of buttons to an array of ints
	 * 
	 * @param buttons an array of buttons
	 * @return an array of ints
	 */
	public int[][] buttonToInt(Button[][] buttons) {
		// stores the final array of ints
		int[][] finalArray = new int[buttons.length][buttons[0].length];
		// runs through each button intiliaze ints based off the text
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				if (buttons[i][j].getText().equals("  ")) {
					finalArray[i][j] = 0;
				} else {

					finalArray[i][j] = Integer.parseInt(buttons[i][j].getText());
				}
			}
		}
		return finalArray;
	}

	/**
	 * helper method to generate an extra one
	 * 
	 * @param ints an array of ints
	 * @return an array of ints with a new one tile
	 */
	public static int[][] addOne(int[][] ints) {
		int indOne = (int) (Math.random() * ints.length);
		int indTwo = (int) (Math.random() * ints[0].length);
		// stores whether or not there is an empty space
		boolean isSpace = false;
		// checks to make sure that there is an empty space
		for (int i = 0; i < ints.length; i++) {
			for (int j = 0; j < ints[i].length; j++) {
				if (ints[i][j] == 0) {
					isSpace = true;
				}
			}
		}
		// if there is space on the board, it will keep generating numbers until it hits
		// a blank space
		while (ints[indOne][indTwo] != 0 && isSpace) {
			indOne = (int) (Math.random() * ints.length);
			indTwo = (int) (Math.random() * ints[0].length);
		}

		ints[indOne][indTwo] = 1;
		return ints;
	}

	/**
	 * helper to determine the number of non-zeroes in a method, to test addOne
	 * 
	 * @param ints a multidimensional array
	 * @return the number of non-zeros in the method
	 */
	public static int numberNonZero(int[][] ints) {
		int numNonZero = 0;
		// loops through the array
		for (int i = 0; i < ints.length; i++) {
			for (int j = 0; j < ints[i].length; j++) {
				if (ints[i][j] != 0) {
					numNonZero++;
				}
			}
		}
		return numNonZero;
	}

	/**
	 * The main method to run the program
	 * 
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}