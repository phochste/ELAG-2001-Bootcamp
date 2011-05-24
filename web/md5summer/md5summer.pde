import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;

String URL = "http://search.ugent.be/~phochste/bodem.tif";
long   count = 0;
long   bytes = 0;
String prev_md5  = "";
long   now = (new Date()).getTime();

void setup() {
   background(0);
   size(600,100); 
}

void draw() {
  background(0);
  
  count++;
    
  String md5 = md5sum(get_data());
  long mb   = bytes/(1024L*1024L);
  long mins = elapsed();
  
  fill(#ffffff);
  text("T:" + count + " MB: " + mb + " MINS: " + mins, 15, 40);
  text("MD5:" + md5, 15,60);
  
  if (prev_md5.length() > 0 && !md5.equals(prev_md5)) {
     fill(#ffcccc);
     text("MD5_prev:" + prev_md5 + " file changed !!!", 15,80);
     noLoop();
  }
  
  prev_md5  = md5;
}

long elapsed() {
  long time = (new Date()).getTime();
  return (time - now)/(1000*60);
}

byte[] get_data() {
     return loadBytes(URL);
}

String md5sum(byte[] data) {
  String hashtext = "";
  
  bytes += (long) data.length;
  
  try {
    MessageDigest m = MessageDigest.getInstance("MD5");
    m.reset();
    m.update(data);
    byte[] digest = m.digest();
    
    BigInteger bigInt = new BigInteger(1,digest);
    hashtext = bigInt.toString(16);
             
    while(hashtext.length() < 32 ){
      hashtext = "0"+hashtext;
    }
  } catch (Exception ex) {}
  
  return hashtext;
}
