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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import subVentanas.Citas;

/**
 *
 * @author jimmy
 */
public class eliminarCita extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       Label lblTexto;
       TextField txtConsulta;
       Button btnEliminar,btnRegresar;
       GridPane contenedor ;

       contenedor = new GridPane();

       lblTexto = new Label("Ingrese el número de cédula del paciente para eliminar su cita");
       txtConsulta = new TextField();
       
       btnEliminar= new Button();
        btnRegresar = new Button();

        txtConsulta.setMinWidth(150);

        btnEliminar.setText("Eliminar Cita");
        btnRegresar.setText("Regresar");

        contenedor.add(lblTexto,0,0);
        contenedor.add(txtConsulta,1,0);
        contenedor.add(btnEliminar,1,1);
        contenedor.add(btnRegresar,0,1);

        contenedor.setAlignment(Pos.CENTER);
        contenedor.setHgap(10);
        contenedor.setVgap(10);

        contenedor.setHalignment(lblTexto, HPos.CENTER);
        contenedor.setHalignment(btnEliminar, HPos.CENTER);
        contenedor.setHalignment(btnRegresar, HPos.CENTER);
        
        
        
         btnEliminar.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Connection cnx = null;
                Statement sta = null;
                ResultSet rs = null;
                try{//si las variables estan mal ingresadas se captura un error y nos da una alert
                    String cedula;

                    cedula = txtConsulta.getText();

                    //hago llamada al driver que me permitira conectar con la base de datos
                    Class.forName("com.mysql.jdbc.Driver");//asumo que la base de datos se llama proyectobd1
                    cnx = DriverManager.getConnection("jdbc:mysql://localhost/proyectofinal?user=root&password=");
                    sta =cnx.createStatement();
                    //sta.executeQuery("delete from paciente where cedula_paciente = "+cedula+"");
                    sta.execute("delete from citasprevias where cedula_paciente = "+cedula+"");

                    contenedor.add(new Label("Registro eliminado con éxito!"),1,2);
                    cnx.close();
                    sta.close();

                }
                catch(Exception e){
                    e.printStackTrace();
                    Alert.AlertType type = Alert.AlertType.ERROR;
                    Alert alert = new Alert(type);

                    alert.getDialogPane().setContentText("No se ha podido eliminar la cita");
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
        
        primaryStage.setTitle("Eliminar Citas");
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
