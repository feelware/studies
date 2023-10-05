package main;

import java.util.Vector;
import java.io.InputStream;
import java.time.LocalDate;

public class Matricula {
    private static String escuela;
    private static String ciclo;
    private static Vector<Vector<String>> cursos;
    private static String fechaHora;

    public static void matricular(Alumno alumno) {
        Utilities.cls();
        escuela = alumno.getEscuela();
        ciclo = Utilities.validarEntrada("Ingrese el ciclo a matricular.", "^\\s*([1-9]|10)\\s*$");
        cursos = definirCursos(escuela, ciclo);
        Vector<Integer> secciones = contarSecciones(cursos);
        
        int posIni = 0;
        for (int i = 0; i < secciones.size(); i++) {
            Utilities.cls();

            int cantidadSecciones = secciones.get(i);
            System.out.println("Secciones disponibles para el curso " + cursos.get(posIni).get(1).replaceAll("\"", "") + "\n");
            for (int j = 0; j < cantidadSecciones; j++) {
                System.out.println((j + 1) + ". " + cursos.get(posIni + j).get(4).replaceAll("\"", ""));
            }
            System.out.println("");
            Curso nuevoCurso = elegirSeccion(posIni, cantidadSecciones);
            alumno.addCursosMatriculados(nuevoCurso);

            posIni += cantidadSecciones;
        }
        alumno.calcAsigCredit();
        fechaHora = LocalDate.now().toString() + " " + java.time.LocalTime.now().toString().substring(0, 8);
        mostrarMatricula(alumno);
    }

    private static Vector<Vector<String>> definirCursos(String escuela, String ciclo) {
        return Utilities.buscarArchivo(
            getCursosCsv(), 
            new int[]{0, 1},
            new String[]{escuela, "(^|\\D)" + ciclo + "(\\D|$)"}, 
            ",(?! )",
            new int[]{2, 3, 4, 5, 6}
        );
    }

    private static Vector<Integer> contarSecciones(Vector<Vector<String>> cursos) {
        Vector<Integer> secciones = new Vector<Integer>();
        for(int i = 1; i < cursos.size(); i++) {
            if (Integer.parseInt(cursos.get(i).get(3)) == 1) { 
                secciones.add(Integer.parseInt(cursos.get(i-1).get(3))); 
            }
        }
        secciones.add(Integer.parseInt(cursos.get(cursos.size() - 1).get(3))); 
        return secciones;
    }

    private static Curso elegirSeccion(int posIni, int cantidadSecciones) {
        String searchPattern = "^\\s*[1-" + cantidadSecciones + "]\\s*$";
        String numeroSeccion = Utilities.validarEntrada(
            "Indicar número de sección en la que desea matricularse.", 
            searchPattern
        );
        Curso nuevoCurso = new Curso(
            escuela, ciclo, cursos.get(posIni + Integer.parseInt(numeroSeccion) - 1)
        );
        return nuevoCurso;
    }

    private static void mostrarMatricula(Alumno alumno) {
        Utilities.cls();
        System.out.println("REPORTE DE MATRICULA\n");

        System.out.println("Datos del estudiante\n");
        alumno.mostrarInfo();

        System.out.println("Asignaturas matriculadas\n");

        System.out.println(
            String.format("%-8s",  "Ciclo") + 
            String.format("%-12s", "Codigo") + 
            String.format("%-55s", "Asignatura") + 
            String.format("%-9s",  "Cred.") + 
            String.format("%-9s",  "Codigo") + 
            String.format("%-20s", "Docente"));
        for (int i = 0; i < alumno.getCursosMatriculados().size(); i++) {
            Curso curso = alumno.getCursosMatriculados().get(i);
            System.out.println(
                String.format("%-8s",  curso.getCiclo()) + 
                String.format("%-12s", curso.getCodigoCurso()) +
                String.format("%-55s", curso.getAsignatura().replaceAll("\"", "")) +
                String.format("%-9s",  curso.getCreditos()) +
                String.format("%-9s",  curso.getSeccion()) +
                String.format("%-20s", curso.getDocente().replaceAll("\"", ""))
            );
        }
        System.out.println("\nSemestre " + alumno.getPeriodo());
        System.out.println("Fecha y hora: " + fechaHora);
        System.out.println("\nCreditaje matriculado: " + alumno.getCreditajeMat());
        System.out.println("Asignaturas matriculadas: " + alumno.getAsignaturasMat());
    }

    private static InputStream getCursosCsv() {
        return Matricula.class.getClassLoader().getResourceAsStream("cursos.csv");
    }
}
