package pollub.ism.lab04;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String currentSymbol;
    boolean isFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button;
        currentSymbol="X";
        isFinished=false;
        int[] buttonIDS = {R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9};
        for (int i = 0; i < buttonIDS.length; i++) {
            button = (Button) findViewById(buttonIDS[i]);
            button.setBackgroundColor(Color.WHITE);
        }
    }

    public void buttonPressed(View view) {
        TextView textView = (TextView) findViewById(view.getId());
        if (textView.getText() == "") {
            if(!isFinished){
                textView.setText(currentSymbol);
                checkIfGameIsFinished(view);
                switchCurrentSymbol();
            }
        }
    }

    private void switchCurrentSymbol() {
        if (currentSymbol == "X") {
            currentSymbol = "O";
        } else if (currentSymbol == "O") {
            currentSymbol = "X";
        }
    }

    public void resetGame(View view) {
        TextView textView;
        Button button;
        int[] buttonIDS = {R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9};
        isFinished=false;
        for (int i = 0; i < buttonIDS.length; i++) {
            button = (Button) findViewById(buttonIDS[i]);
            button.setBackgroundColor(Color.WHITE);
            textView = (TextView) findViewById(buttonIDS[i]);
            textView.setText("");
        }
        Button buttonReset = (Button)findViewById(R.id.button10);
        buttonReset.setText("RESET");
    }

    private void checkIfGameIsFinished(View view) {
        int[] buttonIDS = {R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9};
        checkWinnerInColumn(buttonIDS);
        checkWinnerInRow(buttonIDS);
        checkWinnerInCrossLeft(buttonIDS);
        checkWinnerInCrossRight(buttonIDS);
        if(checkIfGameDrawn()){
            endGameInDraw();
        }
    }
    private void checkWinnerInColumn(int[] buttonIDS){
        for (int i = 0; i < 3; i++) {
            Button button1 = (Button) findViewById(buttonIDS[i]);
            Button button2 = (Button) findViewById(buttonIDS[i + 3]);
            Button button3 = (Button) findViewById(buttonIDS[i + 6]);
            if (button1.getText() == button2.getText() && button1.getText() == button3.getText() && button1.getText() != "") {
                endGame(button1,button2,button3);
            }
        }
    }

    private void checkWinnerInRow(int[] buttonIDS){
        for (int i = 0; i < 3; i++) {
            Button button1 = (Button) findViewById(buttonIDS[i*3]);
            Button button2 = (Button) findViewById(buttonIDS[i*3 + 1]);
            Button button3 = (Button) findViewById(buttonIDS[i*3 + 2]);
            if (button1.getText() == button2.getText() && button1.getText() == button3.getText() && button1.getText() != "") {
                endGame(button1,button2,button3);
            }
        }
    }

    private void checkWinnerInCrossLeft(int[] buttonIDS){
        Button button1 = (Button) findViewById(buttonIDS[0]);
        Button button2 = (Button) findViewById(buttonIDS[4]);
        Button button3 = (Button) findViewById(buttonIDS[8]);
        if (button1.getText() == button2.getText() && button1.getText() == button3.getText() && button1.getText() != "") {
            endGame(button1,button2,button3);
        }
    }

    private void checkWinnerInCrossRight(int[] buttonIDS){
        Button button1 = (Button) findViewById(buttonIDS[2]);
        Button button2 = (Button) findViewById(buttonIDS[4]);
        Button button3 = (Button) findViewById(buttonIDS[6]);
        if (button1.getText() == button2.getText() && button1.getText() == button3.getText() && button1.getText() != "") {
            endGame(button1,button2,button3);
        }
    }

    private boolean checkIfGameDrawn(){
        int[] buttonIDS = {R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9};
        for(int i=0;i<buttonIDS.length;i++){
            Button button = (Button)findViewById(buttonIDS[i]);
            if(button.getText()==""){
                return false;
            }
        }
        return true;
    }

    private void endGame(Button button1,Button button2,Button button3){
        isFinished=true;
        button1.setBackgroundColor(Color.GREEN);
        button2.setBackgroundColor(Color.GREEN);
        button3.setBackgroundColor(Color.GREEN);
        Toast.makeText(this,"Winner is "+currentSymbol, Toast.LENGTH_LONG).show();
        Button buttonReset = (Button)findViewById(R.id.button10);
        buttonReset.setText("PLAY AGAIN");

    }

    private void endGameInDraw(){
        isFinished=true;
        Button button;
        int[] buttonIDS = {R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9};
        for (int i = 0; i < buttonIDS.length; i++) {
            button = (Button) findViewById(buttonIDS[i]);
            button.setBackgroundColor(Color.RED);
        }
        Toast.makeText(this,"Game Drawn", Toast.LENGTH_LONG).show();
        Button buttonReset = (Button)findViewById(R.id.button10);
        buttonReset.setText("PLAY AGAIN");
    }
}