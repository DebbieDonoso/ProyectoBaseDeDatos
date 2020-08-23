/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaclinica;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import subVentanas.Citas;
import subVentanas.Pacientes;

/**
 *
 * @author jimmy
 */
public class SistemaClinica extends Application {
    
   
    
    
    @Override
    public void start(Stage primaryStage) {
         //declaramos los elementos que usaremos en la ventana
        VBox root ;
        ComboBox cmbOpciones;
        Button btnIngresar;
        
        
        //instanciamos los objetos que usaremos en la ventana
        
        root = new VBox(15);
        cmbOpciones = new ComboBox();
        btnIngresar = new Button();
        
        
        //centramos el contenedor
        root.setAlignment(Pos.CENTER);
        
        //agregamos opciones al combobox, le colocamos un texto que inddique seleccione tabla y le damos un tamaño mas amplio
        cmbOpciones.getItems().addAll("Citas","Pacientes");
        cmbOpciones.setPrefWidth(200);
        cmbOpciones.setPromptText("Seleccione una tabla: ");
        
        //añadimos texto al boton y le agregamos una acciòn al hacer clic en èl
        btnIngresar.setText("Seleccionar");
        btnIngresar.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                try {
                    //dentro del boton validamos si ingreso a una de las opciones y colocamos su correspondiente if
                    //hacemos un bloque try en el caso de que de clic al boton sin ingresar a una opcion para capturar el error
                    String opcion = (String)cmbOpciones.getValue();//aqui obtenemos el valor de la opcion elegida
                    
                    if (opcion.equals("Citas")){//dependiendo de la opcion elegida ingresamos a la ventana
                        primaryStage.close();
                        Citas citas = new Citas();
                        citas.start(primaryStage);
                    }
                    else if(opcion.equals("Pacientes")){
                        primaryStage.close();
                        Pacientes pacientes = new Pacientes();
                        pacientes.start(primaryStage);
                    
                    }
                    else{
                    
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
        
        root.getChildren().addAll(cmbOpciones,btnIngresar);
        
        Scene scene = new Scene(root, 800, 650);
        
        primaryStage.setTitle("Menú principal");
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
