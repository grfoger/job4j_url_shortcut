package ru.job4j.shortcut.dto;

import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class LinkDTO {

    @NotBlank(message = "url can't be empty")
    @NotNull(message = "need url parameter")
    @URL(protocol = "http", message = "not correct url")
    private String url;
    private String userLogin;
}
