/*
 * Sharpshooter - a MTTF simulator
 */
int CELL_SIZE   = 20;
int CELL_BORDER =  5;
int CELLS_X     = 50;
int CELLS_Y     = 20;

int[][] grid;
float mttf = 200;
float dfr = 1/(mttf*365);
int stop = 365;
int count = 0;
boolean running = false;

void setup() {
  background(0);  
  smooth();
  size(CELLS_X*(CELL_SIZE+CELL_BORDER)+CELL_BORDER,CELLS_Y*(CELL_SIZE+CELL_BORDER)+CELL_BORDER,P2D);
 
  
  PFont font = loadFont("Consolas-Bold-48.vlw");
  textFont(font);
  
  initGrid();
}

void draw() {
  background(0);
  drawGrid();
  fill(#ffffff);
  text("T:" + count + "/" + stop, 15, 40);
  text("MTTF:" + (int) mttf + " yrs", 15,80);
    
  if (running) {    
    count++;
    
    flipGrid();

    if (count == stop) {
      running = false;
    }
    
  }
}

void keyPressed() {
  if (!running) {
    if (key == 'm') {
       mttf -= 10.0;
       dfr = 1/(mttf*365);
    }
    else if (key == 'M') {
       mttf += 10.0;
       dfr = 1/(mttf*365);
    }
    else if (key == 't') {
       stop -= 365;
    }
    else if (key == 'T') {
       stop += 365;
    }
    else if (key == ' ') {
       count = 0;
       initGrid();
       running = true;
    }
  }
}

void initGrid() {
  grid = new int[CELLS_X][CELLS_Y];
  for (int i = 0 ; i < CELLS_X; i++) {
    for (int j = 0 ; j < CELLS_Y ; j++) {
      grid[i][j] = 0;
    }
  }
}

void flipGrid() {
  for (int i = 0 ; i < CELLS_X; i++) {
    for (int j = 0 ; j < CELLS_Y ; j++) {
      float r = random(1);
      if (r < dfr) {
        grid[i][j] = 1;
      }
    }
  }
}

void drawGrid() {
  for (int i = 0 ; i < CELLS_X; i++) {
    for (int j = 0 ; j < CELLS_Y ; j++) {
      
      fill(grid[i][j] == 0 ? #00cc00 : #cc0000);
      int x = CELL_BORDER + i * (CELL_SIZE + CELL_BORDER);
      int y = CELL_BORDER + j * (CELL_SIZE + CELL_BORDER);
      rect(x,y,CELL_SIZE,CELL_SIZE);
    }
  }
}
