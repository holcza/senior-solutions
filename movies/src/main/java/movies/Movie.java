package movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    private long id;

    private String title;

    private double length;

    private List<Integer> ratings = new ArrayList<>();

    private double averageRatings;

    public Movie(long id, String title, double length) {
        this.id = id;
        this.title = title;
        this.length = length;
    }

    public void addRating(int rating){
        ratings.add(rating);

        //averageRatings = ratings.stream().mapToInt(r->r).sum()*1.0
         //       /ratings.stream().count();
        averageRatings = ratings.stream().collect(Collectors.summarizingInt(Integer::intValue)).getAverage();

    }
}
