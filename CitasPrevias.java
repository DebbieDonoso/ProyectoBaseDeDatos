/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subVentanas.ventanasCitas;

import java.sql.Date;



/**
 *
 * @author Lenovo
 */
public class CitasPrevias{

    private Date fecha_Cita;
    private String tipo_Cita;
    private String diagnostico;

    public CitasPrevias(Date fecha_Cita, String tipo_Cita, String diagnostico) {
        this.fecha_Cita = fecha_Cita;
        this.tipo_Cita = tipo_Cita;
        this.diagnostico = diagnostico;
    }
    
    public Date getFecha_Cita() {
        return fecha_Cita;
    }

    public String getTipo_Cita() {
        return tipo_Cita;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setFecha_Cita(Date fecha_Cita) {
        this.fecha_Cita = fecha_Cita;
    }

    public void setTipo_Cita(String tipo_Cita) {
        this.tipo_Cita = tipo_Cita;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
    
    
    
}
