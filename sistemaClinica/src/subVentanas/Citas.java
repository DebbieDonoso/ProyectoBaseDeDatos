/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subVentanas;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sistemaclinica.SistemaClinica;
import subVentanas.ventanasCitas.anadirCita;
import subVentanas.ventanasCitas.consultarCita;
import subVentanas.ventanasCitas.editarCita;
import subVentanas.ventanasCitas.eliminarCita;

/**
 *
 * @author jimmy
 */
public class Citas extends Application {
    
    @Override
    public void start(Stage primaryStage) {
         //declaramos los elementos que usaremos en la ventana
        VBox root ;
        HBox contenedorBotones;
        ComboBox cmbOpciones;
        Button btnIngresar,btnRegresar;
        
        
        //instanciamos los objetos que usaremos en la ventana
        
        root = new VBox(15);
        contenedorBotones = new HBox(10);
        cmbOpciones = new ComboBox();
        btnIngresar = new Button();
        btnRegresar = new Button();
        
        
        
        
        //centramos el contenedor
        root.setAlignment(Pos.CENTER);
        
        //agregamos opciones al combobox, le colocamos un texto que inddique seleccione tabla y le damos un tamaño mas amplio
        cmbOpciones.getItems().addAll("Añadir cita","Editar cita","Consultar cita","Eliminar cita");
        cmbOpciones.setPrefWidth(200);
        cmbOpciones.setPromptText("Seleccione una opción: ");
        
        //añadimos los botones al contenedor de botones y lo centramos
        
        contenedorBotones.getChildren().addAll(btnIngresar,btnRegresar);
        contenedorBotones.setAlignment(Pos.CENTER);
        
        //añadimos texto al boton Seleccionar y le agregamos una acciòn al hacer clic en èl
        btnIngresar.setText("Seleccionar");
        btnIngresar.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                try {
                    //dentro del boton validamos si ingreso a una de las opciones y colocamos su correspondiente if
                    //hacemos un bloque try en el caso de que de clic al boton sin ingresar a una opcion para capturar el error
                    String opcion = (String)cmbOpciones.getValue();//aqui obtenemos el valor de la opcion elegida
                    
                    if (opcion.equals("Añadir cita")){//dependiendo de la opcion elegida ingresamos a la ventana
                        primaryStage.close();
                        anadirCita ac = new anadirCita();
                        ac.start(primaryStage);
                    }
                    else if(opcion.equals("Consultar cita")){
                        primaryStage.close();
                        consultarCita cc = new consultarCita();
                        cc.start(primaryStage);
                    
                    }
                    else if(opcion.equals("Editar cita")){
                        primaryStage.close();
                        editarCita ec = new editarCita();
                        ec.start(primaryStage);
                    
                    }
                    else if(opcion.equals("Eliminar cita")){
                        primaryStage.close();
                        eliminarCita elc = new eliminarCita();
                        elc.start(primaryStage);
                    
                    }
                    
                
                }
                catch(Exception e){//se captura el error en caso de no elegir ninguna opcion y se le da una advertencia al usuario
                        Alert.AlertType type = Alert.AlertType.ERROR;
                        Alert alert = new Alert(type);

                        alert.getDialogPane().setContentText("No se ha podido ingresar, no ha seleccionando ninguna opción!");
                        alert.getDialogPane().setHeaderText("Error");
                        alert.showAndWait();
                        
                        if(alert.getResult().getText().equals("Aceptar")){
                            start(primaryStage);
                        }
                }
            }
        });
        
        
        //añadimos texto al boton Regresar y le agregamos una acciòn al hacer clic en èl
        btnRegresar.setText("Regresar");
        btnRegresar.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
                SistemaClinica sistema = new SistemaClinica();
                sistema.start(primaryStage);
            }
        });
        
        
        root.getChildren().addAll(cmbOpciones,contenedorBotones);
        
        Scene scene = new Scene(root, 500, 350);
        
        primaryStage.setTitle("Citas");
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
