/*
 Clase de alumnos inscritos
 */
package deportesstucom;

import java.util.Objects;

/**
 *
 * @author usuario
 */
public class AlumnoInscrito {
    
        private String nombre;
        private String apellidos;
        private int edad;
        private String curso;
        private char sexo;
        private String deporte;

    public AlumnoInscrito (String nombre, String apellidos, String curso, char sexo, int edad, String deporte) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.curso = curso;
        this.sexo = sexo;
        this.edad = edad;    
        this.deporte = deporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.nombre);
        hash = 79 * hash + Objects.hashCode(this.apellidos);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AlumnoInscrito other = (AlumnoInscrito) obj;
        if (nombre.equalsIgnoreCase(other.getNombre())) {
            return false;
        }
        return apellidos.equalsIgnoreCase(other.getApellidos());
    }

    @Override
    public String toString() {
        return "AlumnoInscrito{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", edad=" + edad + ", curso=" + curso + ", sexo=" + sexo + ", deporte=" + deporte + '}';
    }
    
    
    
}
