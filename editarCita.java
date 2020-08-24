/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subVentanas.ventanasCitas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sistemaclinica.OpcionesEditar;
import subVentanas.Citas;
/**
 *
 * @author jimmy
 */
public class editarCita extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Label lblEditar,lblCedula, lblAviso;
        Button btnEditar,btnRegresar;
        GridPane contenedor ;
        TextField txtCedula;
        Label lblOpciones, lblDato;
        ComboBox cmbOpciones;
        TextField txtDato;
     
       
        
        contenedor = new GridPane();
   
        
        lblEditar = new Label("Editar: ");
        
        lblCedula = new Label("Ingrese cedula de Paciente: ");
        
        lblAviso = new Label("*Si desea modificar la fecha seguir el formato: AA-MM-DD*");
         
        txtCedula = new TextField();
        
        lblOpciones = new Label("Campos que puede editar: ");
        lblDato = new Label("Ingrese el dato a modificar");
        
        txtDato = new TextField();
        
        
        txtDato.setMinWidth(150);
        
        cmbOpciones = new ComboBox();
        
        
        txtCedula.setMinWidth(150);
        

        
        cmbOpciones.getItems().addAll(OpcionesEditar.cedula_Paciente,OpcionesEditar.fecha_Cita,OpcionesEditar.tipo_Cita, OpcionesEditar.diagnostico);
        cmbOpciones.setPromptText("Seleccione que desea editar: ");
        cmbOpciones.setPrefWidth(200);
  
        
        
        
        btnEditar = new Button();
        btnRegresar = new Button();
        
  
        
        btnEditar.setText("Editar registro");
        btnRegresar.setText("Regresar");
        
        contenedor.add(lblCedula,1,0);
        contenedor.add(txtCedula,1,1);
        contenedor.add(lblEditar,0,0);
        contenedor.add(lblOpciones,2,0);
        contenedor.add(lblDato,3,0);
        contenedor.add(txtDato,3,1);
        contenedor.add(lblAviso,3,2);
        
        contenedor.add(cmbOpciones,2,1);
       
        
        contenedor.add(btnEditar,2,3);
        contenedor.add(btnRegresar,2,4);
        
        
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setHgap(10);
        contenedor.setVgap(10);
        contenedor.setHalignment(lblEditar, HPos.CENTER);
        
        
        contenedor.setHalignment(btnEditar, HPos.CENTER);
        contenedor.setHalignment(btnRegresar, HPos.CENTER);
        contenedor.setHalignment(lblCedula, HPos.CENTER);
        contenedor.setHalignment(lblOpciones, HPos.CENTER);
        contenedor.setHalignment(lblDato, HPos.CENTER);
        
        btnEditar.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                Connection cnx = null;
                Statement sta = null;
                ResultSet rs = null;
                try{//si las variables estan mal ingresadas se captura un error y nos da una alert
                    String cedula,Op;
                    cedula = txtCedula.getText();
                    OpcionesEditar opciones;
                    opciones = (OpcionesEditar)cmbOpciones.getValue();
                    String dato;
                    dato = txtDato.getText();
                    
                    if(cedula.equals(cedula.length() != 10 )){
                        try{
                        }
                        catch(Exception e){
                            Alert.AlertType type = Alert.AlertType.ERROR;
                            Alert alert = new Alert(type);

                            alert.getDialogPane().setContentText("No se ha podido realizar la consulta, no ha ingresado ningun dato!");
                            alert.getDialogPane().setHeaderText("Error");
                            alert.showAndWait();

                                if(alert.getResult().getText().equals("Aceptar")){
                                      start(primaryStage);
                    }

                        }
                    }
                    
                   
                   
                    //hago llamada al driver que me permitira conectar con la base de datos
                    Class.forName("com.mysql.jdbc.Driver");//asumo que la base de datos se llama proyectobd1
                    cnx = DriverManager.getConnection("jdbc:mysql://localhost/proyectofinal1?user=root&password=");
                    sta =cnx.createStatement();//añado el dato a la base de datos en la tabla Citas_previas , si el elemento ya esta agregado no agregara el registro
                    //rs= sta.executeQuery("update citasprevias set tipo_Cita = "+dato+" where cedula_Paciente = "+cedula+"");
                    if(opciones.toString().equals("tipo_Cita")){
                                    sta.executeUpdate("update citasprevias set tipo_Cita = '"+dato+"' where cedula_Paciente = "+cedula+"");
                                    
                    }
                            
                       contenedor.add(new Label("Registro modificado con éxito!"),1,6);
                    
                    if(opciones.toString().equals("fecha_Cita")){
                        sta.executeUpdate("update citasprevias set fecha_Cita= '"+dato+"' where cedula_Paciente ="+cedula+"");
                       
                }
                     contenedor.add(new Label("Registro modificado con éxito!"),1,6);
                     
                    if(opciones.toString().equals("cedula_Paciente")){
                        sta.executeUpdate("update citasprevias set cedula_Paciente = '"+dato+"' where cedula_Paciente ="+cedula+"");
                        
                       
                    }
                    contenedor.add(new Label("Registro modificado con éxito!"),1,6);
                    
                    if(opciones.toString().equals("diagnostico")){
                        sta.executeUpdate("update citasprevias set diagnostico = '"+dato+"' where cedula_Paciente = "+cedula+"");
                        
                    }
                        contenedor.add(new Label("Registro modificado con éxito!"),1,6);
                        
                    
                    
                    cnx.close();
                    sta.close();
                    
                }
                catch(Exception e){
                    Alert.AlertType type = Alert.AlertType.ERROR;
                    Alert alert = new Alert(type);

                    alert.getDialogPane().setContentText("No se ha podido modificar el ingreso, no ha seleccionado ninguna opción!");
                    alert.getDialogPane().setHeaderText("Error");
                    alert.showAndWait();

                    if(alert.getResult().getText().equals("Aceptar")){
                        start(primaryStage);
                    }

                }
            }
        });
        
        
        
       
        
        
        btnRegresar.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
                Citas citas = new Citas();
                citas.start(primaryStage);
             
            }
        });
        
        
        
        Scene scene = new Scene(contenedor, 800, 400);
        
        primaryStage.setTitle("Editar Citas");
        primaryStage.setScene(scene);
        primaryStage.show();
    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
