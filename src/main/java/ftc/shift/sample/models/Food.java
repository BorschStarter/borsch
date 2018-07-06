package ftc.shift.sample.models;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Food implements Serializable {

    private String id;
    private String name;
    private String category;
}
