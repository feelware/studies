package main;

public class App {
    public static void main(String[] args) throws Exception {
        Utilities.cls();
        Alumno alumno = new Alumno();
        Matricula.matricular(alumno);
    }
}