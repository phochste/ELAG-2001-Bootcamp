/*
 * imagediff - calculate the difference between two images
 */
 
//String file1 = "lenin.jpg";
//String file2 = "lenin_2.jpg";

PImage img1;
PImage img2;
PImage img_diff;

void setup() {
   String file1 = selectInput();
   String file2 = selectInput();
  
   img1 = loadImageIO(file1);  
   img2 = loadImageIO(file2);
   img_diff = loadImageIO(file2);
   
   img1.loadPixels();
   img_diff.loadPixels();
   
   float errors = 0.0;
   
   for (int y = 0 ; y < img1.height ; y++) {
     for (int x = 0 ; x < img1.width ; x++) {
       int loc = x + y * img1.width;
       
       color diff = img_diff.pixels[loc] - img1.pixels[loc];
       
       if (diff != 0) {
         img_diff.pixels[loc] =  color(255,0,0);
         
         errors += 1;
       }
       else {
         img_diff.pixels[loc] = color(255);
       }
     }
   }
   
   println("Found: " + errors + " errors = " + (100*errors/(img1.width * img1.height)) + "%");
   
   img_diff.updatePixels();
   
   size(img1.width * 2, img1.height);
}

void draw() {
  image(img2,0,0);
  image(img_diff,img1.width,0);
}

