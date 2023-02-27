// A database class to instantiate and manage an arraylist of resource references
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ArrayList;

public class MovieDatabase {

   private ArrayList<Movie> movies;
   private String dataFileName;
   
   public MovieDatabase(String dataFileName){
      this.dataFileName = dataFileName;
      ArrayList<String> info = readFromFile(dataFileName);
      movies = new ArrayList<>();
      for(int i = 1; i < info.size(); i++){
         String[] basic = splitOnNonQuotedCommas(info.get(i));
         String t = basic[1];
         String dt = basic[2];
         int y = (int) checkDouble(basic[3]);
         double rat = checkDouble(basic[6]);
         double run = checkDouble(basic[7]);
         //CHALLENGE
         String[] g = basic[8].split(", ").length == 1 ? basic[8].split("donotsplit") : basic[8].substring(1,basic[8].length()-1).split(", ");
         String[] d = basic[10].split(", ").length == 1 ? basic[10].split("donotsplit") : basic[10].substring(1,basic[10].length()-1).split(", ");
         
         String s = basic[11];
         int o = checkInt(basic[12]);
         Movie m = new Movie(t, dt, y, rat, run, g, d, s, o);
         movies.add(m);
      }
   }
   
   public ArrayList<Movie> getMovies() {
      return movies;
   }
   private String[] splitOnNonQuotedCommas(String hasCommasWithinQuotes) {
      return hasCommasWithinQuotes.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
   }
   
   private ArrayList<String> readFromFile(String fileName) {
      ArrayList<String> data = null;
      try {
          data = (ArrayList<String>) Files.readAllLines(Paths.get(fileName));
      } catch (IOException e){
          System.out.println("The file " + fileName + " could not be opened.");
          e.printStackTrace();
      }
      return data;
   }
   // SEE TESTER CLASS FOR EXPLANATION
   public int checkInt(String s)
   {
      if(s == "") return 0;
      else return Integer.parseInt(s);
   }
   public double checkDouble(String s)
   {
      if(s == "") return 0.0;
      else return Double.parseDouble(s);
   }
   
   /*public static String[] split(String s, String r){
      int counter = 0;
      ArrayList<String> al = new ArrayList<>();
      for(int i = 0; i < s.length()-r.length()+1; i++){
         //String sub = "";
         if(s.substring(i, i+r.length()).equals(r)){
            counter++;
         }
         else{
            al.add(s.substring(i, i+1));
         }
      }
      String[] a = new String[al.size()];
      for(int i = 0; i < a.length; i++){
         a[i] = al.get(i);
      }
      return a;
   }*/

}