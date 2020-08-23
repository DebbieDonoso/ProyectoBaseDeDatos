/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subVentanas.ventanasPacientes;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;
import javafx.application.Application;
import static javafx.application.Application.launch;
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
import subVentanas.Pacientes;
/**
 *
 * @author F. Lopez
 */
public class anadirPaciente extends Application {
     @Override
    public void start(Stage primaryStage) {
        
        Label lblCedula_empl_reg,lblCedula_pac,lblNombre_pac,lblApellido_pac,lblFecha_nac,lblDirec,lblIdCita,lbltelefono;
        TextField txtCedula_emp_reg,txtCedula_pac,txtNombre_pac,txtApellido_pac,txtDirec,txtIdCita,txtTelefono;
        HBox contenedorFecha;
        ComboBox dia,mes,anio,cmbTipoCita;
        Button btnAñadir,btnRegresar;
        GridPane contenedor ;

        contenedor = new GridPane();
        contenedorFecha = new HBox(1);
        
        lblCedula_empl_reg  = new Label("Cedula del Recepcionista: ");
        lblCedula_pac = new Label("Cedula del Paciente: ");
        lblNombre_pac = new Label("Nombre del Paciente: ");
        lblApellido_pac = new Label("Apellido del Paciente: ");
        lblFecha_nac = new Label("Fecha de Nacimiento del Paciente: ");
        lblDirec = new Label("Dirección del Paciente: ");
        lblIdCita = new Label("Id de la cita: ");
        lbltelefono = new Label("Telefono del Paciente: ");
        

        txtCedula_emp_reg= new TextField();
        txtCedula_pac= new TextField();
        txtNombre_pac= new TextField();
        txtApellido_pac= new TextField();
        txtDirec= new TextField();
        txtIdCita= new TextField();
        txtTelefono= new TextField();

        
        cmbTipoCita = new ComboBox();
        dia = new ComboBox();
        mes = new ComboBox();
        anio = new ComboBox();
        
        btnAñadir = new Button();
        btnRegresar = new Button();
                
        
        txtCedula_emp_reg.setMinWidth(150);
        txtCedula_pac.setMinWidth(150);
        txtNombre_pac.setMinWidth(150);
        txtApellido_pac.setMinWidth(150);
        txtDirec.setMinWidth(150);
        txtIdCita.setMinWidth(150);
        txtTelefono.setMinWidth(150);
        
        
        dia.getItems().addAll("01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31");
        dia.setPromptText("Seleccione día");
        dia.setPrefWidth(130);
        
        mes.getItems().addAll("01","02","03","04","05","06","07","08","09","10","11","12");
        mes.setPromptText("Seleccione mes");
        mes.setPrefWidth(130);
        
        ArrayList<String> l_anio= new ArrayList<>();
        
        for(int i=2020;i>1900;i--)
            l_anio.add(Integer.toString(i));
        anio.getItems().addAll(l_anio);
        anio.setPromptText("Seleccione año");
        anio.setPrefWidth(130);
        
        contenedorFecha.getChildren().addAll(dia,mes,anio);
        
        
        btnAñadir.setText("Añadir Registro");
        btnRegresar.setText("Regresar");
        
        contenedor.add(lblCedula_empl_reg,0,0);
        contenedor.add(lblCedula_pac,0,1);
        contenedor.add(lblNombre_pac ,0,2);
        contenedor.add(lblApellido_pac,0,3);
        contenedor.add(lblFecha_nac,0,4);
        contenedor.add(lblDirec,0,5);
        contenedor.add(lblIdCita ,0,6);
        contenedor.add(lbltelefono ,0,7);
        

        contenedor.add(txtCedula_emp_reg,1,0);
        contenedor.add(txtCedula_pac,1,1);
        contenedor.add(txtNombre_pac,1,2);
        contenedor.add(txtApellido_pac,1,3);
        contenedor.add(contenedorFecha,1,4);
        contenedor.add(txtDirec,1,5);
        contenedor.add(txtIdCita,1,6);
        contenedor.add(txtTelefono,1,7);
        contenedor.add(btnAñadir,1,8);
        contenedor.add(btnRegresar,0,8);
        
        
        
        
        //centramos los labels y los separamos
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setHgap(10);
        contenedor.setVgap(10);
        contenedor.setHalignment(lblCedula_empl_reg, HPos.CENTER);
        contenedor.setHalignment(lblCedula_pac, HPos.CENTER);
        contenedor.setHalignment(lblNombre_pac, HPos.CENTER);
        contenedor.setHalignment(lblApellido_pac, HPos.CENTER);
        contenedor.setHalignment(lblFecha_nac, HPos.CENTER);
        contenedor.setHalignment(lblDirec, HPos.CENTER);
        contenedor.setHalignment(lblIdCita, HPos.CENTER);
        contenedor.setHalignment(lbltelefono, HPos.CENTER);
     
        contenedor.setHalignment(btnAñadir, HPos.CENTER);
        contenedor.setHalignment(btnRegresar, HPos.CENTER);
        
        
        
        
        btnAñadir.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                Connection cnx = null;
                Statement sta = null;
                ResultSet rs = null;
                try{//si las variables estan mal ingresadas se captura un error y nos da una alert
                    
                    String cedula_emp,cedula_pac,nombre_pac,apellido_pac,direc_pac,telefono,id_cita,day,month,year;
                    TipoCita tipoCita;
                    
                    cedula_emp=txtCedula_emp_reg.getText();
                    cedula_pac=txtCedula_pac.getText();
                    nombre_pac=txtNombre_pac.getText();
                    apellido_pac=txtApellido_pac.getText();
                    direc_pac=txtDirec.getText();
                    
                    telefono=txtTelefono.getText();
                    day = (String)dia.getValue();
                    month = (String)mes.getValue();//instancio datos ingresados en el formulario
                    year = (String)anio.getValue();
                    tipoCita = (TipoCita)cmbTipoCita.getValue();
                    id_cita=txtIdCita.getText();
                    
                    
                    //hago llamada al driver que me permitira conectar con la base de datos
                    Class.forName("com.mysql.jdbc.Driver");//asumo que la base de datos se llama proyectobd1
                    cnx = DriverManager.getConnection("jdbc:mysql://localhost/proyectobd1?user=root&password=");
                    sta =cnx.createStatement();//añado el dato a la base de datos en la tabla Citas_previas , si el elemento ya esta agregado no agregara el registro
                    sta.executeUpdate("insert into paciente values("+cedula_emp+",'"+cedula_pac+"','"+nombre_pac+"','"+apellido_pac+"','"+year+"-"+month+"-"+day+"','"+direc_pac+"','"+telefono+"','"+id_cita+"')");
                    contenedor.add(new Label("Registro añadido con éxito!"),1,9);
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
