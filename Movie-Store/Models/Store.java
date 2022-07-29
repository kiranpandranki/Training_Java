package Models;

import java.util.ArrayList;

public class Store {
    ArrayList<Movie> movies;

    public Store(){
        this.movies = new ArrayList<Movie>();
    }

    public Movie getMovie(int index){
        return new Movie(movies.get(index));
    }

    public Movie getMovie(String name){
        for(int i=0;i<movies.size();i++){
            if(this.movies.get(i).getName().equalsIgnoreCase(name)){
                return new Movie(this.movies.get(i));
            }
        }

        return null;
    }

    public void setMovie(int index,Movie movie){
        this.movies.set(index, new Movie(movie));
    }

    public void addMovie(Movie movie){
        this.movies.add(new Movie(movie));
    }

    public void action(String name,String action){
        if(movies.isEmpty()){
            throw new IllegalStateException("Store is empty");
        }
        if(!(action.equalsIgnoreCase("sell")||action.equalsIgnoreCase("rent")||action.equalsIgnoreCase("return"))){
            throw new IllegalArgumentException("Action must be sell, rent or return");
        }
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("name cannot be null or blank");
        }
        for(int i=0;i<movies.size();i++){
            if(movies.get(i).getName().equalsIgnoreCase(name)){
                switch(action){
                    case "sell":
                        if(!this.movies.get(i).isAvailable()){
                            throw new IllegalStateException("Movie not available");
                        }
                        this.movies.remove(i);
                        break;
                    case "rent":
                        this.movies.get(i).setAvailable(false);
                        break;
                    case "return":
                        this.movies.get(i).setAvailable(true);
                        break;
                }
            }
        }
    }

    public String toString(){
        String temp="";
        for(int i=0;i<movies.size();i++){
            temp += this.movies.get(i).toString();
            temp += "\n\n";
        }

        return temp;
    }
}
