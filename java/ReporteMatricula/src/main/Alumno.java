package main;

import java.io.InputStream;
import java.util.Vector;

public class Alumno {
    private String codigo;
    private String nombre;
    private String facultad;
    private String escuela;
    private String especialidad;
    private String plan;
    private String periodo;
    private Vector<Curso> cursosMatriculados = new Vector<Curso>();
    private float creditajeMat;
    private int asignaturasMat;

    public Alumno() {
        facultad = "20 - Ingeniería De Sistemas e Informática";
        especialidad = "0 - Estudios Generales";
        plan = "Plan De Estudios 2018";
        periodo = "2023-2";
        asignarInfo();
    }

    private void asignarInfo() {
        Vector<String> codigoInfo = scanCodigoInfo(true);
        codigo = codigoInfo.get(0);
        nombre = codigoInfo.get(1);
        escuela = codigoInfo.get(2);
        System.out.println(""); mostrarInfo();
        if (!Utilities.continuar("¿Es esta información correcta? (S/N)")) { 
            Utilities.cls();
            asignarInfo(); }
    }

    private Vector<String> scanCodigoInfo(boolean firstTime) {
        if (!firstTime) { System.out.print("\nCódigo de matrícula no encontrado. "); }
        Vector<String> codigoInfo = new Vector<String>();
        codigoInfo.add(Utilities.validarEntrada("Ingrese su código de matrícula.", "[0-9]{8}"));
        try {
            codigoInfo.addAll(Utilities.buscarArchivo(
                getAlumnoCsv(), 
                new int[]{0},
                new String[]{codigoInfo.get(0)}, 
                ",(?! )", 
                new int[]{1, 2}
            ).get(0));
        } catch (Exception ArrayIndexOutOfBoundsException) {
            codigoInfo = scanCodigoInfo(false);
        }
        return codigoInfo;
    }    

    public void mostrarInfo() {
        System.out.println("Apellidos y Nombres:\t" + getNombre().replaceAll("\"", ""));
        System.out.println("Facultad:\t\t" + getFacultad());
        System.out.print("Escuela:\t\t");
        if (getEscuela().equals("sist"))
            { System.out.println("1 - E.P. de Ingeniería De Sistemas"); } 
        else
            { System.out.println("2 - E.P. de Ingeniería de Software"); }
        System.out.println("Especialidad:\t\t" + getEspecialidad());
        System.out.println("Plan:\t\t\t" + getPlan());
        System.out.println("Periodo:\t\t" + getPeriodo());
        System.out.println("");
    }

    // #region Getters, etc

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFacultad() {
        return facultad;
    }

    public String getEscuela() {
        return escuela;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getPlan() {
        return plan;
    }

    public String getPeriodo() {
        return periodo;
    }

    public Vector<Curso> getCursosMatriculados() {
        return cursosMatriculados;
    }
    
    public int getAsignaturasMat() {
        return asignaturasMat;
    }
    
    public float getCreditajeMat() {
        return creditajeMat;
    }

    private InputStream getAlumnoCsv() {
        return Alumno.class.getClassLoader().getResourceAsStream("alumnos.csv");
    }

    public void addCursosMatriculados(Curso curso) {
        cursosMatriculados.add(curso);
    }

    public void calcAsigCredit() {
        asignaturasMat = 0; creditajeMat = 0;
        for (Curso curso : cursosMatriculados) {
            asignaturasMat++;
            creditajeMat += Float.parseFloat(curso.getCreditos());
        }
    }

    //#endregion
}