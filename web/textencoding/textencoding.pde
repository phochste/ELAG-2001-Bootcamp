import java.nio.charset.Charset;

String utf8_text = 
    "Au clair de la lune / "+ 
    "Pierrot répondit / " +
    "Je n'ai pas de plume / " +
    "Je suis dans mon lit / " +
    "Va chez la voisine / " +
    "Je crois qu'elle y est / " +
    "Car dans sa cuisine / " + 
    "On bat le briquet";

String[] enc = new String[] {
  "US-ASCII" , "ISO-8859-1" , "UTF-8" , "UTF-16BE" , 
  "UTF-16LE" , "UTF-16" , "Cp037" , "Cp297" , "windows-1252" ,
  "Cp850" , "Cp1047" , "MacRoman"
};

void setup() {
  for (int i = 0 ; i < enc.length ; i++) {
    write_file("data/" + enc[i] + ".txt",utf8_text,enc[i]);
  }
}

void write_file(String filename, String data, String encoding) {
  try {
    saveBytes(filename,data.getBytes(Charset.forName(encoding)));
  }
  catch (Exception e) {
    e.printStackTrace();
  }
}
