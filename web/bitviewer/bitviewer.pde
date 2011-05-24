void setup() {
  
  byte[] data = loadBytes(selectInput());
  background(0);
  
  float len = (float) data.length * 8 ;
  
  size((int) sqrt(len), (int) sqrt(len));
  
  loadPixels();
  
  for (int i = 0 ; i < pixels.length ; i++) {
    int byte_idx = i/8;
    
    if (byte_idx < data.length) {
      int bit_index = i % 8;
      
      if ((data[byte_idx] & (1 << bit_index)) > 0) {
        pixels[i] = color(255);
      }
    }
  }
  
  updatePixels();
}


