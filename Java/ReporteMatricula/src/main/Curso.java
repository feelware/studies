package main;

import java.util.Vector;

public class Curso {
    private String escuela;
    private String ciclo;
    private String codigoCurso;
    private String asignatura;
    private String creditos;
    private String seccion;
    private String docente;

    public Curso(String escuela, String ciclo, Vector<String> cursoInfo) {
        this.escuela = escuela;
        this.ciclo = ciclo;
        codigoCurso = cursoInfo.get(0);
        asignatura = cursoInfo.get(1);
        creditos = cursoInfo.get(2);
        seccion = cursoInfo.get(3);
        docente = cursoInfo.get(4);
    }

    // #region Getters
    public String getEscuela() {
        return escuela;
    }

    public String getCiclo() {
        return ciclo;
    }

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public String getCreditos() {
        return creditos;
    }

    public String getSeccion() {
        return seccion;
    }

    public String getDocente() {
        return docente;
    }
    // #endregion

}
