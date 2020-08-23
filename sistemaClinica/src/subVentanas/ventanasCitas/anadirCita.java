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
import javafx.collections.ObservableList;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sistemaclinica.TipoCita;
import subVentanas.Citas;

/**
 *
 * @author jimmy
 */
public class anadirCita extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Label lblCedula,lblFecha,lblTipoCita,lblDiagnostico;
        TextField txtCedula,txtDiagnostico;
        HBox contenedorFecha;
        ComboBox dia,mes,anio,cmbTipoCita;
        Button btnAñadir,btnRegresar;
        GridPane contenedor ;
        
        
        
        
        contenedor = new GridPane();
        contenedorFecha = new HBox(1);
        
        lblCedula = new Label("Cedula: ");
        lblFecha = new Label("Fecha: ");
        lblTipoCita = new Label("Tipo de cita: ");
        lblDiagnostico = new Label("Diagnostico: ");
        
        txtCedula = new TextField();
        
        txtDiagnostico = new TextField();
        
        cmbTipoCita = new ComboBox();
        dia = new ComboBox();
        mes = new ComboBox();
        anio = new ComboBox();
        
        btnAñadir = new Button();
        btnRegresar = new Button();
        
        
        
        
        cmbTipoCita.getItems().addAll(TipoCita.ecografia,TipoCita.examenlaboratorio,TipoCita.consulta);
        cmbTipoCita.setPromptText("Seleccione un tipo de cita: ");
        cmbTipoCita.setPrefWidth(200);
        
        
        txtCedula.setMinWidth(150);
        txtDiagnostico.setPrefSize(150, 50);
        
        dia.getItems().addAll("01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31");
        dia.setPromptText("Seleccione día");
        dia.setPrefWidth(130);
        
        mes.getItems().addAll("01","02","03","04","05","06","07","08","09","10","11","12");
        mes.setPromptText("Seleccione mes");
        mes.setPrefWidth(130);
        
        anio.getItems().addAll("2020","2021");
        anio.setPromptText("Seleccione año");
        anio.setPrefWidth(130);
        
        contenedorFecha.getChildren().addAll(dia,mes,anio);
     
        
        
        
        btnAñadir.setText("Añadir Registro");
        btnRegresar.setText("Regresar");
        
        contenedor.add(lblCedula,0,0);
        contenedor.add(lblFecha,0,1);
        contenedor.add(lblTipoCita,0,2);
        contenedor.add(lblDiagnostico,0,3);
        
        
        
        
        contenedor.add(txtCedula,1,0);
        contenedor.add(contenedorFecha,1,1);
        contenedor.add(cmbTipoCita,1,2);
        contenedor.add(txtDiagnostico,1,3);
        contenedor.add(btnAñadir,1,4);
        contenedor.add(btnRegresar,1,5);
        
        
        
        
        
        //centramos los labels y los separamos
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setHgap(10);
        contenedor.setVgap(10);
        contenedor.setHalignment(lblCedula, HPos.CENTER);
        contenedor.setHalignment(lblFecha, HPos.CENTER);
        contenedor.setHalignment(lblTipoCita, HPos.CENTER);
        contenedor.setHalignment(lblDiagnostico, HPos.CENTER);
       
     
        contenedor.setHalignment(btnAñadir, HPos.CENTER);
        contenedor.setHalignment(btnRegresar, HPos.CENTER);
        
        
        
        
        btnAñadir.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                Connection cnx = null;
                Statement sta = null;
                ResultSet rs = null;
                try{//si las variables estan mal ingresadas se captura un error y nos da una alert
                    String cedula,day,month,year,diagnostico;
                    TipoCita tipoCita;
                    cedula = txtCedula.getText();
                    day = (String)dia.getValue();
                    month = (String)mes.getValue();//instancio datos ingresados en el formulario
                    year = (String)anio.getValue();
                    tipoCita = (TipoCita)cmbTipoCita.getValue();
                    diagnostico = txtDiagnostico.getText();
                    //hago llamada al driver que me permitira conectar con la base de datos
                    Class.forName("com.mysql.jdbc.Driver");//asumo que la base de datos se llama proyectobd1
                    cnx = DriverManager.getConnection("jdbc:mysql://localhost/proyectobd1?user=root&password=");
                    sta =cnx.createStatement();//añado el dato a la base de datos en la tabla Citas_previas , si el elemento ya esta agregado no agregara el registro
                    sta.executeUpdate("insert into citasprevias values("+cedula+",'"+year+"-"+month+"-"+day+"','"+tipoCita.toString()+"','"+diagnostico+"')");

                    contenedor.add(new Label("Registro añadido con éxito!"),1,6);
                    cnx.close();
                    sta.close();
                    
                }
                catch(Exception e){
                    e.printStackTrace();
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
        
        
        
        Scene scene = new Scene(contenedor, 600, 400);
        
        primaryStage.setTitle("Formulario de datos");
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
