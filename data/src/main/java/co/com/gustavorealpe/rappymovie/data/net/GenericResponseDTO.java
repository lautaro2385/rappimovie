package co.com.gustavorealpe.rappymovie.data.net;

import java.util.List;

import co.com.gustavorealpe.rappymovie.data.net.DatesDTO;
import lombok.Data;

@Data
public class GenericResponseDTO<T> {
    private int page;
    private int total_results;
    private DatesDTO dates;
    private int total_pages;
    private List<T> results;

}
