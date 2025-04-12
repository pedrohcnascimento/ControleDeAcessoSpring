package model.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Aluno extends Usuario{

    private String turma;
}
