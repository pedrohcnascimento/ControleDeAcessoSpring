package Entity;

import jakarta.persistence.Entity;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Coordenador extends Usuario{

    private List<Professor> listaDeProfessores;
}
