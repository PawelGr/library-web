package com.pg.Lesson007.model;

import com.pg.Lesson007.controller.CourseController;
import com.pg.Lesson007.service.CourseService;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@Entity
public class Course {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull(message = "Title can't be null.")
    @NotEmpty(message = "Title can't be empty.")
    @Pattern(regexp = "[a-zA-Z.,\\s]+", message = "Invalid value.")
    private String name;

//    @ManyToMany
//    private List<User> user;

}
