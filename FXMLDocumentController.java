/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package quizgame;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtMobile;
    @FXML
    private TextField txtCourse;
    @FXML
    private TableView<Student> table;
    @FXML
    private TableColumn<Student, String> IDcolumn;
    @FXML
    private TableColumn<Student, String> StudentNameColumn;
    @FXML
    private TableColumn<Student, String> MobileColumn;
    @FXML
    private TableColumn<Student, String> CourseColumn;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    void Add(ActionEvent event) {
        
            String stname,mobile,course;
            stname = txtName.getText();
            mobile = txtMobile.getText();
            course = txtCourse.getText();
        try 
        {
            pst = con.prepareStatement("INSERT INTO studentinfo(Name,Mobile,Course)values(?,?,?)");
            pst.setString(1, stname);
            pst.setString(2, mobile);
            pst.setString(3, course);
            pst.executeUpdate();
          
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Student Registation");
        
        alert.setHeaderText("Student Registation");
        alert.setContentText("Record Addedddd!");
        alert.showAndWait();
            table();
            
            txtName.setText("");
            txtMobile.setText("");
            txtCourse.setText("");
            txtName.requestFocus();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void table()
      {
          Connect();
          ObservableList<Student> students = FXCollections.observableArrayList();
       try 
       {
           pst = con.prepareStatement("select Name,Mobile,Course,new_id from studentinfo");  
           ResultSet rs = pst.executeQuery();
      {
        while (rs.next())
        {
            Student st = new Student();
           
            st.setName(rs.getString("name"));
            st.setMobile(rs.getString("mobile"));
            st.setCourse(rs.getString("course"));
            st.setId(rs.getString("new_id"));
            students.add(st);
       }
    } 
                table.setItems(students);
                IDcolumn.setCellValueFactory(f -> f.getValue().idProperty());
                StudentNameColumn.setCellValueFactory(f -> f.getValue().nameProperty());
                MobileColumn.setCellValueFactory(f -> f.getValue().mobileProperty());
                CourseColumn.setCellValueFactory(f -> f.getValue().courseProperty());
                
               
       }
       
       catch (SQLException ex) 
       {
           Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
       }
             table.setRowFactory( tv -> {
             TableRow<Student> myRow = new TableRow<>();
             myRow.setOnMouseClicked (event -> 
             {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  table.getSelectionModel().getSelectedIndex();
         
                   id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
                   txtName.setText(table.getItems().get(myIndex).getName());
                   txtMobile.setText(table.getItems().get(myIndex).getMobile());
                            txtCourse.setText(table.getItems().get(myIndex).getCourse());
                           
                         
                           
                }
             });
                return myRow;
                   });
    
    
      }
    @FXML
    void Delete(ActionEvent event) {
        myIndex = table.getSelectionModel().getSelectedIndex();
         
        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
                     
        try 
        {
            pst = con.prepareStatement("delete from studentinfo where new_id = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Student Registationn");
        
        alert.setHeaderText("Student Registation");
        alert.setContentText("Deletedd!");
        alert.showAndWait();
                  table();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    void Update(ActionEvent event) {
       
        String stname,mobile,course;
        
         myIndex = table.getSelectionModel().getSelectedIndex();
         
        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
           
            stname = txtName.getText();
            mobile = txtMobile.getText();
            course = txtCourse.getText();
        try 
        {
        pst = con.prepareStatement("DELETE FROM studentinfo WHERE new_id = ?");
        pst.setInt(1, id);
        pst.executeUpdate();

        
        pst = con.prepareStatement("INSERT INTO studentinfo (name, mobile, course) VALUES (?, ?, ?)");
        pst.setString(1, stname);
        pst.setString(2, mobile);
        pst.setString(3, course);
        pst.executeUpdate();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Student Registationn");        
        alert.setHeaderText("Student Registation");
        alert.setContentText("Updateddd!");
        alert.showAndWait();
                table();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;
    
    
    
     public void Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/dbstudentinfo","root","");
        } catch (ClassNotFoundException ex) {
          
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connect();
        table();
    }    
    
}