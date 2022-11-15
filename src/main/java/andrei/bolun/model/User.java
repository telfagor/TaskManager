package andrei.bolun.model;

import java.io.Serializable;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class User implements Serializable {
    private String firstName;
    private String lastName;
    private String userName;
}
