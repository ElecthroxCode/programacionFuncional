
package modelo;

import java.io.PrintStream;


public class Empleado {
    private String nombre, apellido;
    private double salario;
    private String departamento;

    public Empleado() {
    }

    public Empleado(String nombre, String apellido, double salario, String departamento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.salario = salario;
        this.departamento = departamento;
    }
    
    public String getNombreCompleto(){
        return String.format("%s %s%n", getNombre(), getApellido());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    
    @Override
    public String toString(){
        return String.format("%s %s Salario:%.2f Departamento:%s %n", getNombre(), getApellido(), getSalario(), getDepartamento());
    }
    
}
