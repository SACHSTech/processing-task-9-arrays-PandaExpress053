import java.util.Arrays;

import processing.core.PApplet;

public class Sketch extends PApplet {
	/**
   * 
   * Description: This class creates a sketch that displays snowflakes falling from the top of the screen.
   * The player can move left and right to avoid the snowflakes. Each snowball takes one life away from the characters initial 3 lives
   * The player can also change the speed of the snowflakes by pressing
   * the up and down arrow keys. The player can hide snowflakes by clicking on them with the mouse.
   * @author: E.Fung
   */
	// Initializing variables
  int intObjectNum = 40;
  float[] fltCircleY = new float[intObjectNum];
  float[] fltCircleX = new float[intObjectNum];
  boolean[] ballHideStatus = new boolean[intObjectNum];
  float fltSpeed = 1;

  boolean isUpPressed = false;
  boolean isDownPressed = false;

  boolean isWPressed = false;
  boolean isAPressed = false;
  boolean isSPressed = false;
  boolean isDPressed = false;

  float fltCharacterX = 200;
  float fltCharacterY = 300;

  int intSnowballDiameter = 30;
  int intCharacterDiameter = 26;

  int intLives = 3;

  boolean isAlive = true;

  boolean isDrag = false;
  boolean isPressed = false;


  public void settings() {
	// put your size call here
    size(400, 400);
  }
  

  public void setup() {
    // Preparing arrays with necessary initial values
    for (int i = 0; i < fltCircleY.length; i++) {
      fltCircleY[i] = -random(height + 100);
    }
    for (int i = 0; i < fltCircleX.length; i++) {
      fltCircleX[i] = random(width);
    }
    for (int i = 0; i < ballHideStatus.length; i++) {
      ballHideStatus[i] = false;
    }
  }
  
  public void draw() {
    background(50);

    // Displaying snow
    for (int i = 0; i < fltCircleY.length; i++) {
      fill(255);
      
      if (isAlive && !ballHideStatus[i]){
        ellipse(fltCircleX[i], fltCircleY[i], intSnowballDiameter, intSnowballDiameter);
      }
      
      fltCircleY[i] += fltSpeed;
  
      // Checks if snow has reached the boundaries of the screen
      if (fltCircleY[i] > height + (intSnowballDiameter / 2)) {
        fltCircleY[i] = -random(height);
        fltCircleX[i] = random(width);
      }

      // Checks if character is colliding with snowball
      if (dist(fltCircleX[i], fltCircleY[i], fltCharacterX, fltCharacterY) < (intCharacterDiameter / 2) + (intSnowballDiameter / 2) && !ballHideStatus[i]){
        fltCircleY[i] = -random(height);
        fltCircleX[i] = random(width);
        intLives--;
      }

      // Checks if the mouse has been clicked over the snowball
      if (dist(mouseX, mouseY, fltCircleX[i], fltCircleY[i]) < intSnowballDiameter / 2 && !isDrag && isPressed){
        // removes snowball from screen
        ballHideStatus[i] = true;
        isPressed = false;
      }
      
    }

    // Calculating speed of snowfall from user input + max/min speed boundaries
    if (isUpPressed && fltSpeed >= 0.5f){
      fltSpeed -= 0.1f;
    }
    if (isDownPressed && fltSpeed <= 2f){
      fltSpeed += 0.1f;
    }

    // Calculating character movement + boundaries
    if (isWPressed && fltCharacterY > intCharacterDiameter / 2){
      fltCharacterY -= 2.25f;
    }
    if (isAPressed && fltCharacterX > intCharacterDiameter / 2){
      fltCharacterX -= 2.25f;
    }
    if (isSPressed && fltCharacterY < height - (intCharacterDiameter / 2)){
      fltCharacterY += 2.25f;
    }
    if (isDPressed && fltCharacterX < width - (intCharacterDiameter / 2)){
      fltCharacterX += 2.25f;
    }

    // Displaying Character
    fill(0, 0, 200);
    if (isAlive){
      ellipse(fltCharacterX, fltCharacterY, intCharacterDiameter, intCharacterDiameter);
    }
    

    // Displaying lives
    for (int i = 0; i < intLives; i++){
      fill(255, 0, 0);
      if (isAlive){
        rect(i * 35 + 20, 20, 20, 20);
      }
    }

    // Checks if character is still alive; will turn screen white if no more lives left
    if (intLives <= 0){
      // tells the rest of the code to stop displaying 
      isAlive = false;
      // turns background white
      background(255);
    }

  }
  
  // define other methods down here.
  public void keyReleased() {
    // toggles respective variables to know when key is released

    // for snowfall speed
    if (keyCode == UP) {
      isUpPressed = false;
    }
    if (keyCode == DOWN) {
      isDownPressed = false;
    }

    // for character movement
    if (key == 'w') {
      isWPressed = false;
    }
    if (key == 'a') {
      isAPressed = false;
    }
    if (key == 's') {
      isSPressed = false;
    }
    if (key == 'd') {
      isDPressed = false;
    }
  }

  
  public void keyPressed(){
    // toggles respective variables to know when key is pressed

    // for snowfall speed
    if (keyCode == UP) {
      isUpPressed = true;
    }
    if (keyCode == DOWN) {
      isDownPressed = true;
    }

    // for character movement
    if (key == 'w') {
      isWPressed = true;
    }
    if (key == 'a') {
      isAPressed = true;
    }
    if (key == 's') {
      isSPressed = true;
    }
    if (key == 'd') {
      isDPressed = true;
    }
  }
  public void mouseDragged(){
    // check if mouse is being dragged
    isDrag = true;
  }
  public void mouseReleased(){
    // mouse is not being dragged or pressed
    isDrag = false;
    isPressed = false;
  }
  public void mousePressed(){
    // check if mouse has been pressed
    isPressed = true;
  }
  
}