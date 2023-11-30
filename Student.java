/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quizgame;

/**
 *
 * @author PC
 */

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author kobinath
 */
public class Student {
    private final StringProperty new_id;
    private final StringProperty Name;
    private final StringProperty Mobile;
    private final StringProperty  Course;
     
    public Student()
    {
        new_id = new SimpleStringProperty(this, "  new_id");
        Name = new SimpleStringProperty(this, "Name");
        Mobile = new SimpleStringProperty(this, "Mobile");
        Course = new SimpleStringProperty(this, "Course");
    }

    public StringProperty idProperty() { return   new_id; }
    public String getId() { return   new_id.get(); }
    public void setId(String newId) {   new_id.set(newId); }

    public StringProperty nameProperty() { return Name; }
    public String getName() { return Name.get(); }
    public void setName(String newName) { Name.set(newName); }

    public StringProperty mobileProperty() { return Mobile; }
    public String getMobile() { return Mobile.get(); }
    public void setMobile(String newMobile) { Mobile.set(newMobile); }
    
    public StringProperty courseProperty() { return Course; }
    public String getCourse() { return Course.get(); }
    public void setCourse(String newCourse) { Course.set(newCourse); }
    }
