/* 

WARNING: The data set I used had a few entries with several missing values, so my MovieDatabase class had some methods 
that created placeholder values for these (0 for a missing int, "" for a missing String, etc.). As such, there may be 
discrepancies between the answers for some of my questions and those by official sources, but these are caused purely
by errors made during the creation of this dataset.

*/

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tester
{
   public static void main(String[] args)
   {
      MovieDatabase db = new MovieDatabase("Movies-Cleaned.csv");
      ArrayList<Movie> movies = db.getMovies();
      
      //Q1: What is the longest running movie series?
      {
         String series = movies.get(1).getSeries();
         int count = 0;
         for(int i = 0; i < movies.size(); i++)
         {
            if(movies.get(i).getOrder() > count)
            {
               count = movies.get(i).getOrder();
               series = movies.get(i).getSeries();
            }
         }
         System.out.println("\nHollywood trivia #1: The longest running movie series is " + series + " with " + count + " movies.");
      }
      //Q2: What is the most common movie genre?
      {
         ArrayList<String> genres = new ArrayList<>();
         for(int i = 0; i<movies.size(); i++){
            String[] newGenres = movies.get(i).getGenres();
            for(int j = 0; j < newGenres.length; j++){
               if(!genres.contains(newGenres[j])) genres.add(newGenres[j]);
            }
         }
         int[] counts = new int[genres.size()];
         for(int i = 0; i < movies.size(); i++)
         {

            for(int j = 0; j < genres.size(); j++){
               List tempGenres = Arrays.asList(movies.get(i).getGenres());
               if(tempGenres.contains(genres.get(j))) 
                  counts[j] += 1;
            }
         }
         
         int maxInd = 0;
         for(int i = 0; i < counts.length; i++)
         {
            if(counts[i] > counts[maxInd])
            {
               maxInd = i;
            }
         }
         System.out.println("\nHollywood trivia #2: The most common movie genre is " + genres.get(maxInd) + " with " + counts[maxInd] + " movies.");
      }
      //Q3: Who has directed the most movies? (PRECONDITION: at least one director has directed more than one movie)
      {
         ArrayList<String> directors = new ArrayList<>();
         for(int i = 0; i<movies.size(); i++){
            String[] newDirectors = movies.get(i).getDirectors();
            for(int j = 0; j < newDirectors.length; j++){
               if(!directors.contains(newDirectors[j])) directors.add(newDirectors[j]);
            }
         }
         int[] counts = new int[directors.size()];
         for(int i = 0; i < counts.length; i++)
         {
            for(int j = 0; j < directors.size(); j++){
               List tempDirectors = Arrays.asList(movies.get(i).getDirectors());
               if(tempDirectors.contains(directors.get(j))) 
                  counts[j] += 1;
            }
         }
         int max = 0;
         String directorTracker = directors.get(0);
         for(int i = 0; i < counts.length; i++)
         {
            if(counts[i] > max && (directors.get(i) != ""))
            {
               max = counts[i];
               directorTracker = directors.get(i);
            }
         }
         System.out.println("\nHollywood trivia #3: " + directorTracker + " has directed the highest total of " + max + " movies.");
      }
      //Q4: USER INPUT: Which movies were released on [date]?
      {
         ArrayList<String> dates = new ArrayList<>();
         for(int i = 0; i < movies.size(); i++){
            String date = movies.get(i).getDate();
            dates.add(date);
         }
         
         Scanner s = new Scanner(System.in);
         System.out.println("\nTry your luck! Enter a date you think a movie might have been released on since 1940 (dd-mm-yyyy): ");
         String answer = s.nextLine();
         
         while(true)
         {
            if(answer.length() != 10 || answer.charAt(2) != '-' || answer.charAt(5) != '-')
            {
               System.out.println("Hmmm... that doesn't seem to be a valid date. Try again in the format dd-mm-yyyy (e.g. 24-04-2037): ");
               answer = s.nextLine();
            }
            else if(!dates.contains(answer))
            {
               System.out.println("As far as I know, no movie was released on that day. Try again!");
               answer = s.nextLine();
            }
            else break;
         }
         ArrayList<Integer> indices = new ArrayList<>();
         for(int i = 0; i < dates.size(); i++)
         {
            if(dates.get(i).equals(answer)) indices.add(i);
         }
         System.out.println(
            "Congratulations! " + indices.size() + (indices.size() == 1
            ? " movie was released on that day! Here it is:\n"
            : " movies were released on that day! Here they are:\n")
         );
         for(int value: indices)
         {
            Movie m = movies.get(value);
            System.out.println(
               "* " + m.getTitle() + 
               ". This was a " + (int) m.getRuntime() + "-minute " +
               m.getGenres()[(int) (Math.random()*m.getGenres().length)] + " movie" +
               " directed by " + (m.getDirectors().length == 1 ? m.getDirectors()[0] : m.getDirectors()[0] + " and " + m.getDirectors()[1]) + 
               ", #" + m.getOrder() + " in the " + m.getSeries() + " series" +
               ", rated " + m.getRating() + "/10 on IMDb. "
            );
         }
      }
   }
}