package relatorios;

import usuarios.*;

import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private List<Paciente>   pacientes   = new ArrayList<>();
    private List<Enfermeiro> enfermeiros = new ArrayList<>();
    private List<Medico>     medicos     = new ArrayList<>();


    public void addPacientes(Paciente paciente) {
        this.pacientes.add(paciente);
    }

    public void addEnfermeiro(Enfermeiro enfermeiro) {
        this.enfermeiros.add(enfermeiro);
    }

    public void addMedicos(Medico medico) {
        this.medicos.add(medico);
    }
}
