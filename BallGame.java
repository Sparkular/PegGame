
import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BallGame extends Application {
    private static final int GRID_SIZE = 4; // Size of the grid
    private static final int BALL_RADIUS = 40; // Radius of the ball
    private static final int BUTTON_SIZE_LONG = 20; // Long dimension of the button
    private static final int BUTTON_SIZE_SHORT = 80; // Short dimension of the button

class GamePane extends GridPane {
    private Canvas canvas; // Canvas to draw the ball
    private Button topButton;
    private Button leftButton;
    private Button rightButton;
    private Button bottomButton;
    
    private boolean ballVisible; // Boolean for whether the ball is visible
    private boolean topButtonVisible; // Boolean for top button visibility
    private boolean leftButtonVisible; // Boolean for left button visibility
    private boolean rightButtonVisible; // Boolean for right button visibility
    private boolean bottomButtonVisible; // Boolean for bottom button visibility

    public GamePane() 
    {
        setHgap(2); // Horizontal gap between grid cells
        setVgap(2); // Vertical gap between grid cells
        setPadding(new Insets(10));
        
        // Create buttons
        topButton = createButton("");
        leftButton = createButton("");
        rightButton = createButton("");
        bottomButton = createButton("");

        // Set size of left and right buttons
        leftButton.setMinSize(BUTTON_SIZE_LONG, BUTTON_SIZE_SHORT);
        leftButton.setMaxSize(BUTTON_SIZE_LONG, BUTTON_SIZE_SHORT);
        
        rightButton.setMinSize(BUTTON_SIZE_LONG, BUTTON_SIZE_SHORT);
        rightButton.setMaxSize(BUTTON_SIZE_LONG, BUTTON_SIZE_SHORT);
        
        topButton.setMinSize(BUTTON_SIZE_SHORT, BUTTON_SIZE_LONG);
        topButton.setMaxSize(BUTTON_SIZE_SHORT, BUTTON_SIZE_LONG);
        
        bottomButton.setMinSize(BUTTON_SIZE_SHORT, BUTTON_SIZE_LONG);
        bottomButton.setMaxSize(BUTTON_SIZE_SHORT, BUTTON_SIZE_LONG);

        // Create canvas
        canvas = new Canvas(BALL_RADIUS * 2, BALL_RADIUS * 2);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK); // Set the color of the ball
        gc.fillOval(0, 0, BALL_RADIUS * 2, BALL_RADIUS * 2); // Draw the ball on the canvas

        // Add buttons and canvas to the grid
        add(topButton, 1, 0);
        add(leftButton, 0, 1);
        add(rightButton, 2, 1);
        add(bottomButton, 1, 2);
        add(canvas, 1, 1);
        
        
    }

    // Helper method to create a button
    private Button createButton(String text) 
    {
        Button button = new Button(text);
        button.setMinSize(BUTTON_SIZE_LONG, BUTTON_SIZE_SHORT); // Update size for left and right buttons
        button.setMaxSize(BUTTON_SIZE_LONG, BUTTON_SIZE_SHORT); // Update size for left and right buttons
        return button;
    }
}


    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setPrefSize(600, 600);

        // Add label to the top
        Label label = new Label("Ball Game");
        label.setAlignment(Pos.CENTER);
        root.setTop(label);

        // Add GamePanes to the center
        GridPane center = new GridPane();
        center.setAlignment(Pos.CENTER);
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                GamePane gamePane = new GamePane();
                center.add(gamePane, i, j);
            }
        }
        root.setCenter(center);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
