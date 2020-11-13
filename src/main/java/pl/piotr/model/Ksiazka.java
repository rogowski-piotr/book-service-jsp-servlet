package pl.piotr.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Ksiazka implements Serializable {

    private int idk;

    private String tytul;

    private Wydawnictwo wydawnictwo;

    private Kategoria kategoria;

}
