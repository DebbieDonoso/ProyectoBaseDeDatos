/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subVentanas.ventanasCitas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import subVentanas.Citas;
import subVentanas.ventanasCitas.CitasPrevias;
/**
 *
 * @author jimmy
 */
public class consultarCita extends Application {
  
    @Override
    public void start(Stage primaryStage) {
        Label lblConsulta;
        Label lblTexto;
        TextField txtConsulta;
        Button btnConsultar,btnRegresar;
        GridPane contenedor ;
     
     
        
        contenedor = new GridPane();
       
      
        
        lblTexto = new Label("Ingrese el número de cédula del paciente");
        lblConsulta = new Label("Consulta: ");
        txtConsulta = new TextField();
        
        btnConsultar = new Button();
        btnRegresar = new Button();
        
        txtConsulta.setMinWidth(150);
        
        btnConsultar.setText("Consultar");
        btnRegresar.setText("Regresar");
        
        contenedor.add(lblTexto,0,0);
        contenedor.add(lblConsulta,0,1);
        contenedor.add(txtConsulta,1,1);
        contenedor.add(btnConsultar,3,0);
        contenedor.add(btnRegresar,3,1);
        
        
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setHgap(10);
        contenedor.setVgap(10);
        
        contenedor.setHalignment(lblTexto, HPos.CENTER);
        contenedor.setHalignment(lblConsulta, HPos.CENTER);
        
        contenedor.setHalignment(btnConsultar, HPos.CENTER);
        contenedor.setHalignment(btnRegresar, HPos.CENTER);
       
        
        btnConsultar.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                Connection cnx = null;
                Statement sta = null;
                ResultSet rs = null;
                
                TableView<CitasPrevias> table= new TableView<>();
                ObservableList<CitasPrevias> citas = FXCollections.observableArrayList();
                
                try{//si las variables estan mal ingresadas se captura un error y nos da una alert
                    String consulta;
                    consulta = txtConsulta.getText();
                   if(consulta.equals(consulta.length() != 10 )){
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
                    Class.forName("com.mysql.jdbc.Driver");//asumo que la base de datos se llama clinica
                    cnx = DriverManager.getConnection("jdbc:mysql://localhost/proyectobd1?user=root&password=");
                    sta =cnx.createStatement();//añado el dato a la base de datos en la tabla Citas_previas , si el elemento ya esta agregado no agregara el registro
                
                    rs= sta.executeQuery("select * from citasprevias where cedula_Paciente = "+consulta+"");
             
                    
                    while(rs.next()){
     
                             Date fecha= rs.getDate("fecha_Cita");
                             String tipo = rs.getString("tipo_Cita");
                             String d = rs.getString("diagnostico");
                             citas.add(new CitasPrevias(fecha,tipo, d));
                           
                        
                    }
                    
                    TableColumn<CitasPrevias, Date> columnafecha=new TableColumn<>("Fecha");
                    columnafecha.setMinWidth(100);
                    columnafecha.setCellValueFactory(new PropertyValueFactory<>("fecha_Cita"));
                    
                    TableColumn<CitasPrevias, String> columnatipo=new TableColumn<>("TipoCita");
                    columnatipo.setMinWidth(100);
                    columnatipo.setCellValueFactory(new PropertyValueFactory<>("tipo_Cita"));
                    
                    TableColumn<CitasPrevias, String> columnadiagnostico=new TableColumn<>("Diagnostico");
                    columnadiagnostico.setMinWidth(100);
                    columnadiagnostico.setCellValueFactory(new PropertyValueFactory<>("diagnostico"));
                    
                    table.setItems(citas);
                    table.getColumns().addAll(columnafecha,columnatipo,columnadiagnostico);
                    
                    contenedor.add(new Label("Estas son las citas disponibles"),1,3);
                    contenedor.add(table,1,4);
                    
                    table.setMinWidth(200);
                    
                    contenedor.setHalignment(table, HPos.CENTER);
                    cnx.close();
                    sta.close();
                    rs.close();
                }
                catch(Exception e){
          
                    Alert.AlertType type = Alert.AlertType.ERROR;
                    Alert alert = new Alert(type);

                    alert.getDialogPane().setContentText("No se ha podido agregar el ingreso, no ha seleccionando ninguna opción!");
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
        
        
        
        Scene scene = new Scene(contenedor, 600, 550);

        
        primaryStage.setTitle("Consultas");
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
