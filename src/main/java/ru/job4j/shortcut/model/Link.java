package ru.job4j.shortcut.model;

import lombok.*;

import javax.persistence.*;
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

    private String url;

    private String code;

    @ManyToOne
    private User user;

    private AtomicInteger count = new AtomicInteger(0);

    public int getCount() {
        return count.get();
    }

    public int incrementCount() {
        return count.incrementAndGet();
    }
}
