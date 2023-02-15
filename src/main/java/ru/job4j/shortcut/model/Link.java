package ru.job4j.shortcut.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "links")
public class Link {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "url can't be empty")
    @NotNull(message = "need url parameter")
    private String url;
    private String code;
    @ManyToOne
    private User user;
    private int count;
}
