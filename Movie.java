public class Movie
{

   /*
    * @param title
    * @param releaseDate
    * @param year
    * @param rating
    * @param runtime
    * @param genres
    * @param series
    * @param order
    */
   
   private String title;
   private String date;
   private int year;
   private double rating;
   private double runtime;
   private String[] genres;
   private String[] directors;
   private String series;
   private int order;
   
   
   public Movie(String title, String date, int year, double rating,  double runtime,  String[] genres, String[] directors, String series, int order)
   {
      this.title = title;
      this.date = date;
      this.year = year;
      this.rating = rating;
      this.runtime = runtime;
      this.genres = genres;
      this.directors = directors;
      this.series = series;
      this.order = order;
   }
   
	public String toString() {
		return title;
	}
	public String getTitle() {
		return title;
	}
	public String getDate() {
		return date;
	}
	public double getYear() {
		return year;
	}
   public double getRating() {
      return rating;
   }
   public double getRuntime() {
      return runtime;
   }
   public String[] getGenres() {
      return genres;
   }
   public String[] getDirectors() {
      return directors;
   }
   public String getSeries() {
      return series;
   }
   public int getOrder() {
      return order;
   }

}