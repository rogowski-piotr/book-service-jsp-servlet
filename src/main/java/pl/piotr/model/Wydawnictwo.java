package pl.piotr.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Wydawnictwo implements Serializable {

    private int idw;

    private String nazwa;

    private String adres;

}
