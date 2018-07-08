package ftc.shift.sample.models;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public final class Food implements Serializable {

    private String id;
    private String name;
    private String category;
}
