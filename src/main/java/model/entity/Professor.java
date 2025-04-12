package model.entity;

import jakarta.persistence.Entity;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Professor extends Usuario {

    private List<String> turma;
    private List<UnidadeCurricular> unidadesCurriculares;
}
